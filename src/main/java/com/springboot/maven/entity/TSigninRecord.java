package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 签到记录
 * </p>
 *
 * @author fans
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSigninRecord extends Model<TSigninRecord> {

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
     * 图片url数组
     */
    private String imageList;

    /**
     * 地址
     */
    private String place;

    /**
     * 详细地址
     */
    private String detailPlace;

    /**
     * 备注
     */
    private String remark;

    /**
     * 签到时间
     */
    private Long checkinTime;

    /**
     * 拜访客户
     */
    private String visitUser;

    /**
     * 纬度
     */
    private String longitude;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 状态：NEW/VALID/INVALID
     */
    private String status;

    /**
     * 是否人工确认过(0:否,1:是)
     */
    private Integer confirm;

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
