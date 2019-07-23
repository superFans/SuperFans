package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOperationLog extends Model<TOperationLog> {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 操作人员id
     */
    private String operatorUserId;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作详情json
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 最后修改时间
     */
    private LocalDate modifyTime;

    /**
     * 是否删除(0:未删,1:已删)
     */
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
