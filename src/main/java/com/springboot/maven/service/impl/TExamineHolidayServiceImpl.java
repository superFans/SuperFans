package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TExamineHoliday;
import com.springboot.maven.mapper.TExamineHolidayMapper;
import com.springboot.maven.service.ITExamineHolidayService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公休假 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TExamineHolidayServiceImpl extends ServiceImpl<TExamineHolidayMapper, TExamineHoliday> implements ITExamineHolidayService {

}
