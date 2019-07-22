package com.springboot.maven.service;

import com.springboot.maven.entity.TSigninRule;

import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param map
     * @return
     */
    Map<String,Object> insertSingRule (Map<String,Object> map);

    /**
     *
     *
     * 删除签到规则
     * @param id
     * @return
     */
    Map<String,Object> deleteSingRule (String id);

}
