package  com.springboot.maven.controller;


import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.utlis.commensUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志记录 前端控制器
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Controller
@RequestMapping("/tReportRecord")
public class TReportRecordController {
    @Autowired
    private ITReportRecordService itReportRecordService;
    private static final String appkey  = "ding9p7vzgvgrx3tvd6a";
    private static final String appsecret= "nGKknhri4XwBjcLxkZFacafx10_k67dUT6B09kJF50xLBrwb9AULlVQxcI4L0W3W";
    private static final String AgentId = "276194902";

    /**
     * 获取子公司每天所有用户日志记录
     *
     * @return
     */
    @GetMapping("/getReport")
    public List<Map<String, Object>> getAllUserReportByCompany() {
        return itReportRecordService.getAllUserReportByCompany(commensUtil.token(appkey,appsecret));
    }

    /**
     * 导入子公司每天所有用户日志记录
     * size :每页条数分页查询的每页大小，最大100
     * @return
     */
    @GetMapping("/importReport")
    public Map<String,Object> importEveryDayReportData() {
        return itReportRecordService.importEveryDayReportData(commensUtil.token(appkey,appsecret));
    }




}

