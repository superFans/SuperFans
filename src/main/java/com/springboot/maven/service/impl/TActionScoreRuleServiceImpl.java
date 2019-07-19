package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TActionScoreRule;
import com.springboot.maven.mapper.TActionScoreRuleMapper;
import com.springboot.maven.service.ITActionScoreRuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行为积分的规则 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TActionScoreRuleServiceImpl extends ServiceImpl<TActionScoreRuleMapper, TActionScoreRule> implements ITActionScoreRuleService {

}
