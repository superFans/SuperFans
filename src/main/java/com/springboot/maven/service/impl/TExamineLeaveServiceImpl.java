package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TExamineLeave;
import com.springboot.maven.mapper.TExamineLeaveMapper;
import com.springboot.maven.service.ITExamineLeaveService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请假 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TExamineLeaveServiceImpl extends ServiceImpl<TExamineLeaveMapper, TExamineLeave> implements ITExamineLeaveService {

}
