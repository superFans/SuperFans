package  com.springboot.maven.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCheckinRecordGetRequest;
import com.dingtalk.api.response.OapiCheckinRecordGetResponse;
import com.springboot.maven.entity.TSigninRecord;
import com.springboot.maven.mapper.TSigninRecordMapper;
import com.springboot.maven.service.ITSigninRecordService;
import com.springboot.maven.service.utlis.UUIDS;
import com.springboot.maven.service.utlis.commensUtil;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 签到记录 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TSigninRecordServiceImpl extends ServiceImpl<TSigninRecordMapper, TSigninRecord> implements ITSigninRecordService {

    @Autowired
    TSigninRecordMapper  tsigninrecordmapper;
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
     * @Title:获取用户签到记录列表
     * @param:
     * useridList 需要查询的用户列表，最大列表长度：10（如"1226682231742708,zhansan"）
     * duration 截止当前系统时间几天前的签到数据（如：1 一天前)
     * pageNo 分页查询的游标，最开始可以传0，然后以1、2依次递增
     * size 分页查询的每页大小，最大100
     * @return  List<Map<String, Object>>
     * @author: FSS
     */
    @Override
    public Map<String, Object> checkinUser(String useridList , long duration , long pageNo , long size) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/checkin/record/get");
        OapiCheckinRecordGetRequest request = new OapiCheckinRecordGetRequest();
        request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(duration));
        request.setEndTime(System.currentTimeMillis());
        request.setSize(size);
        request.setCursor(pageNo);
        request.setUseridList(useridList);
        try {
            OapiCheckinRecordGetResponse response = client.execute(request, commensUtil.token());
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
     * 获取子公司每天所有用户签到记录
     * @return
     */

    public List<Map<String,Object>> checkin(long  size ) {
        try {
            List<Map<String,Object>> allCheckDataList = new ArrayList<>();
            List<Map<String,Object>> checkDataList ;
            Set<String> allUserIdSet = commensUtil.allUserIdByAppkey(commensUtil.token());
            if(allUserIdSet!=null&&allUserIdSet.size()>0){
                //进行用户和分页批处理
                Iterator<String> it = allUserIdSet.iterator();
                Map<String, Object> checkinrsmap;
                Map<String, Object> checkresultmap;
                while (it.hasNext()) {
                    long  pageNo = 0 ;
                    String str = it.next();
                    boolean k = true;
                    while (k) {
                        checkinrsmap = checkinUser(str,1,pageNo,size);//获取用户签到记录列表
                        if(checkinrsmap!=null&&checkinrsmap.containsKey("errcode")&&Integer.valueOf(checkinrsmap.get("errcode").toString()) == 0) {
                            checkresultmap = (Map) checkinrsmap.get("result");
                            if(checkresultmap.containsKey("page_list")&&checkresultmap.get("page_list")!=null){
                                checkDataList = (List) checkresultmap.get("page_list");
                                if (checkDataList != null && checkDataList.size() > 0) {
                                    allCheckDataList.addAll(checkDataList);
                                    if(checkresultmap.containsKey("next_cursor")&&checkresultmap.get("next_cursor")!=null&&!"".equals(checkresultmap.get("next_cursor"))) {//下页有数据
                                        pageNo = Long.parseLong(checkresultmap.get("next_cursor").toString());
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


    /**
     * 导入签到数据
     *
     * @return
     */
    @Override
    public  Map<String,Object> importEveryDayCheckinData(List<Map<String,Object>> list){

        if(list!=null&&list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> checkMap = list.get(i);
                String id  = UUIDS.getID();
                checkMap.put("id", id);
                TSigninRecord tsr = new TSigninRecord();
                int  d =  tsigninrecordmapper.insert((TSigninRecord)checkMap);
                if(d>0){
                    Map<String, Object> map = new HashMap<>();
                    map.put("status","ok");
                }
            }
        }
        return null;
    }
}
