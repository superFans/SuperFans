package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TClockinRecord;
import com.springboot.maven.mapper.TClockinRecordMapper;
import com.springboot.maven.service.ITClockinRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考勤打卡记录 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TClockinRecordServiceImpl extends ServiceImpl<TClockinRecordMapper, TClockinRecord> implements ITClockinRecordService {

}
