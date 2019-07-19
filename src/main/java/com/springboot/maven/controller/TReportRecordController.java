package  com.springboot.maven.controller;


import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.utlis.commensUtil;
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


    /**
     * 获取子公司每天所有用户日志记录
     *
     * @return
     */
    @GetMapping("/getReport")
    public List<Map<String, Object>> checkin11() {
        return itReportRecordService.getAllUserReportByCompany(commensUtil.token());
    }

    /**
     * 导入子公司每天所有用户日志记录
     * size :每页条数分页查询的每页大小，最大100
     * @return
     */
    @GetMapping("/importReport")
    public Map<String,Object> importEveryDayReportData() {
        return itReportRecordService.importEveryDayReportData(commensUtil.token());
    }


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
    public List<Map<String, Object>> importEveryDayReportData() {
        return itReportRecordService.reportTemplateList(null,commensUtil.token());
    }
    /**
     * @Title:导入企业的所有模板
     */

}

