package com.slj.pg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Component
public class DataUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataUtils.class);

    @Value("${mysql.bakupCmd}")
    private String bakupCmd;

    @Value("${mysql.recoverCmd}")
    private String recoverCmd;

    public boolean bakup(String filePath) {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
            LOGGER.info("cmd:{}",bakupCmd);
            Process process = Runtime.getRuntime().exec(bakupCmd);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            //0 表示线程正常终止。
            if(process.waitFor() == 0){
                return true;
            }
        }catch (Exception e) {
            LOGGER.error("数据库备份异常", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("---备份失败!");
        return false;
    }

    public boolean recover(String filePath) {
        LOGGER.info("----恢复mysql备份文件，filePath：{}", filePath);
        File bakFile = new File(filePath);
        if(!bakFile.exists()) {
            return false;
        }
        OutputStreamWriter writer = null;
        BufferedReader br = null;
        Process process = null;
        try {
            LOGGER.info("---备份恢复命令：{}", recoverCmd);
            process = Runtime.getRuntime().exec(recoverCmd);
            writer = new OutputStreamWriter(process.getOutputStream(),
                    "utf-8");
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                writer.write(line + "\r\n");
            }
            writer.flush();
            writer.close();
            if(process.waitFor() == 0){
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("数据恢复异常", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (br != null) {
                    br.close();
                }
                if(process != null) {
                    process.destroy();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}


