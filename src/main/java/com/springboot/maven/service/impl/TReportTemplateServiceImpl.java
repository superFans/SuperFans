package  com.springboot.maven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.springboot.maven.entity.TReportTemplate;
import com.springboot.maven.mapper.TReportTemplateMapper;
import com.springboot.maven.service.ITReportTemplateService;
import com.springboot.maven.service.utlis.MapUtils;
import com.springboot.maven.service.utlis.UUIDS;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志模板 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TReportTemplateServiceImpl extends ServiceImpl<TReportTemplateMapper, TReportTemplate> implements ITReportTemplateService {

    @Autowired
    TReportTemplateMapper treportTemplateMapper;
    /**
     * @Title:获取企业的所有模板
     * @param:
     * userid 可选，不传表示获取企业的所有模板
     * offset 分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     * size 分页查询的每页大小，最大100
     * @return  List<Map<String, Object>>
     * @author: FSS
     */
    @Override
    public List<Map<String, Object>> reportTemplateList(String userid , String token) {
        boolean  isNotWhile = true;
        List<Map<String,Object>> allReportTemplateList = new ArrayList<>();
        List<Map<String,Object>> reportTemplateDataList ;
        try {
            long pageNo = 0;
            long size = 100;
            while (isNotWhile){
                DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/template/listbyuserid");
                OapiReportTemplateListbyuseridRequest req = new OapiReportTemplateListbyuseridRequest();
                req.setUserid(userid);
                req.setOffset(pageNo);
                req.setSize(size);
                try {
                    OapiReportTemplateListbyuseridResponse rsp = client.execute(req, token);
                    Map<String, Object> rsmap = MapUtils.stringToMap(rsp.getBody());
                    if(!MapUtils.mapIsAnyBlank(rsmap,"errcode")&&!MapUtils.mapIsAnyBlank(rsmap,"result")&&Integer.valueOf(rsmap.get("errcode").toString()) == 0){
                        Map<String, Object> resultMap = (Map)rsmap.get("result");
                        if(!MapUtils.mapIsAnyBlank(resultMap,"template_list")){
                            reportTemplateDataList = (List)resultMap.get("template_list");
                            if(reportTemplateDataList!=null&&reportTemplateDataList.size()>0){
                                allReportTemplateList.addAll(reportTemplateDataList);
                            }else{
                                isNotWhile = false;
                            }
                            //下页有数据
                            if(!MapUtils.mapIsAnyBlank(resultMap,"next_cursor")){
                                pageNo = Long.valueOf(String.valueOf(resultMap.get("next_cursor")));
                            }else{
                                isNotWhile = false;
                            }
                        }else{
                            isNotWhile = false;
                        }
                    }else{
                        isNotWhile = false;
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                    isNotWhile = false;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return allReportTemplateList;
    }
    /**
     * 导入日报模板数据
     *
     * @return
     */
    @Override
    public  Map<String,Object> importReportTemplateData(String token,String companyId){
        Map<String, Object> map = new HashMap<>();
        map.put("code",1);
        map.put("message","导入失败");
        List<Map<String,Object>> list = reportTemplateList(null,token);
        if(list!=null&&list.size()>0){
            try {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> reportMap = list.get(i);
                    String id  = UUIDS.getID();
                    TReportTemplate tRt = new TReportTemplate();
                    tRt.setId(id);
                    tRt.setDeleted(0);
                    //重复code不insert
                    if(!MapUtils.mapIsAnyBlank(reportMap,"report_code")){
                        QueryWrapper<TReportTemplate> queryWrapper = new QueryWrapper<>();
                        queryWrapper.lambda().eq(TReportTemplate::getCode, reportMap.get("report_code"));
                        Integer count = treportTemplateMapper.selectCount(queryWrapper);
                        if(count>0){
                            continue;
                        }
                        tRt.setCode(String.valueOf(reportMap.get("report_code")));
                    }
                    if(!MapUtils.mapIsAnyBlank(reportMap,"create_time")){
                        tRt.setCreateTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
                        /*tRt.setModifyTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));*/
                    }else {
                        tRt.setCreateTime(Long.valueOf("1563767671000"));
                    }
                    if(!MapUtils.mapIsAnyBlank(reportMap,"name")){
                        tRt.setName(String.valueOf(reportMap.get("name")));
                    }
                    if(!StringUtils.isAllBlank(companyId)){
                        tRt.setCompanyId(companyId);
                    }
                    tRt.setTemplateGroup("各个公司的同一种模板");

                    int d = treportTemplateMapper.insert(tRt);
                    if(d>0){
                        map.put("code",0);
                        map.put("message","导入成功");
                        return map;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return map;
            }
        }
        return map;
    }
}
