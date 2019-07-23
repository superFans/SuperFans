package com.springboot.maven.service.utlis;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetDeptMemberRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetDeptMemberResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.taobao.api.ApiException;

import java.text.SimpleDateFormat;
import java.util.*;





public class PublicMethods {

    private static final String appkey  = "ding9p7vzgvgrx3tvd6a";
    private static final String appsecret= "nGKknhri4XwBjcLxkZFacafx10_k67dUT6B09kJF50xLBrwb9AULlVQxcI4L0W3W";
    private static final String AgentId = "276194902";

    public static Map<String, Object> stringToMap(String jsonString) {
        new HashMap();

        try {
            JSONObject jbo = JSONObject.parseObject(jsonString);
            return jbo;
        } catch (Exception var3) {
            return null;
        }
    }
    public static String token  (String appkey , String appsecret){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appkey);
        request.setAppsecret(appsecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return response.getAccessToken();
    }

    /**
     * @Title:获取公司所有员工id集合
     */
    public static Set<String> allUserIdByAppkey(String token ) {
        try {
            Map<String, Object> dep = allDepartment(token);//子公司所有部门的id集合
            Set<String> allUserIdSet = new HashSet<>();
            if (dep.containsKey("department") && dep.get("department") != null) {
                List<Map<String, Object>> listdep = (List) dep.get("department");
                try {
                    for (int i = 0; i < listdep.size(); i++) {
                        Map<String, Object> listmap = listdep.get(i);
                        if (listmap != null && listmap.containsKey("id") && listmap.get("id") != null) {
                            String id = String.valueOf(listmap.get("id"));//部门ID
                            List<String> userIdListByDep = allUserIdByDeptId(id,token);//获取部门用户userid列表
                            if (userIdListByDep != null && userIdListByDep.size() > 0) {
                                allUserIdSet.addAll(userIdListByDep);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
            return  allUserIdSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取子部门ID列表
     * @return
     */
    public static Map<String, Object> allDepartment(String token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListIdsRequest request = new OapiDepartmentListIdsRequest();
        request.setId("1");
        request.setHttpMethod("GET");
        try {
            OapiDepartmentListIdsResponse response = client.execute(request, token);
            Map<String, Object> rsmap = stringToMap(response.getBody());
            return rsmap;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取部门用户userid列表
     * @return
     */
    public static List<String>  allUserIdByDeptId(String deptId , String token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getDeptMember");
        OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
        req.setDeptId(deptId);
        req.setHttpMethod("GET");
        try {
            OapiUserGetDeptMemberResponse rsp = client.execute(req, token);
            Map<String, Object> rsmap = stringToMap(rsp.getBody());
            if(rsmap.containsKey("userIds")&&rsmap.get("userIds")!=null){
                List<String> userList = (List) rsmap.get("userIds");
                return userList;
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /**
     * 获取用户信息
     * @return
     */
    public static Map<String, Object> userinfoByUserId(String userId , String token) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        try {
            OapiUserGetResponse response = client.execute(request, token);
            Map<String, Object> rsmap = stringToMap(response.getBody());
            if(!MapUtils.mapIsAnyBlank(rsmap,"errcode")){
                return rsmap;
            }
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static String formatDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static void main(String[] args) {
        System.out.println(allUserIdByAppkey("a5fff405f35c3d75b7f75031e7eaad8a"));
    }
}
