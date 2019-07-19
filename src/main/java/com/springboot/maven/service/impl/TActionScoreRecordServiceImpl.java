package com.springboot.maven.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TActionScoreRecord;
import com.springboot.maven.mapper.TActionScoreRecordMapper;
import com.springboot.maven.service.ITActionScoreRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行为积分变动记录 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TActionScoreRecordServiceImpl extends ServiceImpl<TActionScoreRecordMapper, TActionScoreRecord> implements ITActionScoreRecordService {

}
