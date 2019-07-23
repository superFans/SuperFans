package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行为积分变动记录
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TActionScoreRecord extends Model<TActionScoreRecord> {

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
     * 员工姓名
     */
    private String userName;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 来源，行为奖罚，迟到，缺卡，等
     */
    private String source;

    /**
     * 关联的目标的编号
     */
    private String targetId;

    /**
     * 加项/减项
     */
    private String type;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 备注
     */
    private String remark;

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
