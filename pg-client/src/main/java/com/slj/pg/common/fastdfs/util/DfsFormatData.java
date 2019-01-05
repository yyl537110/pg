package com.slj.pg.common.fastdfs.util;

/**
 * Created by pengzhang7@iflytek.com on 17/4/6.
 */
public class DfsFormatData {
    public String[] splitFileId(String fileId) {
        String[] fileInfo = new String[2];
        fileInfo = fileId.split("/", 2);
        return fileInfo;
    }
}
