package com.jf.mydemo.mytest.Utils.uploadFileUtils.cloudUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

/**
 * 对应返回上传地址接口json转实体类
 */
public class UploadAddr {
	private String url;
	private String dirId;
	
	@JsonProperty("Signature")
	private String Signature;
	@JsonProperty("AccessKey")
	private String AccessKey;
	private String key;
	@JsonProperty("Date")
	private String Date;
	@JsonProperty("CallBack")
	private String CallBack;
	@JsonProperty("Act")
	private String Act;
	@JsonProperty("storageType")
	private String storageType;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDirId() {
		return dirId;
	}
	public void setDirId(String dirId) {
		this.dirId = dirId;
	}
	@JsonIgnore
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	@JsonIgnore 
	public String getAccessKey() {
		return AccessKey;
	}
	public void setAccessKey(String accessKey) {
		AccessKey = accessKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@JsonIgnore 
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	@JsonIgnore 
	public String getCallBack() {
		return CallBack;
	}
	public void setCallBack(String callBack) {
		CallBack = callBack;
	}
	@JsonIgnore 
	public String getAct() {
		return Act;
	}
	public void setAct(String act) {
		Act = act;
	}
	public String getStorageType() {
		return StringUtils.isEmpty(AccessKey)?"kms":"cloud";
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
}
