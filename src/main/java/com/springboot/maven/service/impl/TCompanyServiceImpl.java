package  com.springboot.maven.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TCompany;
import com.springboot.maven.mapper.TCompanyMapper;
import com.springboot.maven.service.ITCompanyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分公司 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TCompanyServiceImpl extends ServiceImpl<TCompanyMapper, TCompany> implements ITCompanyService {

}
