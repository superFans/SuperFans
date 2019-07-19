package com.springboot.maven.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.maven.entity.TSigninRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 签到记录 Mapper 接口
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Repository
public interface TSigninRecordMapper extends BaseMapper<TSigninRecord> {

}
