package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TExamine;
import com.springboot.maven.mapper.TExamineMapper;
import com.springboot.maven.service.ITExamineService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批表单 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TExamineServiceImpl extends ServiceImpl<TExamineMapper, TExamine> implements ITExamineService {

}
