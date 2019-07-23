package com.springboot.maven.controller;


import com.springboot.maven.service.ITSigninRecordService;
import com.springboot.maven.service.utlis.PublicMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * <p>
 * 签到记录 前端控制器
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Controller
@RequestMapping
public class TSigninRecordController {

    @Autowired
    private ITSigninRecordService itSigninRecordService;

    private static final String appkey  = "ding9p7vzgvgrx3tvd6a";
    private static final String appsecret= "nGKknhri4XwBjcLxkZFacafx10_k67dUT6B09kJF50xLBrwb9AULlVQxcI4L0W3W";
    private static final String AgentId = "276194902";
    @GetMapping
    public String testEnum() {
       /* List<TSigninRecord> users =  itSigninRecordService.list();*/
        return "sdfafasf";
    }
    /**
     * 签到
     * size :每页条数分页查询的每页大小，最大100
     * @return
     */
    @GetMapping("/checkin")
    public  Map<String, Object> checkin11() {
        return itSigninRecordService.checkinUser("",1,0,10, PublicMethods.token(appkey,appsecret));
    }
    /**
     * @Title:导入分公司下的所有签到记录
     */
    @GetMapping("/importSigninRecord")
    public Map<String, Object> importreportTemplate() {
        return itSigninRecordService.importEveryDayCheckinData(PublicMethods.token(appkey,appsecret), AgentId);
    }
    /**
     *
     *
     * 分公司下的签到记录列表
     * @param request
     * @return
     */
    @PostMapping("/signin_rule/signin_rule_list")
    public Map<String, Object> signinRuleListByCompany(HttpServletRequest request) {
        return itSigninRecordService.signinRuleListByCompany(request);
    }


}

