package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yaoyl on 2018/12/31.
 */
@RestController
@RequestMapping("/files")
@Api(value = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", notes = "文件上传", response = HttpMessage.class)
    public HttpMessage<String> upload(MultipartFile file) {
        Preconditions.checkState(!file.isEmpty(), CustomStatus.PARAM_ERROR);
        String uploadResult = fileService.upload(file);
        if(StringUtils.isNotBlank(uploadResult)) {
            return new HttpMessage<>(uploadResult,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }
}
