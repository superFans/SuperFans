package com.springboot.maven.service;

import com.springboot.maven.entity.TReportRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志记录 服务类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
public interface ITReportRecordService extends IService<TReportRecord> {
    /**
     * 获取子公司每天所有用户日志记录
     *
     * @return
     */

    public List<Map<String, Object>> getAllUserLoggingByCompany(long size);
}
