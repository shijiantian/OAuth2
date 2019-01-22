package com.shijt.OAuth2.services.impl;

import com.shijt.OAuth2.Utils.DataConversionUtil;
import com.shijt.OAuth2.dto.FileUploadInfo;
import com.shijt.OAuth2.dto.FileUploadInfoStorage;
import com.shijt.OAuth2.services.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.addr}")
    private String uploadAddr;

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
        if (!info.vaild())         {
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
