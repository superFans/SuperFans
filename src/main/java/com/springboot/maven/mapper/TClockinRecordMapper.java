package com.springboot.maven.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.maven.entity.TClockinRecord;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 考勤打卡记录 Mapper 接口
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Repository
public interface TClockinRecordMapper extends BaseMapper<TClockinRecord> {

}
