package com.slj.pg.common.fastdfs.entity;


import com.slj.pg.common.fastdfs.SDKOperate;
import com.slj.pg.common.fastdfs.fastdfsBase.StorageClient;

/**
 * fastdfs实体
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class DfsEntity {
    /**
     * 存储客户端
     */
    private StorageClient storageClient;

    /**
     * sdk操作实体对象
     */
    private SDKOperate sdkOperate;

    public DfsEntity() {
    }

    public DfsEntity(StorageClient storageClient) {
        this.storageClient = storageClient;
        this.sdkOperate = new SDKOperate();
    }

    public StorageClient getStorageClient() {
        return storageClient;
    }

    public void setStorageClient(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

    public SDKOperate getSdkOperate() {
        return sdkOperate;
    }

    public void setSdkOperate(SDKOperate sdkOperate) {
        this.sdkOperate = sdkOperate;
    }
}
