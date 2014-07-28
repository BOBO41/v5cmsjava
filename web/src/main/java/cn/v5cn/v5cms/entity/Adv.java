package cn.v5cn.v5cms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZYW on 2014/7/28.
 */
@Table
@Entity
public class Adv implements Serializable {
    private Long advId;
    private String advName;
    private Long advPosId;
    private Date advStartTime;
    private Date advEndTime;
    private int startUsing;         //1：启用 0：关闭
    private int advType;            //1: 图片，2：Flash，3：文本，4：代码
    private Date createDate;        //创建时间
    private String advCode;         //广告代码
    private String advTypeInfo;     //包含广告类型信息的JSON字符串

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public Long getAdvPosId() {
        return advPosId;
    }

    public void setAdvPosId(Long advPosId) {
        this.advPosId = advPosId;
    }

    public Date getAdvStartTime() {
        return advStartTime;
    }

    public void setAdvStartTime(Date advStartTime) {
        this.advStartTime = advStartTime;
    }

    public Date getAdvEndTime() {
        return advEndTime;
    }

    public void setAdvEndTime(Date advEndTime) {
        this.advEndTime = advEndTime;
    }

    public int getStartUsing() {
        return startUsing;
    }

    public void setStartUsing(int startUsing) {
        this.startUsing = startUsing;
    }

    public int getAdvType() {
        return advType;
    }

    public void setAdvType(int advType) {
        this.advType = advType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAdvCode() {
        return advCode;
    }

    public void setAdvCode(String advCode) {
        this.advCode = advCode;
    }

    public String getAdvTypeInfo() {
        return advTypeInfo;
    }

    public void setAdvTypeInfo(String advTypeInfo) {
        this.advTypeInfo = advTypeInfo;
    }

    @Override
    public String toString() {
        return "Adv{" +
                "advId=" + advId +
                ", advName='" + advName + '\'' +
                ", advPosId=" + advPosId +
                ", advStartTime=" + advStartTime +
                ", advEndTime=" + advEndTime +
                ", startUsing=" + startUsing +
                ", advType=" + advType +
                ", advCode=" + advCode +
                ", createDate=" + createDate +
                ", advTypeInfo='" + advTypeInfo + '\'' +
                '}';
    }
}
