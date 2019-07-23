package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分公司
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TCompany extends Model<TCompany> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 公司名
     */
    private String name;

    /**
     * 应用id
     */
    private String agentId;

    /**
     * 应用key
     */
    private String appKey;

    /**
     * 应用密钥
     */
    private String appSecret;

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
