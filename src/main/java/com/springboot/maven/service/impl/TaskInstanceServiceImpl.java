package com.springboot.maven.service.impl;

import com.springboot.maven.entity.TaskInstance;
import com.springboot.maven.mapper.TaskInstanceMapper;
import com.springboot.maven.service.ITaskInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务实例 服务实现类
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Service
public class TaskInstanceServiceImpl extends ServiceImpl<TaskInstanceMapper, TaskInstance> implements ITaskInstanceService {

}
