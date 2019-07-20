package com.springboot.maven.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.springboot.maven.entity.TReportTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.maven.service.utlis.MapUtils;
import com.springboot.maven.service.utlis.UUIDS;
import com.taobao.api.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志模板 服务类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
public interface ITReportTemplateService extends IService<TReportTemplate> {
    /**
     * @return List<Map   <   String   ,       Object>>
     * @Title:获取企业的所有模板
     * @param: userid 可选，不传表示获取企业的所有模板
     * offset 分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     * size 分页查询的每页大小，最大100
     * @author: FSS
     */
    public List<Map<String, Object>> reportTemplateList(String userid, String token);

    /**
     * 导入日报模板数据
     *
     * @return
     */
    public Map<String, Object> importReportTemplateData(String token,String companyId);
}
