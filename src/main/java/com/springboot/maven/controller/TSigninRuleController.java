package  com.springboot.maven.controller;


import com.springboot.maven.service.ITSigninRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 签到规则 前端控制器
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Controller
@RequestMapping("/api/v1")
public class TSigninRuleController {

    @Autowired
    ITSigninRuleService itSigninRuleService;


    /**
     *
     *
     * 新增签到规则
     * @param req
     * @return
     */
    @PostMapping("/signin_rule/signin_rule_add")
    public Map<String, Object> insertSingRule(HttpServletRequest req) {
        return itSigninRuleService.insertSingRule(req);
    }
    /**
     *
     *
     * s删除签到规则
     * @param request
     * @return
     */
    @GetMapping("/signin_rule/signin_rule_delete/{id}")
    public Map<String, Object> deleteSingRule(HttpServletRequest request) {
        return itSigninRuleService.deleteSingRule(request.getParameter("id"));
    }
    /**
     *
     *
     * 部门签到规则列表
     * @param request
     * @return
     */
    @GetMapping("/signin_rule/signin_rule_modify")
    public Map<String, Object> signinRuleListByDeptId(HttpServletRequest request) {
        return itSigninRuleService.signinRuleListByDeptId(request.getParameter("dept_id"));
    }



}

