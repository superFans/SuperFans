package com.springboot.maven.service;

import com.springboot.maven.entity.TSigninRule;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 签到规则 服务类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
public interface ITSigninRuleService extends IService<TSigninRule> {
   /**
     *
     *
     * 新增签到规则
     * @param req
     * @return
     */
    Map<String,Object> insertSingRule (HttpServletRequest req);

    /**
     *
     *
     * 删除签到规则（可以删除多条）
     * @param id
     * @return
     */
    Map<String,Object> deleteSingRule (String id);

    /**
     *
     *
     * 修改签到规则
     * @param req
     * @return
     */
    Map<String,Object> updateSingRule (HttpServletRequest req);


    /**
     *
     *
     * 部门签到规则列表
     * @param id
     * @return
     */
    Map<String,Object> signinRuleListByDeptId (String id);








    }
