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
import com.springboot.maven.service.utlis.MapUtils;
import com.springboot.maven.service.utlis.UUIDS;
import com.springboot.maven.service.utlis.commensUtil;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.From;
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
    public Map<String, Object> checkinUser(String useridList , long duration , long pageNo , long size ,String token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/checkin/record/get");
        OapiCheckinRecordGetRequest request = new OapiCheckinRecordGetRequest();
        request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(duration));
        request.setEndTime(System.currentTimeMillis());
        request.setSize(size);
        request.setCursor(pageNo);
        request.setUseridList(useridList);
        try {
            OapiCheckinRecordGetResponse response = client.execute(request, token);
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
    public  List<Map<String,Object>> checkinListByUserList(long size , String token) {
        try {
            List<Map<String, Object>> allCheckDataList = new ArrayList<>();
            List<Map<String, Object>> checkDataList;
            Set<String> allUserIdSet = commensUtil.allUserIdByAppkey(token);
            if (allUserIdSet != null && allUserIdSet.size() > 0) {
                //进行用户和分页批处理
                Iterator<String> it = allUserIdSet.iterator();
                while (it.hasNext()) {
                    long pageNo = 0;
                    String str = it.next();
                    boolean k = true;
                    while (k) {
                        //获取用户签到记录列表
                        Map<String, Object> checkMap = checkinUser(str, 1, pageNo, size, token);
                        if (!MapUtils.mapIsAnyBlank(checkMap, "result")) {
                            Map<String, Object> resultMap = (Map) checkMap.get("result");
                            if (!MapUtils.mapIsAnyBlank(resultMap, "page_list")) {
                                checkDataList = (List) resultMap.get("page_list");
                                if (checkDataList != null && checkDataList.size() > 0) {
                                    allCheckDataList.addAll(checkDataList);
                                    //下页有数据
                                    if (!MapUtils.mapIsAnyBlank(resultMap, "next_cursor") && !"".equals(resultMap.get("next_cursor"))) {
                                        pageNo = Long.parseLong(resultMap.get("next_cursor").toString());
                                    } else {
                                        k = false;
                                    }
                                } else {
                                    k = false;
                                }
                            } else {
                                k = false;
                            }
                        } else {
                            k = false;
                        }
                    }
                }
                return allCheckDataList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }



    /**
     * 用户签到记录封装成实体类集合
     * @return
     */
    @Override
    public List<TSigninRecord> checkin(long  size , String token) {
        try {
            List<TSigninRecord> allCheckVoList = new ArrayList<>();
            List<Map<String,Object>> allCheckDataList =  checkinListByUserList(size, token);
                //封装成实体类集合
                if(allCheckDataList!=null&&allCheckDataList.size()>0){
                    for (int i = 0; i < allCheckDataList.size(); i++) {
                        TSigninRecord tsr = new TSigninRecord();
                        String id  = UUIDS.getID();
                        tsr.setId(id);
                        tsr.setStatus("NEW");
                        tsr.setDeleted(0);
                        Map<String, Object> checkMap = allCheckDataList.get(i);
                        if(!MapUtils.mapIsAnyBlank(checkMap,"checkin_time")){
                            tsr.setCheckinTime(Long.valueOf(String.valueOf(checkMap.get("checkin_time"))));
                            tsr.setCreateTime(Long.valueOf(String.valueOf(checkMap.get("checkin_time"))));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"detail_place")){
                            tsr.setDetailPlace(String.valueOf(checkMap.get("detail_place")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"image_list")){
                            tsr.setImageList(String.valueOf(checkMap.get("image_list")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"latitude")){
                            tsr.setLatitude(String.valueOf(checkMap.get("latitude")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"longitude")){
                            tsr.setLatitude(String.valueOf(checkMap.get("longitude")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"place")){
                            tsr.setPlace(String.valueOf(checkMap.get("place")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"remark")){
                            tsr.setRemark(String.valueOf(checkMap.get("remark")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"userid")){
                            tsr.setUserId(String.valueOf(checkMap.get("userid")));
                        }
                        if(!MapUtils.mapIsAnyBlank(checkMap,"visit_user")){
                            tsr.setVisitUser(String.valueOf(checkMap.get("visit_user")));
                        }
                        allCheckVoList.add(tsr);
                    }
                    return allCheckVoList;
                }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 导入签到数据
     *
     * @return
     */
    @Override
    public  Map<String,Object> importEveryDayCheckinData( String token){
        List<TSigninRecord> list = checkin(100, token);
        if(list!=null&&list.size()>0){
            for (TSigninRecord trt : list){
                int  d =  tsigninrecordmapper.insert(trt);
                if(d>0){
                    Map<String, Object> map = new HashMap<>();
                    map.put("status","ok");
                }
            }
        }
        return null;
    }
}
