package com.springboot.maven.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class TExamineBusinessTravel extends Model<TExamineBusinessTravel> {

    private static final long serialVersionUID=1L;

    private String id;

    private String userId;

    private String userName;

    private String companyId;

    private String processCode;

    private String processInstanceId;

    private String processTitle;

    private Long processCreateTime;

    private Long processFinishTime;

    private String processStatus;

    private String processResult;

    private String formValueCompany;

    private String formValueReason;

    private String formValueFromCity;

    private String formValueToCity;

    private Long formValueStartTime;

    private Long formValueFinishTime;

    private String formValueStartTransport;

    private String formValueFinishTransport;

    private String formValueHotelType;

    private String formValueRemark;

    private Long createTime;

    private Long modifyTime;

    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
