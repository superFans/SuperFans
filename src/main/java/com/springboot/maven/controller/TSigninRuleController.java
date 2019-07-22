package  com.springboot.maven.controller;


import com.aliyun.api.internal.parser.json.ObjectJsonParser;
import com.springboot.maven.service.ITSigninRuleService;
import com.springboot.maven.service.utlis.commensUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
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
@RequestMapping("/tSigninRule")
public class TSigninRuleController {

    @Autowired
    ITSigninRuleService itSigninRuleService;


    /**
     *
     *
     * 新增签到规则
     * @param map
     * @return
     */
    @PostMapping("/create_sing_rule")
    public Map<String, Object> insertSingRule(@RequestBody Map<String, Object> map) {
        return itSigninRuleService.insertSingRule(map);
    }
    /**
     *
     *
     * s删除签到规则
     * @param request
     * @return
     */
    @GetMapping("/delete_sing_rule{id}")
    public Map<String, Object> deleteSingRule(HttpServletRequest request) {
        return itSigninRuleService.deleteSingRule(request.getAttribute("id").toString());
    }




}

