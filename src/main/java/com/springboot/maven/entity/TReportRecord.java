package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志记录
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TReportRecord extends Model<TReportRecord> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 员工id
     */
    private String userId;

    /**
     * 日志模板id
     */
    private String templateId;

    /**
     * 日志内容,[{'key':'今日任务','type':1,'value':'好啊好啊'},{'key':'明日任务','type':1'value':'明天去外地'}]
     */
    private String content;

    /**
     * 状态
     */
    private String status;

    /**
     * 不合格的原因
     */
    private String unqualifiedReason;

    private Long reportTime;

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
