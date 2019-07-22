package com.springboot.maven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.maven.entity.TReportRecord;

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

    public List<Map<String, Object>> getAllUserReportByCompany(String token);

    /**
     * 导入日志数据
     *
     * @return
     */
    public Map<String, Object> importEveryDayReportData(String token);

    /**
     * @return Object
     * @Title:获取日志统计数据
     * @param: report_id 日志id
     * 该接口用于获取日志的已读人数、评论条数、评论人数、点赞人数。
     * @author: FSS
     */
    public Map<String, Object> statistics(String report_id, String token);


}
