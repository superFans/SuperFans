package com.springboot.maven.oa;

import com.springboot.maven.service.ITReportRecordService;
import com.springboot.maven.service.utlis.commensUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MavenApplicationTests {
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
	@Test
	public List<Map<String, Object>> getAllUserReportByCompany() {
		return itReportRecordService.getAllUserReportByCompany(commensUtil.token(appkey,appsecret));
	}

	/**
	 * 导入子公司每天所有用户日志记录
	 * size :每页条数分页查询的每页大小，最大100
	 * @return
	 */
	@Test
	public Map<String,Object> importEveryDayReportData() {
		return itReportRecordService.importEveryDayReportData(commensUtil.token(appkey,appsecret));
	}
	@Test
	public void contextLoads() {


	}

}
