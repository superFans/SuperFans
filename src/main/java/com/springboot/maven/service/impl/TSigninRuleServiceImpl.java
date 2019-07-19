package  com.springboot.maven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TSigninRule;
import com.springboot.maven.mapper.TSigninRuleMapper;
import com.springboot.maven.service.ITSigninRuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到规则 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TSigninRuleServiceImpl extends ServiceImpl<TSigninRuleMapper, TSigninRule> implements ITSigninRuleService {

}
