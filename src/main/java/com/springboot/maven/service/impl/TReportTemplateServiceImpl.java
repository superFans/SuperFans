package  com.springboot.maven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TReportTemplate;
import com.springboot.maven.mapper.TReportTemplateMapper;
import com.springboot.maven.service.ITReportTemplateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志模板 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TReportTemplateServiceImpl extends ServiceImpl<TReportTemplateMapper, TReportTemplate> implements ITReportTemplateService {

}
