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
 * @author dancer
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TAttendanceRecord extends Model<TAttendanceRecord> {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 员工id
     */
    private Integer userId;

    /**
     * 月份yyyy-MM
     */
    private String month;

    /**
     * 未打卡次数
     */
    private Integer notSignedCount;

    /**
     * 早退次数
     */
    private Integer earlyCount;

    /**
     * 迟到次数
     */
    private Integer lateCount;

    /**
     * 旷工天数
     */
    private String absenteeismDays;

    /**
     * 计薪天数
     */
    private String salaryDays;

    /**
     * 实际出勤天数
     */
    private String attendanceDays;

    /**
     * 出差天数
     */
    private String businessTravelDays;

    /**
     * 公休天数
     */
    private String holidayDays;

    /**
     * 病假天数
     */
    private String sickLeaveDays;

    /**
     * 事假天数
     */
    private String personalLeaveDays;

    /**
     * 产假天数
     */
    private String childbirthLeaveDays;

    /**
     * 是否全勤(0:否,1:是)
     */
    private Integer fullAttendance;

    /**
     * 积分变动数
     */
    private Integer score;

    /**
     * 日报不合格数
     */
    private Integer reportLowQualityCount;

    /**
     * 员工是否确认(0:未确认,1:已确认)
     */
    private Integer userConfirm;

    /**
     * 员工确认时间
     */
    private Long userConfirmTime;

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
