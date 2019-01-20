package com.shijt.OAuth2.services;

import com.shijt.OAuth2.dto.FileUploadInfo;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    int getResumableChunkNumber(HttpServletRequest request);
    FileUploadInfo getFileUploadInfo(HttpServletRequest request);
}
