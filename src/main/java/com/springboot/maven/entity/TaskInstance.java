package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务实例
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaskInstance extends Model<TaskInstance> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 任务模板id
     */
    private String taskTemplateId;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 状态
     */
    private String status;

    /**
     * 重试次数
     */
    private Integer retryTime;

    /**
     * 错误原因
     */
    private String failReason;

    /**
     * 启动参数
     */
    private String params;

    /**
     * 最后执行时间
     */
    private Long lastExcuteTime;

    /**
     * 成功时间
     */
    private Long successTime;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 最后修改时间
     */
    private Long modifyTime;

    /**
     * 是否删除(0:未删,1:已删)
     */
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
