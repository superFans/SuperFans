package  com.springboot.maven.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TAttendanceRecord;
import com.springboot.maven.mapper.TAttendanceRecordMapper;
import com.springboot.maven.service.ITAttendanceRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TAttendanceRecordServiceImpl extends ServiceImpl<TAttendanceRecordMapper, TAttendanceRecord> implements ITAttendanceRecordService {

}
