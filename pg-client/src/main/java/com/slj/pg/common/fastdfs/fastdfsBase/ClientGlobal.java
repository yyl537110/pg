/**
 * Copyright (C) 2008 Happy Fish / YuQing
 * <p>
 * FastDFS Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
 **/

package com.slj.pg.common.fastdfs.fastdfsBase;


import com.slj.pg.common.fastdfs.common.IniFileReader;
import com.slj.pg.common.fastdfs.common.MyException;
import com.slj.pg.common.fastdfs.entity.DfsGroupConf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Global variables
 *
 * @author Happy Fish / YuQing
 * @version Version 1.11
 */
public class ClientGlobal {
    public int g_connect_timeout; //millisecond
    public int g_network_timeout; //millisecond
    public String g_charset;
    public int g_tracker_http_port;
    public boolean g_anti_steal_token;  //if anti-steal token
    public String g_secret_key;   //generage token secret key
    public TrackerGroup g_tracker_group;

    public final int DEFAULT_CONNECT_TIMEOUT = 5;  //second
    public final int DEFAULT_NETWORK_TIMEOUT = 30; //second

    public ClientGlobal() {
    }

    /**
     * load global variables
     *
     * @param dfsGroupConf config object
     */
    public void init(DfsGroupConf dfsGroupConf) throws MyException {
        IniFileReader iniReader;
        String[] szTrackerServers;
        String[] parts;

//        iniReader = new IniFileReader(conf_filename);

        g_connect_timeout = dfsGroupConf.getConnectTimeout();
//        g_connect_timeout = iniReader.getIntValue("connect_timeout", DEFAULT_CONNECT_TIMEOUT);
        if (g_connect_timeout < 0) {
            g_connect_timeout = DEFAULT_CONNECT_TIMEOUT;
        }
        g_connect_timeout *= 1000; //millisecond

        g_network_timeout = dfsGroupConf.getNetworkTimeout();
//        g_network_timeout = iniReader.getIntValue("network_timeout", DEFAULT_NETWORK_TIMEOUT);
        if (g_network_timeout < 0) {
            g_network_timeout = DEFAULT_NETWORK_TIMEOUT;
        }
        g_network_timeout *= 1000; //millisecond

        g_charset = dfsGroupConf.getCharset();
//        g_charset = iniReader.getStrValue("charset");
        if (g_charset == null || g_charset.length() == 0) {
            g_charset = "utf-8";
        }

        szTrackerServers = dfsGroupConf.getTrackerServer();
        if (szTrackerServers == null) {
            throw new MyException("item \"tracker_server\" not found");
        }

        InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];
        for (int i = 0; i < szTrackerServers.length; i++) {
            parts = szTrackerServers[i].split("\\:", 2);
            if (parts.length != 2) {
                throw new MyException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
            }

            tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        }
        g_tracker_group = new TrackerGroup(tracker_servers, this.g_network_timeout, this.g_connect_timeout);

        g_tracker_http_port = dfsGroupConf.getTrackerPort();
        g_anti_steal_token = dfsGroupConf.isAntiStealToken();
        if (g_anti_steal_token) {
            g_secret_key = dfsGroupConf.getSecretKey();
        }
    }

    /**
     * construct Socket object
     *
     * @param ip_addr ip address or hostname
     * @param port    port number
     * @return connected Socket object
     */
    public Socket getSocket(String ip_addr, int port) throws IOException {
        Socket sock = new Socket();
        sock.setSoTimeout(this.g_network_timeout);
        sock.connect(new InetSocketAddress(ip_addr, port), this.g_connect_timeout);
        return sock;
    }

    /**
     * construct Socket object
     *
     * @param addr InetSocketAddress object, including ip address and port
     * @return connected Socket object
     */
    public Socket getSocket(InetSocketAddress addr) throws IOException {
        Socket sock = new Socket();
        sock.setSoTimeout(this.g_network_timeout);
        sock.connect(addr, this.g_connect_timeout);
        return sock;
    }

    public int getG_connect_timeout() {
        return g_connect_timeout;
    }

    public void setG_connect_timeout(int connect_timeout) {
        g_connect_timeout = connect_timeout;
    }

    public int getG_network_timeout() {
        return g_network_timeout;
    }

    public void setG_network_timeout(int network_timeout) {
        g_network_timeout = network_timeout;
    }

    public String getG_charset() {
        return g_charset;
    }

    public void setG_charset(String charset) {
        g_charset = charset;
    }

    public int getG_tracker_http_port() {
        return g_tracker_http_port;
    }

    public void setG_tracker_http_port(int tracker_http_port) {
        g_tracker_http_port = tracker_http_port;
    }

    public boolean getG_anti_steal_token() {
        return g_anti_steal_token;
    }

    public boolean isG_anti_steal_token() {
        return g_anti_steal_token;
    }

    public void setG_anti_steal_token(boolean anti_steal_token) {
        g_anti_steal_token = anti_steal_token;
    }

    public String getG_secret_key() {
        return g_secret_key;
    }

    public void setG_secret_key(String secret_key) {
        g_secret_key = secret_key;
    }

    public TrackerGroup getG_tracker_group() {
        return g_tracker_group;
    }

    public void setG_tracker_group(TrackerGroup tracker_group) {
        g_tracker_group = tracker_group;
    }
}
