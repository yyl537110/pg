package com.slj.pg.common.fastdfs;

import com.alibaba.fastjson.JSON;
import com.slj.pg.common.fastdfs.entity.DfsGroupConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzhang7@iflytek.com on 17/5/23.
 */
@Component
public class FastdfsMgr {

    @Value("${fastdfs.charset}")
    private String charset;

    @Value("${fastdfs.antiStealToken}")
    private Boolean antiStealToken;

    @Value("${fastdfs.connetTimeout}")
    private Integer connetTimeout;

    @Value("${fastdfs.networkTimeout}")
    private Integer networkTimeout;

    @Value("${fastdfs.groupId}")
    private String groupId;

    @Value("${fastdfs.trackerServer}")
    private String trackerServer;

    @Value("${fastdfs.trackerPort}")
    private Integer trackerPort;


    @PostConstruct
    public void init() {
        List<DfsGroupConf> dfsGroupConfs = new ArrayList<>();
        DfsGroupConf dfsGroupConf = new DfsGroupConf();
        dfsGroupConf.setCharset(charset);
        dfsGroupConf.setAntiStealToken(antiStealToken);
        dfsGroupConf.setConnectTimeout(connetTimeout);
        dfsGroupConf.setNetworkTimeout(networkTimeout);
        dfsGroupConf.setGroupID(groupId);
        dfsGroupConf.setTrackerServer(trackerServer.split(","));
        dfsGroupConf.setTrackerPort(trackerPort);
        dfsGroupConfs.add(dfsGroupConf);
        try{
            DfsSDKClient.initClient(dfsGroupConfs);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static DfsSDKClient getDfsSDKClient(){
        return DfsSDKClient.getDfsSDKClient();
    }
}
