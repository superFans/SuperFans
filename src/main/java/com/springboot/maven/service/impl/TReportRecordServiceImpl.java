package  com.springboot.maven.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.response.OapiReportListResponse;
import com.springboot.maven.entity.TReportRecord;
import com.springboot.maven.mapper.TReportRecordMapper;
import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.utlis.commensUtil;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

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



    public static Map<String, Object> stringToMap(String jsonString) {
        new HashMap();

        try {
            JSONObject jbo = JSONObject.parseObject(jsonString);
            return jbo;
        } catch (Exception var3) {
            return null;
        }
    }
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
    public Map<String, Object> reportList(String userid , long duration , long pageNo , long size) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/list");
        OapiReportListRequest request = new OapiReportListRequest();
        request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(duration));
        request.setEndTime(System.currentTimeMillis());
        request.setSize(size);
        request.setCursor(pageNo);
        request.setUserid(userid);
        try {
            OapiReportListResponse response = client.execute(request, commensUtil.token());
            Map<String, Object> rsmap = stringToMap(response.getBody());
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

    public List<Map<String,Object>> getAllUserLoggingByCompany(long  size) {
        try {
            List<Map<String,Object>> allCheckDataList = new ArrayList<>();
            List<Map<String,Object>> lodDataList ;
            Set<String> allUserIdSet = commensUtil.allUserIdByAppkey(commensUtil.token());
            if(allUserIdSet!=null&&allUserIdSet.size()>0){
                //进行用户和分页批处理
                Map<String, Object> logsmap;
                Iterator<String> it = allUserIdSet.iterator();
                Map<String, Object> checkresultmap;
                while (it.hasNext()) {
                    String str = it.next();
                    long  pageNo = 0 ;
                    boolean k = true;
                    while (k) {
                        logsmap = reportList(str,1,pageNo,size);//获取用户签到记录列表
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
                }
                return allCheckDataList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
