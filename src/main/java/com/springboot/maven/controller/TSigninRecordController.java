package com.springboot.maven.controller;


import com.alibaba.fastjson.JSONObject;
import com.springboot.maven.entity.TSigninRecord;
import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.ITSigninRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;
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
    @Autowired
    ITReportRecordService itReportRecordService;


    @GetMapping
    public JSONObject testEnum() {
       /* List<TSigninRecord> users =  itSigninRecordService.list();*/
        JSONObject result = new JSONObject();
        result.put("users", "");
        return result;
    }
    /**
     * 签到
     * size :每页条数分页查询的每页大小，最大100
     * @return
     */
    @GetMapping("/checkin")
    public List<Map<String,Object>> checkin11() {
        return itSigninRecordService.checkin(5);
    }

    /**
     * 日志
     * size :每页条数分页查询的每页大小，最大20
     * @return
     */
    @GetMapping("/log")
    public List<Map<String,Object>>  log() {
        return itReportRecordService.getAllUserLoggingByCompany(2);
    }
}

