package  com.springboot.maven.controller;

import com.springboot.maven.service.ITReportTemplateService;
import com.springboot.maven.service.utlis.PublicMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志模板 前端控制器
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Controller
@RequestMapping("/tReportTemplate")
public class TReportTemplateController {
    private static final String appkey  = "ding9p7vzgvgrx3tvd6a";
    private static final String appsecret= "nGKknhri4XwBjcLxkZFacafx10_k67dUT6B09kJF50xLBrwb9AULlVQxcI4L0W3W";
    private static final String AgentId = "276194902";
    @Autowired
    ITReportTemplateService itReportTemplateService;
    /**
     * @Title:获取企业的所有模板
     * @param:
     * userid 可选，不传表示获取企业的所有模板
     * offset 分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     * size 分页查询的每页大小，最大100
     * @return  List<Map<String, Object>>
     * @author: FSS
     */
    @GetMapping("/getReportTemplate")
    public List<Map<String, Object>> reportTemplateList() {
        return itReportTemplateService.reportTemplateList(null, PublicMethods.token(appkey,appsecret));
    }
    /**
     * @Title:导入企业的所有模板
     */
    @GetMapping("/importReportTemplate")
    public Map<String, Object> importreportTemplate() {
        return itReportTemplateService.importReportTemplateData(PublicMethods.token(appkey,appsecret),AgentId);
    }
}

