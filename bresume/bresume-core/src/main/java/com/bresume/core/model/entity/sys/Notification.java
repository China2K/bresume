package com.bresume.core.model.entity.sys;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bresume.core.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name = "br_notification")
public class Notification extends BaseEntity
{

	// Fields
	private String type;
	private String sender;
	private String address;
	private String cc;
	private String bcc;
	private String replyTo;
	private String subject;
	private String content;
	private boolean sentFlag;
	private Date   sentTime;
	private Date   createdTime;
	
	//for shotmessage
	private String SMContent;
	private String SMSendTo;
	

	// Constructors

	/** default constructor */
	public Notification()
	{
	}


	/**
	 * @return the type
	 */
	@Column(name = "`TYPE`", length = 2)
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the sender
	 */
	@Column(name = "SENDER", length = 32)
	public String getSender() {
		return sender;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}


	/**
	 * @return the address
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the cc
	 */
	@Column(name = "CC")
	public String getCc() {
		return cc;
	}


	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}


	/**
	 * @return the bcc
	 */
	@Column(name = "BCC")
	public String getBcc() {
		return bcc;
	}


	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}


	/**
	 * @return the replyTo
	 */
	@Column(name = "REPLYTO")
	public String getReplyTo() {
		return replyTo;
	}


	/**
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}


	/**
	 * @return the subject
	 */
	@Column(name = "SUBJECT")
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @return the content
	 */
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the sentFlag
	 */
	@Column(name = "SENT_FLAG", length = 1)
	public boolean isSentFlag() {
		return sentFlag;
	}


	/**
	 * @param sentFlag the sentFlag to set
	 */
	public void setSentFlag(boolean sentFlag) {
		this.sentFlag = sentFlag;
	}


	/**
	 * @return the sentTime
	 */
	@Column(name="SENT_TIME")
	public Date getSentTime() {
		return sentTime;
	}


	/**
	 * @param sentTime the sentTime to set
	 */
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
	@Column(name="CREATED_TIME")
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Column(name="SM_CONTENT")
	public String getSMContent() {
		return SMContent;
	}


	public void setSMContent(String sMContent) {
		SMContent = sMContent;
	}

	@Column(name="SM_SENDTO")
	public String getSMSendTo() {
		return SMSendTo;
	}


	public void setSMSendTo(String sMSendTo) {
		SMSendTo = sMSendTo;
	}

	
	public interface Type{
		/**邮件*/
		String EMAIL = "1";
		/**短信*/
		String SHORTMSG = "2";
		/**其他*/
		String OTHER = "3";
		//邮件 与 短信一起发送
		String EMAILANDSHORTMSG = "4";
	}
	
}