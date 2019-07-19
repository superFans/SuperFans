package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 签到规则
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSigninRule extends Model<TSigninRule> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 分公司id
     */
    private String companyId;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 中心纬度
     */
    private String centerLng;

    /**
     * 中心经度
     */
    private String centerLat;

    /**
     * 合法距离
     */
    private Integer distance;

    /**
     * 签到开始时间
     */
    private Long startTime;

    /**
     * 签到结束时间
     */
    private Long finishTime;

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
