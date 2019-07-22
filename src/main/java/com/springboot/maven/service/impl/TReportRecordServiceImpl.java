package  com.springboot.maven.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.request.OapiReportStatisticsRequest;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportListResponse;
import com.dingtalk.api.response.OapiReportStatisticsResponse;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.springboot.maven.entity.TReportRecord;
import com.springboot.maven.entity.TReportTemplate;
import com.springboot.maven.mapper.TReportRecordMapper;
import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.utlis.MapUtils;
import com.springboot.maven.service.utlis.UUIDS;
import com.springboot.maven.service.utlis.commensUtil;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.LocaleData;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 日志记录 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TReportRecordServiceImpl extends ServiceImpl<TReportRecordMapper, TReportRecord> implements ITReportRecordService {


    @Autowired
    TReportRecordMapper treportRecordMapper;
    @Autowired
    ITReportRecordService itReportRecordService;
    /**
     * @Title:获取用户日志数据
     * @param:
     * useridList 需要查询的用户列表，最大列表长度：10（如"1226682231742708,zhansan"）
     * duration 截止当前系统时间几天前的签到数据（如：1 一天前)
     * pageNo 分页查询的游标，最开始可以传0，然后以1、2依次递增
     * size 分页查询的每页大小，最大100
     * @return  List<Map<String, Object>>
     * @author: FSS
     */
    public Map<String, Object> reportList(String userid , long duration , long pageNo , long size , String token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/list");
        OapiReportListRequest request = new OapiReportListRequest();
        request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(duration));
        request.setEndTime(System.currentTimeMillis());
        request.setSize(size);
        request.setCursor(pageNo);
        request.setUserid(userid);
        try {
            OapiReportListResponse response = client.execute(request, token);
            Map<String, Object> rsmap =  MapUtils.stringToMap(response.getBody());
            if(rsmap!=null){
                return rsmap;
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /**
     * @Title:获取日志统计数据
     * @param:
     * report_id 日志id
     * 该接口用于获取日志的已读人数、评论条数、评论人数、点赞人数。
     * @return   Object
     * @author: FSS
     */
    @Override
    public Map<String, Object> statistics(String report_id , String token ) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/statistics");
        OapiReportStatisticsRequest req = new OapiReportStatisticsRequest();
        req.setReportId(report_id);
        try {
            OapiReportStatisticsResponse rsp = client.execute(req, token);
            Map<String, Object> rsmap =  MapUtils.stringToMap(rsp.getBody());
            if(rsmap!=null){
                return rsmap;
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /**
     * 获取子公司每天所有用户日志记录
     * @return
     */
    @Override
    public List<Map<String,Object>> getAllUserReportByCompany(String token) {
        try {
            List<Map<String,Object>> allCheckDataList = new ArrayList<>();
            List<Map<String,Object>> lodDataList ;
            Set<String> allUserIdSet = commensUtil.allUserIdByAppkey(token);
            if(allUserIdSet!=null&&allUserIdSet.size()>0){
                //进行用户和分页批处理
                Map<String, Object> logsmap;
                Iterator<String> it = allUserIdSet.iterator();
                Map<String, Object> checkresultmap;
                while (it.hasNext()) {
                    String str = it.next();
                    long  pageNo = 0 ;
                    long size = 20;
                    boolean k = true;
                    try {
                        while (k) {
                            //获取用户昨天的日志记录列表
                            logsmap = reportList(str,1,pageNo,size,token);
                            if(logsmap!=null&&logsmap.containsKey("errcode")&&Integer.valueOf(logsmap.get("errcode").toString()) == 0) {
                                checkresultmap = (Map) logsmap.get("result");
                                if(checkresultmap.containsKey("data_list")&&checkresultmap.get("data_list")!=null){
                                    lodDataList = (List) checkresultmap.get("data_list");
                                    if (lodDataList != null && lodDataList.size() > 0) {
                                        allCheckDataList.addAll(lodDataList);
                                        //下页有数据
                                        if(checkresultmap.containsKey("has_more")&&checkresultmap.get("has_more")!=null&&(boolean)checkresultmap.get("has_more")) {
                                            pageNo = Long.parseLong(String.valueOf(checkresultmap.get("next_cursor")));
                                        }else {
                                            k  = false;
                                        }
                                    }else {
                                        //出差、请假、公休的日期未写日报的，不予扣分
                                        //未取到日志的员工，自动扣取对应员工的日志行为积分
                                        k  = false;
                                    }

                                }else{
                                    //出差、请假、公休的日期未写日报的，不予扣分
                                    //未取到日志的员工，自动扣取对应员工的日志行为积分
                                    k  = false;
                                }
                            }else{
                                //出差、请假、公休的日期未写日报的，不予扣分
                                //未取到日志的员工，自动扣取对应员工的日志行为积分
                                k  = false;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }
                }
                return allCheckDataList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 导入日报数据
     *
     * @return
     */
    @Override
    public  Map<String,Object> importEveryDayReportData(String token){
        Map<String, Object> map = new HashMap<>();
        map.put("code",1);
        map.put("message","导入失败");
        List<Map<String,Object>> list = getAllUserReportByCompany(token);
        if(list!=null&&list.size()>0){
            try {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> reportMap = list.get(i);
                    String id  = UUIDS.getID();
                    // 对不符合该子公司的日志要求的（时间、格式）的日志自动标记为”异常”状态，记录不合格的原因。并自动扣取对应员工的日志行为积分
                    TReportRecord tRr = new TReportRecord();
                    tRr.setId(id);
                    tRr.setDeleted(0);
                    if(!MapUtils.mapIsAnyBlank(reportMap,"create_time")){
                        tRr.setCreateTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
                        tRr.setModifyTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
                        tRr.setReportTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
                    }
                    if(!MapUtils.mapIsAnyBlank(reportMap,"creator_id")){
                        tRr.setUserId(String.valueOf(reportMap.get("creator_id")));
                    }
                    if(!MapUtils.mapIsAnyBlank(reportMap,"contents")){
                        tRr.setContent(String.valueOf(reportMap.get("contents")));
                    }
                    if(!MapUtils.mapIsAnyBlank(reportMap,"report_id")){
                        tRr.setTemplateId(String.valueOf(reportMap.get("report_id")));
                    }
                    tRr.setStatus("");
                    //时间和要求均符合规则的日报的状态为“正常”
                    //日报数据存入“日报记录”表（t_report_record）中
                    int d = treportRecordMapper.insert(tRr);
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
