package com.slj.pg.common.fastdfs;


import com.slj.pg.common.fastdfs.common.MyException;
import com.slj.pg.common.fastdfs.common.NameValuePair;
import com.slj.pg.common.fastdfs.entity.Result;
import com.slj.pg.common.fastdfs.fastdfsBase.FileInfo;
import com.slj.pg.common.fastdfs.fastdfsBase.ProtoCommon;
import com.slj.pg.common.fastdfs.fastdfsBase.StorageClient;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * sdk操作
 * Created by pengzhang7@iflytek.com on 17/4/5.
 */
public class SDKOperate {
    /**
     * 上传文件
     *
     * @param storageClient 存储客户端
     * @param file          待上传文件
     * @param groupName     存储分组名称
     * @param typeName     后缀名
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result uploadFile(StorageClient storageClient, File file, String groupName,String typeName) throws IOException, MyException {
        String extName = null;
        Result result = new Result();
        final byte cmd = ProtoCommon.STORAGE_PROTO_CMD_UPLOAD_FILE;
        int nPos = file.getName().lastIndexOf('.');
        if (nPos > 0 && file.getName().length() - nPos <= ProtoCommon.FDFS_FILE_EXT_NAME_MAX_LEN + 1) {
            extName = file.getName().substring(nPos + 1);
        }
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("extName", extName);
        String[] resultStr = storageClient.upload_file(cmd, groupName, file, typeName, meta_list);
        if (resultStr != null && resultStr.length == 2) {
            result.setFileId(resultStr[0] + "/" + resultStr[1]);
            result.setSuccess(true);
        } else {
            result.setErrorNo(storageClient.getErrorCode());
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param storageClient 存储客户端
     * @param inputStream   待上传文件流
     * @param fileLength    文件长度
     * @param groupName     存储分组名称
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result uploadFile(StorageClient storageClient, InputStream inputStream, long fileLength, String groupName,String typeName) throws IOException, MyException {
        Result result = new Result();
        final byte cmd = ProtoCommon.STORAGE_PROTO_CMD_UPLOAD_FILE;
        String[] resultStr = storageClient.upload_file(cmd, groupName, inputStream, fileLength, typeName, null);
        if (resultStr != null && resultStr.length == 2) {
            result.setFileId(resultStr[0] + "/" + resultStr[1]);
            result.setSuccess(true);
        } else {
            result.setErrorNo(storageClient.getErrorCode());
        }
        return result;
    }

    /**
     * 创建文件
     *
     * @param storageClient 存储客户端
     * @param groupName     存储分组名称
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result createFile(StorageClient storageClient, String groupName) throws IOException, MyException {
        Result result = new Result();
        byte[] fileBuff = new byte[]{};
        String[] resultStr = storageClient.upload_appender_file(groupName, fileBuff, null, null);
        if (resultStr != null && resultStr.length == 2) {
            result.setFileId(resultStr[0] + "/" + resultStr[1]);
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setErrorNo(storageClient.getErrorCode());
        }
        return result;
    }

    /**
     * 追加文件
     *
     * @param storageClient    存储客户端
     * @param groupName        存储分组名称
     * @param appenderFileName 被追加的文件在fastdfs中名字
     * @param fileBuff         待追加的文件数据
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result appendFile(StorageClient storageClient, String groupName, String appenderFileName, byte[] fileBuff) throws IOException, MyException {
        int errorCode = -1;
        Result result = new Result();
        errorCode = storageClient.append_file(groupName, appenderFileName, fileBuff);
        result = getResult(errorCode, groupName, appenderFileName);
        return result;
    }

    /**
     * 追加文件
     *
     * @param storageClient    存储客户端
     * @param groupName        存储分组名称
     * @param appenderFileName 被追加的文件在fastdfs中名字
     * @param file             待追加的文件数据
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result appendFile(StorageClient storageClient, String groupName, String appenderFileName, File file) throws IOException, MyException {
        int errorCode = -1;
        Result result = new Result();
        errorCode = storageClient.append_file(groupName, appenderFileName, file);
        result = getResult(errorCode, groupName, appenderFileName);
        return result;
    }

    FileInfo getFileInfo(StorageClient storageClient, String groupName, String remoteFileName) throws IOException, MyException {
        return storageClient.get_file_info(groupName,remoteFileName);
    }

    /**
     * 删除文件
     *
     * @param storageClient  存储客户端
     * @param groupName      存储分组名称
     * @param remoteFileName 被追加的文件在fastdfs中名字
     * @return
     * @throws IOException
     * @throws MyException
     */
    Result deleteFile(StorageClient storageClient, String groupName, String remoteFileName) throws IOException, MyException {
        int errorCode = -1;
        Result result = new Result();
        errorCode = storageClient.delete_file(groupName, remoteFileName);
        result = getResult(errorCode, groupName, remoteFileName);
        return result;
    }

    /**
     *
     * @param storageClient
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     * @throws MyException
     */
    InputStream downloadFile(StorageClient storageClient, String groupName, String remoteFileName) throws IOException, MyException {
        InputStream inputStream;
        inputStream = storageClient.download_file(groupName, remoteFileName);
        if (inputStream == null) {
            throw new MyException("can not download File InputStream");
        }
        return inputStream;
    }

    /**
     *
     * @param storageClient
     * @param groupName
     * @param remoteFileName
     * @param file_offset
     * @param download_bytes
     * @return
     * @throws IOException
     * @throws MyException
     */
    InputStream downloadFilePartStream(StorageClient storageClient, String groupName, String remoteFileName, long file_offset, long download_bytes) throws IOException, MyException {
        InputStream inputStream;
        byte[] data = downloadFile(storageClient, groupName, remoteFileName, file_offset, download_bytes);
        if (data == null) {
            throw new MyException("can not download part File stream");
        }
        inputStream = new ByteArrayInputStream(data);
        return inputStream;
    }

    /**
     *
     * @param storageClient
     * @param groupName
     * @param remoteFileName
     * @param file_offset
     * @param download_bytes
     * @return
     * @throws IOException
     * @throws MyException
     */
    byte[] downloadFile(StorageClient storageClient, String groupName, String remoteFileName, long file_offset, long download_bytes) throws IOException, MyException {
        byte[] data;
        data = storageClient.download_file(groupName, remoteFileName, file_offset, download_bytes);
        if (data == null) {
            throw new MyException("can not download File");
        }
        return data;
    }

    private Result getResult(int errorCode, String groupName, String appenderFileName) {
        Result result = new Result();
        result.setFileId(groupName + "/" + appenderFileName);
        if (errorCode == 0) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setErrorNo(errorCode);
        }
        return result;
    }
}
