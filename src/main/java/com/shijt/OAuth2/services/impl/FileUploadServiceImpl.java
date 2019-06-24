package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.FileUploadInfo;
import com.shijt.OAuth2.dto.FileUploadInfoStorage;
import com.shijt.OAuth2.services.FileUploadService;
import com.shijt.OAuth2.utils.DataConversionUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.addr}")
    private String uploadAddr;
    private static final Logger log= LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public ControllerResult checkChunkStatus(HttpServletRequest request, HttpServletResponse response) {
        int resumableChunkNumber=this.getResumableChunkNumber(request);
        FileUploadInfo info=this.getFileUploadInfo(request);

        if (info.uploadedChunks.contains(resumableChunkNumber)) {
            return new ControllerResult("已上传");
        } else {
            //将404状态作为chunk还未上传的信号
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ControllerResult("未上传");
        }
    }

    @Override
    public ControllerResult writeChunkToDisk(HttpServletRequest request, HttpServletResponse response) {
        int resumableChunkNumber = this.getResumableChunkNumber(request);
        FileUploadInfo info = this.getFileUploadInfo(request);

        InputStream is = null;
        try{
            is=request.getInputStream();
        }catch (IOException e){
            log.info("从request获取输入流错误：",e);
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(info.getResumableFilePath(), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //写入磁盘
        //查找chunk位置
        long readed = 0;
        long content_length = request.getContentLength();
        byte[] bytes = new byte[1024 * 100];
        try{
            raf.seek((resumableChunkNumber - 1) * (long)info.getResumableChunkSize());
            while(readed < content_length) {
                int r = is.read(bytes);
                if (r < 0)  {
                    break;
                }
                raf.write(bytes, 0, r);
                readed += r;
            }
            raf.close();
        }catch (IOException e){
            IOUtils.closeQuietly(raf);
            IOUtils.closeQuietly(is);
            log.info("文件写入错误：",e);
        }

        //chunk上传完成.
        info.uploadedChunks.add(resumableChunkNumber);
        if (info.checkIfUploadFinished()) { //Check if all chunks uploaded, and change filename
            FileUploadInfoStorage.getInstance().remove(info);
            return new ControllerResult("全部上传完成");
        } else {
            return new ControllerResult("分块上传完成");
        }
    }

    @Override
    public int getResumableChunkNumber(HttpServletRequest request) {
        String resumableChunkNumber=request.getParameter("resumableChunkNumber");
        return DataConversionUtil.str2Int(resumableChunkNumber,-1);
    }

    @Override
    public FileUploadInfo getFileUploadInfo(HttpServletRequest request) {
        int resumableChunkSize          = DataConversionUtil.str2Int(request.getParameter("resumableChunkSize"), -1);
        long resumableTotalSize         = DataConversionUtil.str2Long(request.getParameter("resumableTotalSize"), -1);
        String resumableIdentifier      = request.getParameter("resumableIdentifier");
        String resumableFilename        = request.getParameter("resumableFilename");
        String resumableRelativePath    = request.getParameter("resumableRelativePath");

        new File(uploadAddr).mkdirs();
        String resumableFilePath        = new File(uploadAddr, resumableFilename).getAbsolutePath() + ".temp";

        FileUploadInfoStorage storage = FileUploadInfoStorage.getInstance();
        FileUploadInfo info = storage.get(resumableChunkSize, resumableTotalSize,
                resumableIdentifier, resumableFilename, resumableRelativePath, resumableFilePath);
        if (!info.vaild()){
            storage.remove(info);
            try {
                throw new ServletException("Invalid request params.");
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return info;

    }
}
