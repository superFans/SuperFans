package  com.springboot.maven.service.impl;
import com.springboot.maven.entity.TSigninRule;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.mapper.TReportRuleMapper;
import com.springboot.maven.service.ITReportRuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志规则 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TReportRuleServiceImpl extends ServiceImpl<TReportRuleMapper, TSigninRule> implements ITReportRuleService {

}
