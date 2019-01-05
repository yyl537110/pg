package com.slj.pg.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
public interface FileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
