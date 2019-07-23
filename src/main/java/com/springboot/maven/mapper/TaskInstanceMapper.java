package com.springboot.maven.mapper;

import com.springboot.maven.entity.TaskInstance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 任务实例 Mapper 接口
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Repository
public interface TaskInstanceMapper extends BaseMapper<TaskInstance> {

}
