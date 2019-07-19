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

    public List<Map<String, Object>> getAllUserReportByCompany(String token);

    /**
     * 导入日志数据
     *
     * @return
     */
    public Map<String, Object> importEveryDayReportData(String token);

    /**
     * @Title:获取企业的所有模板
     * @param:
     * userid 可选，不传表示获取企业的所有模板
     * offset 分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     * size 分页查询的每页大小，最大100
     * @return  List<Map<String, Object>>
     * @author: FSS
     */
    public List<Map<String, Object>> reportTemplateList(String userid , String token)

}
