package com.springboot.maven.service.impl;

import com.springboot.maven.entity.TaskTemplate;
import com.springboot.maven.mapper.TaskTemplateMapper;
import com.springboot.maven.service.ITaskTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务模板 服务实现类
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Service
public class TaskTemplateServiceImpl extends ServiceImpl<TaskTemplateMapper, TaskTemplate> implements ITaskTemplateService {

}
