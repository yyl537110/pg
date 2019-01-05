package com.slj.pg.service.impl;

import com.slj.pg.common.fastdfs.DfsSDKClient;
import com.slj.pg.common.fastdfs.FastdfsMgr;
import com.slj.pg.common.fastdfs.entity.Result;
import com.slj.pg.service.interfaces.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${fastdfs.cluster}")
    private String cluster;

    @Value("${fastdfs.groupName}")
    private String groupName;

    @Value("${fastdfs.prefix}")
    private String prefix;

    @Override
    public String upload(MultipartFile file) {
        //文件名
        String fileName = new String(file.getOriginalFilename().getBytes()).replace(" ", "");
        // 后缀名 不含.
        String typeName = null;
        try {
            typeName = fileName.substring(fileName.lastIndexOf(".")+1);
        } catch (Exception ex) {
            LOGGER.error("-------- process request method:文件类型获取错误; param: fileName: {}; exception: {}",fileName, ex.getMessage());
            return "err";
        }

        try {
            //1 fastDFS上传
            InputStream inputStream = file.getInputStream();
            DfsSDKClient dfsSDKClient = FastdfsMgr.getDfsSDKClient();
            Result result =  dfsSDKClient.uploadFile(inputStream, file.getSize(),cluster, groupName,typeName);
            if (result.isSuccess()) {
                String fileId = result.getFileId();
                LOGGER.info("dfs文件上传成功; fileId: {};",fileId);
                return prefix + fileId.replace(groupName + "/M00","");
            }
        }catch (Exception ex){
            LOGGER.error("-------- process request method:uploadFile ERROR; param: fileName: {}; exception: {}",
                    file.getOriginalFilename(), ex.getMessage());
        }
        return "";
    }
}
