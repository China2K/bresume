package com.bresume.core.model.entity.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;

/**
 * @author 2k
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "br_sys_file")
public class SysFile extends BaseEntity {
	// 文件类型：1-图片；2-语音；3-视频
	private String fileType;
	// 文件存储URL
	private String fileUrl;
	private Long fileSize;

	// 上传后文件名 自定义
	private String fileName;

	// 上传前文件名
	private String uploadName;

	private String createdBy;
	private Date createdTime;

	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FILE_URL")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "FILE_SIZE")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "UPLOAD_NAME")
	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
