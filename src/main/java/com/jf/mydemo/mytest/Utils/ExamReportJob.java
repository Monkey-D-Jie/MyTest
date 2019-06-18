package com.jf.mydemo.mytest.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cw
 * @Date: 2019-3-28 14:56
 * @Description:考务上报---上报任务实体类
 */
public class ExamReportJob implements Serializable {
    private static final long serialVersionUID = 5351323727814946957L;
    /**
     * uid
     */
    private String uid;

    /**
     */
    private String jobName;

    /**
     */
    private String jobType;

    /**
     */
    private String exId;

    /**
     */
    private String exName;


    /**
     */
    private Integer templateId;

    /**
     */
    private String chooseOrgs;

    /**
     * 任务开始时间
     */
    private Date startDate;

    /**
     */
    private Date endDate;

    /**
     */
    private Date createTime;

    /**
     */
    private Integer hasAttachment;


    /**
     * 发起任务的机构
     */
    private String orgCode;


    /**
     */
    private Integer isAchieved;

    /**
     */
    private Integer isFinished;

    /**
     */
    private Integer hasPublic;

    private Integer jobStatus;

    /**
     */
    private Integer handleFlag;

    /**
     */
    private List<ExamReportJob> orgCodeList;

    /**
     */
    private List timeList;
    /**
     ********************************************
     */
    private Integer subFinishedJobCount;
    /**
     */
    private Integer jobClosedStatus;
    /**
     */
    private String name;
    /**
     */
    private String jobContent;
    /**
     */
    private String jobDetailInfo;
    /**
     */
    /**
     */
    private String amountType;
    /**
     */
    private String jobDateType;
    /**
     */
    private String orgName;

      private Integer orgTypeId;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getChooseOrgs() {
        return chooseOrgs;
    }

    public void setChooseOrgs(String chooseOrgs) {
        this.chooseOrgs = chooseOrgs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(Integer hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getIsAchieved() {
        return isAchieved;
    }

    public void setIsAchieved(Integer isAchieved) {
        this.isAchieved = isAchieved;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getHasPublic() {
        return hasPublic;
    }

    public void setHasPublic(Integer hasPublic) {
        this.hasPublic = hasPublic;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getHandleFlag() {
        return handleFlag;
    }

    public void setHandleFlag(Integer handleFlag) {
        this.handleFlag = handleFlag;
    }

    public List<ExamReportJob> getOrgCodeList() {
        return orgCodeList;
    }

    public void setOrgCodeList(List<ExamReportJob> orgCodeList) {
        this.orgCodeList = orgCodeList;
    }

    public List getTimeList() {
        return timeList;
    }

    public void setTimeList(List timeList) {
        this.timeList = timeList;
    }

    public Integer getSubFinishedJobCount() {
        return subFinishedJobCount;
    }

    public void setSubFinishedJobCount(Integer subFinishedJobCount) {
        this.subFinishedJobCount = subFinishedJobCount;
    }

    public Integer getJobClosedStatus() {
        return jobClosedStatus;
    }

    public void setJobClosedStatus(Integer jobClosedStatus) {
        this.jobClosedStatus = jobClosedStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getJobDetailInfo() {
        return jobDetailInfo;
    }

    public void setJobDetailInfo(String jobDetailInfo) {
        this.jobDetailInfo = jobDetailInfo;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getJobDateType() {
        return jobDateType;
    }

    public void setJobDateType(String jobDateType) {
        this.jobDateType = jobDateType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrgTypeId() {
        return orgTypeId;
    }

    public void setOrgTypeId(Integer orgTypeId) {
        this.orgTypeId = orgTypeId;
    }
}
