package com.slj.pg.common.fastdfs.entity;

/**
 * dfs集群配置对象
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class DfsGroupConf {
    private String groupID;
    /**
     * 连接超时时长
     */
    private int connectTimeout;

    private int networkTimeout;

    private String charset;

    private int trackerPort;

    private boolean antiStealToken;

    private String secretKey;

    private String[] trackerServer;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getNetworkTimeout() {
        return networkTimeout;
    }

    public void setNetworkTimeout(int networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getTrackerPort() {
        return trackerPort;
    }

    public void setTrackerPort(int trackerPort) {
        this.trackerPort = trackerPort;
    }

    public boolean isAntiStealToken() {
        return antiStealToken;
    }

    public void setAntiStealToken(boolean antiStealToken) {
        this.antiStealToken = antiStealToken;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String[] getTrackerServer() {
        return trackerServer;
    }

    public void setTrackerServer(String[] trackerServer) {
        this.trackerServer = trackerServer;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
