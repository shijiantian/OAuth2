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
@RequestMapping(value = GlobalConsts.login_need)
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    //分块上传状态检测
    @RequestMapping(value = "fileUpload",method = RequestMethod.GET)
    public Object doGet(HttpServletRequest request, HttpServletResponse response){
        return fileUploadService.checkChunkStatus(request,response);
    }

    //进行分块的上传
    @RequestMapping(value = "fileUpload",method = RequestMethod.POST)
    public Object doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return fileUploadService.writeChunkToDisk(request,response);
    }

}
