package com.slj.pg.service.interfaces;

import com.slj.pg.bean.BakLog;
import com.slj.pg.http.HttpMessage;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
public interface BakLogService extends BaseService<BakLog> {
    /**
     * 意见备份
     * @return
     */
    BakLog createBak();

    /**
     * 恢复备份
     * @param id
     * @return
     */
    boolean recover(Integer id);
}
