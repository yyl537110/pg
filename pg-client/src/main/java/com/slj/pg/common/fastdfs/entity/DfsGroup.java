package com.slj.pg.common.fastdfs.entity;


import com.slj.pg.common.fastdfs.common.MyException;
import com.slj.pg.common.fastdfs.fastdfsBase.ClientGlobal;
import com.slj.pg.common.fastdfs.fastdfsBase.StorageClient;
import com.slj.pg.common.fastdfs.fastdfsBase.TrackerClient;
import com.slj.pg.common.fastdfs.fastdfsBase.TrackerServer;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * fastdfs集群
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class DfsGroup {
    private ClientGlobal clientGlobal;

    /**
     * 存储客户端缓冲池
     */
    private Queue<StorageClient> storageClientPool;

    public DfsGroup() {

    }

    public DfsGroup(DfsGroupConf dfsGroupConf) throws IOException, MyException {
        this.clientGlobal = new ClientGlobal();
        this.storageClientPool = new LinkedBlockingQueue<>(20);
        clientGlobal.init(dfsGroupConf);
        TrackerClient trackerClient = new TrackerClient(this.clientGlobal);
        TrackerServer trackerServer = trackerClient.getConnection();
        trackerServer.close();
        StorageClient storageClient = new StorageClient(trackerServer, null, this.clientGlobal);
        this.storageClientPool.offer(storageClient);
    }

    /**
     * 获取dfs实体
     *
     * @return
     * @throws IOException
     * @throws MyException
     */
    public synchronized DfsEntity getDfsEntity() throws IOException, MyException {
        StorageClient storageClient = getActiveTStorageClient();
        DfsEntity entity = new DfsEntity(storageClient);
        return entity;
    }

    /**
     * 存储客户端释放
     *
     * @param storageClient 存储客户端
     */
    public synchronized void recoverClient(StorageClient storageClient) {
        if (this.storageClientPool.size() < 20) {
            storageClientPool.add(storageClient);
        }
    }

    /**
     * 获取可用存储客户端
     *
     * @return
     * @throws IOException
     */
    private StorageClient getActiveTStorageClient() throws IOException {
        StorageClient storageClient;
        if (!this.storageClientPool.isEmpty()) {
            storageClient = storageClientPool.remove();
        } else {
            TrackerClient trackerClient = new TrackerClient(this.clientGlobal);
            TrackerServer trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, null, this.clientGlobal);
        }
        return storageClient;
    }
}
