package com.jf.mydemo.mytest;

import com.jf.mydemo.mytest.ObjectTest.Serializable.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源
 */
public class Resource implements Serializable {
    /**
     * 唯一标识
     */
    private String resourceId;
    /**
     * 资源文件类型，视频、图片、文本
     */
    private Integer resFileType;
    /**
     * 资源名称
     */
    private String resName;
    /**
     * 资源大小，单位K
     */
    private Float resSize;
    /**
     * 文件的格式，未知、png、word、excel
     */
    private String resFormat;
    /**
     * 上传用户id
     */
    private String userId;
    /**
     * 云盘资源所在文件id
     */
    private String folderId;
    /**
     * 上传时间（录播资源为空）
     */
    private Date uplaodTime;
    /**
     * 上传成功状态，涉及转码可能要用到，待讨论
     */
    private Integer uploadStatus;
    /**
     * 是否为录播资源，0录播资源，1用户云盘上传资源
     */
    private Integer isRecordVideo;
    /**
     * 云存储文件id
     */
    private String fileId;
    /**
     * 文本转码后pdf对应的文件id
     */
    private String transcodeFileId;
    /**
     * 数据状态，A：正常；S：停用；D：删除
     */
    private String status;
    /**
     * 更新时间，时间戳，数据操作变化时间
     */
    private Date timestamp;
    /**
     * 发布人
     */
    private User publishUser;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 发布描述
     */
    private String pubDesc;
    /**
     * 发布资源类型
     */
    private String pubType;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getResFileType() {
        return resFileType;
    }

    public void setResFileType(Integer resFileType) {
        this.resFileType = resFileType;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Float getResSize() {
        return resSize;
    }

    public void setResSize(Float resSize) {
        this.resSize = resSize;
    }

    public String getResFormat() {
        return resFormat;
    }

    public void setResFormat(String resFormat) {
        this.resFormat = resFormat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUplaodTime() {
        return uplaodTime;
    }

    public void setUplaodTime(Date uplaodTime) {
        this.uplaodTime = uplaodTime;
    }

    public Integer getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Integer getIsRecordVideo() {
        return isRecordVideo;
    }

    public void setIsRecordVideo(Integer isRecordVideo) {
        this.isRecordVideo = isRecordVideo;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getTranscodeFileId() {
        return transcodeFileId;
    }

    public void setTranscodeFileId(String transcodeFileId) {
        this.transcodeFileId = transcodeFileId;
    }

    public User getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(User publishUser) {
        this.publishUser = publishUser;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPubDesc() {
        return pubDesc;
    }

    public void setPubDesc(String pubDesc) {
        this.pubDesc = pubDesc;
    }

    public String getPubType() {
        return pubType;
    }

    public void setPubType(String pubType) {
        this.pubType = pubType;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId='" + resourceId + '\'' +
                ", resName='" + resName + '\'' +
                '}';
    }
}