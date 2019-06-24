package com.shijt.OAuth2.services;

import com.shijt.OAuth2.dto.ControllerResult;
import com.shijt.OAuth2.dto.FileUploadInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileUploadService {

    int getResumableChunkNumber(HttpServletRequest request);
    FileUploadInfo getFileUploadInfo(HttpServletRequest request);

    ControllerResult checkChunkStatus(HttpServletRequest request, HttpServletResponse response);

    ControllerResult writeChunkToDisk(HttpServletRequest request, HttpServletResponse response);
}
