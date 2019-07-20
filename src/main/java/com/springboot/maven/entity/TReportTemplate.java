package com.springboot.maven.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志模板
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TReportTemplate extends Model<TReportTemplate> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId("数据库字段名称")
    private String id;

    /**
     * 分公司id
     */
    private String companyId;

    /**
     * 日志模板标识
     */
    private String code;

    /**
     * 日志模板名
     */
    private String name;

    /**
     * 各个公司的同一种模板，比如"门店总日报"group是相同的，之间的规则可以复制
     */
    private String group;

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
