package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.FileUploadInfo;
import com.shijt.OAuth2.dto.FileUploadInfoStorage;
import com.shijt.OAuth2.services.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

@Api(value = "文件上传控制器")
@RestController
@RequestMapping(value = GlobalConsts.login_no_need)
public class FileUploadController {

    @Value("${file.upload.addr}")
    private String uploadAddr;
    @Autowired
    private FileUploadService fileUploadService;

    //分块上传状态检测
    @RequestMapping(value = "fileUpload",method = RequestMethod.GET)
    public Object doGet(HttpServletRequest request, HttpServletResponse response){
        int resumableChunkNumber=fileUploadService.getResumableChunkNumber(request);
        FileUploadInfo info=fileUploadService.getFileUploadInfo(request);

        if (info.uploadedChunks.contains(resumableChunkNumber)) {
            return new ControllerResult("已上传");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ControllerResult("未上传");
        }
    }

    //进行分块的上传
    @RequestMapping(value = "fileUpload",method = RequestMethod.POST)
    public Object doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int resumableChunkNumber = fileUploadService.getResumableChunkNumber(request);
        FileUploadInfo info = fileUploadService.getFileUploadInfo(request);
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(info.getResumableFilePath(), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //查找chunk位置
        raf.seek((resumableChunkNumber - 1) * (long)info.getResumableChunkSize());

        //写入磁盘
        InputStream is = request.getInputStream();
        long readed = 0;
        long content_length = request.getContentLength();
        byte[] bytes = new byte[1024 * 100];
        while(readed < content_length) {
            int r = is.read(bytes);
            if (r < 0)  {
                break;
            }
            raf.write(bytes, 0, r);
            readed += r;
        }
        raf.close();


        //chunk上传完成.
        info.uploadedChunks.add(resumableChunkNumber);
        if (info.checkIfUploadFinished()) { //Check if all chunks uploaded, and change filename
            FileUploadInfoStorage.getInstance().remove(info);
            return new ControllerResult("全部上传完成");
        } else {
            return new ControllerResult("分块上传完成");
        }
    }

}
