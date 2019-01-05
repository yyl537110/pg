package com.slj.pg.common.fastdfs;


import com.slj.pg.common.fastdfs.common.MyException;
import com.slj.pg.common.fastdfs.entity.DfsEntity;
import com.slj.pg.common.fastdfs.entity.DfsGroup;
import com.slj.pg.common.fastdfs.entity.DfsGroupConf;
import com.slj.pg.common.fastdfs.entity.Result;
import com.slj.pg.common.fastdfs.fastdfsBase.FileInfo;
import com.slj.pg.common.fastdfs.util.DfsFormatData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class DfsSDKClient {
    HashMap<String, DfsGroup> dfsGroups = new HashMap<String, DfsGroup>();
    ExecutorService exec = Executors.newCachedThreadPool();
    private static final Logger LOG = LoggerFactory.getLogger(DfsSDKClient.class);
    private static final int errorNo = -1;
    private static int isInited = -1;
    private static final int APPAND_BUFF_FILEDATA = 0;
    private static final int APPAND_FILE_FILEDATA = 1;

    private DfsSDKClient() {
    }

    private static final DfsSDKClient dfsSDKClient = new DfsSDKClient();

    public static DfsSDKClient getDfsSDKClient() {
        if (isInited == 0) {
            return dfsSDKClient;
        } else {
            return null;
        }
    }

    public static void initClient(List<DfsGroupConf> dfsGroupConfs) throws IOException {
        try {
            dfsSDKClient.init(dfsGroupConfs);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化client
     *
     * @param dfsGroupConfs fastdfs集群配置对象
     * @throws IOException
     * @throws MyException
     */
    private void init(List<DfsGroupConf> dfsGroupConfs) throws IOException, MyException {
        for (DfsGroupConf dfsGroupConf :
                dfsGroupConfs) {
            DfsGroup dfsGroup = new DfsGroup(dfsGroupConf);
            dfsGroups.put(dfsGroupConf.getGroupID(), dfsGroup);
        }
        isInited = 0;
    }

    /**
     * 上传完整文件
     *
     * @param file      待上传文件
     * @param clusterId fastdfs集群ID
     * @param groupName 集群分组名
     * @param typeName 后缀名
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Result uploadFile(final File file, final String clusterId, final String groupName, String typeName) throws ExecutionException, InterruptedException {
        Result result = (Result) exec.submit(new Callable() {
            Result result;
            public Result call() throws Exception {
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    result = operate.uploadFile(dfsEntity.getStorageClient(), file, groupName,typeName);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                }
            }
        }).get();
        return result;
    }

    /**
     * 上传完整文件
     *
     * @param inputStream 文件流
     * @param fileSize    文件大小
     * @param clusterId   集群id
     * @param groupName   集群存储分组名
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Result uploadFile(InputStream inputStream, long fileSize, String clusterId, String groupName,String typeName) throws ExecutionException, InterruptedException {
        LOG.info("dfs开始上传文件.......");
        Result result = (Result) exec.submit(new Callable() {
            Result result;
            public Result call() throws Exception {
                DfsGroup dfsGroup;
                try {
                    dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    result = operate.uploadFile(dfsEntity.getStorageClient(), inputStream, fileSize, groupName,typeName);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                }
            }
        }).get();
        LOG.info("dfs上传文件结束.......");
        return result;
    }

    /**
     * 创建文件
     *
     * @param clusterId fastdfs集群ID
     * @param groupName 集群分组名
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Result createFile(final String clusterId, final String groupName) throws ExecutionException, InterruptedException {
        Result result = (Result) exec.submit(new Callable() {
            Result result;

            public Result call() throws Exception {
                DfsGroup dfsGroup;
                try {
                    dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    result = operate.createFile(dfsEntity.getStorageClient(), groupName);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                }
            }
        }).get();
        return result;
    }

    /**
     * 获得文件信息
     * @param clusterId 集群id
     * @param fileId 文件id
     * @return 文件信息对象
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public FileInfo getFileInfo(final String clusterId, final String fileId) throws ExecutionException, InterruptedException {
        return (FileInfo) exec.submit(new Callable() {
            FileInfo fileInfo;
            DfsFormatData dfsFormatData = new DfsFormatData();

            public FileInfo call() throws Exception {
                try {
                    DfsGroup dfsgroup;
                    dfsgroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity = dfsgroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileNames = dfsFormatData.splitFileId(fileId);
                    if (fileNames.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    fileInfo = operate.getFileInfo(dfsEntity.getStorageClient(), fileNames[0], fileNames[1]);
                    return fileInfo;
                } catch (Exception ex) {
                    LOG.error(ex.getMessage(), ex);
                    return null;
                }
            }
        }).get();
    }

    /**
     * 追加上传文件
     *
     * @param fileBuff  待上传的文件byte数组
     * @param fileId    文件在fastdfs中的id
     * @param clusterId fastdfs集群id
     * @return
     */
    public Result appandFile(byte[] fileBuff, String fileId, String clusterId) throws ExecutionException, InterruptedException {
        Result result = this.appandFile(fileBuff, fileId, clusterId, APPAND_BUFF_FILEDATA);
        return result;
    }

    private Result appandFile(final Object fileData, final String fileId, final String clusterId, final int fileDataType) throws ExecutionException, InterruptedException {
        Result result = (Result) exec.submit(new Callable() {
            Result result;

            public Result call() throws Exception {
                DfsFormatData dfsFormatData = new DfsFormatData();
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileInfo = dfsFormatData.splitFileId(fileId);
                    if (fileInfo.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    if (fileDataType == APPAND_BUFF_FILEDATA) {
                        result = operate.appendFile(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1], (byte[]) fileData);
                    } else if (fileDataType == APPAND_FILE_FILEDATA) {
                        result = operate.appendFile(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1], (File) fileData);
                    }
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                }
            }
        }).get();
        return result;

    }

    /**
     * 追加上传文件
     *
     * @param file      带追加上传的文件
     * @param fileId    文件在fastdfs中的ID
     * @param clusterId fastdfs集群ID
     * @return
     * @throws MyException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Result appandFile(final File file, final String fileId, final String clusterId) throws ExecutionException, InterruptedException {
        Result result = this.appandFile(file, fileId, clusterId, APPAND_FILE_FILEDATA);
        return result;
    }

    /**
     * 删除文件
     *
     * @param fileId    文件在fastdfs中的id
     * @param clusterId fastdfs集群id
     * @return
     * @throws MyException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Result deleteFile(final String fileId, final String clusterId) throws ExecutionException, InterruptedException {
        Result result = (Result) exec.submit(new Callable() {
            Result result;

            public Result call() throws Exception {
                DfsFormatData dfsFormatData = new DfsFormatData();
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileInfo = dfsFormatData.splitFileId(fileId);
                    if (fileInfo.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    result = operate.deleteFile(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1]);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return new Result(false, errorNo);
                }
            }
        }).get();
        return result;
    }

    /**
     * 下载文件
     *
     * @param fileId    文件在fastdfs中的id
     * @param clusterId fastdfs集群id
     * @return
     * @throws MyException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public InputStream downloadFile(final String fileId, final String clusterId) throws ExecutionException, InterruptedException {
        return (InputStream) exec.submit(new Callable() {
            InputStream result;

            public InputStream call() throws Exception {
                DfsFormatData dfsFormatData = new DfsFormatData();
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileInfo = dfsFormatData.splitFileId(fileId);
                    if (fileInfo.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    result = operate.downloadFile(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1]);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                }
            }
        }).get();
    }

    /**
     * 下载文件
     *
     * @param fileId        文件在fastdfs中的id
     * @param clusterId     fastdfs集群id
     * @param fileOffset    文件下载开始位置
     * @param downloadBytes 文件下载长度
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws MyException
     */
    public byte[] downloadFile(final String fileId, final String clusterId, final long fileOffset, final long downloadBytes) throws ExecutionException, InterruptedException, MyException {
        return (byte[]) exec.submit(new Callable() {
            byte[] result;

            public byte[] call() throws Exception {
                DfsFormatData dfsFormatData = new DfsFormatData();
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity = null;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileInfo = dfsFormatData.splitFileId(fileId);
                    if (fileInfo.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    result = operate.downloadFile(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1], fileOffset, downloadBytes);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                }
            }
        }).get();
    }

    /**
     * 下载文件
     *
     * @param fileId        文件在fastdfs中的id
     * @param clusterId     fastdfs集群id
     * @param fileOffset    文件下载开始位置
     * @param downloadBytes 文件下载长度
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws MyException
     */
    public InputStream downloadPartFile(final String fileId, final String clusterId, final long fileOffset, final long downloadBytes) throws ExecutionException, InterruptedException, MyException {
        return (InputStream) exec.submit(new Callable() {
            InputStream result;

            public InputStream call() throws Exception {
                DfsFormatData dfsFormatData = new DfsFormatData();
                try {
                    DfsGroup dfsGroup = getDfsGroup(clusterId);
                    DfsEntity dfsEntity = null;
                    dfsEntity = dfsGroup.getDfsEntity();
                    SDKOperate operate = dfsEntity.getSdkOperate();
                    String[] fileInfo = dfsFormatData.splitFileId(fileId);
                    if (fileInfo.length < 2) {
                        throw new MyException("fileId is wrong");
                    }
                    result = operate.downloadFilePartStream(dfsEntity.getStorageClient(), fileInfo[0], fileInfo[1], fileOffset, downloadBytes);
                    dfsGroup.recoverClient(dfsEntity.getStorageClient());
                    return result;
                } catch (MyException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    return null;
                }
            }
        }).get();
    }


    /**
     * 根据集群id获取集群
     *
     * @param groupId 集群id
     * @return
     * @throws MyException
     */
    private DfsGroup getDfsGroup(String groupId) throws MyException {
        if (groupId != null && groupId.length() > 0) {
            if (dfsGroups.containsKey(groupId)) {
                DfsGroup dfsGroup = dfsGroups.get(groupId);
                return dfsGroup;
            } else {
                throw new MyException("dfsGroupId is not contained");
            }
        } else {
            throw new MyException("dfsGroupId is empty");
        }
    }
}
