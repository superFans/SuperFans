package  com.springboot.maven.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportListResponse;
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
     * 获取子公司每天所有用户日志记录
     * @return
     */
    @Override
    public List<Map<String,Object>> getAllUserReportByCompany(String token) {
        try {
            List<Map<String,Object>> allCheckDataList = new ArrayList<>();
            List<Map<String,Object>> lodDataList ;
            Set<String> allUserIdSet = commensUtil.allUserIdByAppkey(token);
            /*Set<String> allUserIdSet = new HashSet<>();
            allUserIdSet.add("243653281433078787");
            allUserIdSet.add("283723342836231475");*/
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
                            logsmap = reportList(str,1,pageNo,size,token);//获取用户签到记录列表
                            if(logsmap!=null&&logsmap.containsKey("errcode")&&Integer.valueOf(logsmap.get("errcode").toString()) == 0) {
                                checkresultmap = (Map) logsmap.get("result");
                                if(checkresultmap.containsKey("data_list")&&checkresultmap.get("data_list")!=null){
                                    lodDataList = (List) checkresultmap.get("data_list");
                                    if (lodDataList != null && lodDataList.size() > 0) {
                                        allCheckDataList.addAll(lodDataList);
                                        if(checkresultmap.containsKey("has_more")&&checkresultmap.get("has_more")!=null&&(boolean)checkresultmap.get("has_more")) {//下页有数据
                                            pageNo = Long.parseLong(String.valueOf(checkresultmap.get("next_cursor")));
                                        }else {
                                            k  = false;
                                        }
                                    }else {
                                        k  = false;
                                    }

                                }else{
                                    k  = false;
                                }
                            }else{
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
        List<Map<String,Object>> list = getAllUserReportByCompany(token);
        if(list!=null&&list.size()>0){
            try {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> reportMap = list.get(i);
                    String id  = UUIDS.getID();
                    TReportRecord tRr = new TReportRecord();
                    tRr.setId(id);
                    tRr.setDeleted(0);
                    if(!MapUtils.mapIsAnyBlank(reportMap,"create_time")){
                        tRr.setCreateTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
                        tRr.setModifyTime(Long.valueOf(String.valueOf(reportMap.get("create_time"))));
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
                    int d = treportRecordMapper.insert(tRr);
                    if(d>0){
                        Map<String, Object> map = new HashMap<>();
                        map.put("status","ok");
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
















}
