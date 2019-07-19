package com.springboot.maven.service;

import com.springboot.maven.entity.TSigninRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 签到记录 服务类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
public interface ITSigninRecordService extends IService<TSigninRecord> {

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
    Map<String, Object> checkinUser(String useridList , long duration , long pageNo , long size);


    /**
     * 获取子公司每天所有用户签到记录
     * @return
     */
    List<Map<String, Object>> checkin(long size);

    /**
     * 导入签到数据
     *
     * @return
     */
    Map<String,Object> importEveryDayCheckinData(List<Map<String, Object>> list);
}
