package  com.springboot.maven.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.springboot.maven.service.utlis.PublicMethods;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
            Set<String> allUserIdSet = PublicMethods.allUserIdByAppkey(token);
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
                                    //在数据集中添加用户名称
                                    Map<String, Object> userMap = PublicMethods.userinfoByUserId(str,token);
                                    if(!MapUtils.mapIsAnyBlank(userMap,"name")){
                                     String name = userMap.get("name").toString();
                                        for (int i = 0; i <checkDataList.size() ; i++) {
                                            Map<String, Object>  checkMaps = checkDataList.get(i);
                                            checkMaps.put("user_name",name);
                                        }
                                    }
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
                        if(!MapUtils.mapIsAnyBlank(checkMap,"user_name")){
                            tsr.setUserName(String.valueOf(checkMap.get("user_name")));
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
    public  Map<String,Object> importEveryDayCheckinData( String token, String agentId){
        List<TSigninRecord> list = checkin(100, token);
        if(list!=null&&list.size()>0){
            for (TSigninRecord trt : list){
                trt.setCompanyId(agentId);
                int  d =  tsigninrecordmapper.insert(trt);
                if(d>0){
                    Map<String, Object> map = new HashMap<>();
                    map.put("status","ok");
                }
            }
        }
        return null;
    }


    /**
     *
     *
     * 公司签到记录列表
     * @param request
     * @return
     */
    @Override
    public Map<String,Object> signinRuleListByCompany(HttpServletRequest request){

        try {
            long pages = Long.valueOf(request.getParameter("page"));
            long capacity = Long.valueOf(request.getParameter("capacity"));
            String company_id = request.getParameter("company_id");
            String app_key  = request.getParameter("app_key");
            String app_secret = request.getParameter("app_secret");

            String dept_id = request.getParameter("dept_id");

            String confirm = request.getParameter("confirm");
            String status = request.getParameter("status");
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String user_id = request.getParameter("user_id");
            QueryWrapper<TSigninRecord> queryWrapper = new QueryWrapper<>();
            Page<TSigninRecord> page = new Page<>(pages,capacity);
            if(StringUtils.isNotEmpty(company_id)){
                queryWrapper.lambda().eq(TSigninRecord::getCompanyId, company_id);
            }else{
                return  null;
            }
            if(StringUtils.isNotEmpty(start_time)){
                queryWrapper.lambda().eq(TSigninRecord::getCheckinTime, start_time);
            }
            if(StringUtils.isNotEmpty(end_time)){
                queryWrapper.lambda().eq(TSigninRecord::getCheckinTime, end_time);
            }
            if(StringUtils.isNotEmpty(status)){
                queryWrapper.lambda().eq(TSigninRecord::getStatus, status);
            }
            if(StringUtils.isNotEmpty(user_id)){
                queryWrapper.lambda().eq(TSigninRecord::getUserId, user_id);
            }
            if(StringUtils.isNotEmpty(confirm)){
                queryWrapper.lambda().eq(TSigninRecord::getConfirm, confirm);
            }
            queryWrapper.lambda().orderByAsc(TSigninRecord::getCreateTime);
            IPage<TSigninRecord> singRule = tsigninrecordmapper.selectPage(page, queryWrapper);
            if(singRule==null){
                return  null;
            }
            Map<String, Object> map = new HashMap<>(16);
            map.put("data", singRule);
            System.out.println(singRule.toString());
            return   map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

































}
