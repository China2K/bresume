package com.bresume.core.common.upload;

import java.util.List;

public class UploadConfig {
	private String rootFileDir;

	private String portalFileDir;
	private String adminFileDir;
	private String systemFileDir;

	private List<String> imageTypes;

	private String downloadParamName;

	private String staticUrlPrefix;
	
	public String getRootFileDir() {
		return rootFileDir;
	}

	public void setRootFileDir(String rootFileDir) {
		this.rootFileDir = rootFileDir;
	}

	public String getPortalFileDir() {
		return portalFileDir;
	}

	public void setPortalFileDir(String portalFileDir) {
		this.portalFileDir = portalFileDir;
	}

	public String getAdminFileDir() {
		return adminFileDir;
	}

	public void setAdminFileDir(String adminFileDir) {
		this.adminFileDir = adminFileDir;
	}

	public String getSystemFileDir() {
		return systemFileDir;
	}

	public void setSystemFileDir(String systemFileDir) {
		this.systemFileDir = systemFileDir;
	}

	public List<String> getImageTypes() {
		return imageTypes;
	}

	public void setImageTypes(List<String> imageTypes) {
		this.imageTypes = imageTypes;
	}

	public String getDownloadParamName() {
		return downloadParamName;
	}

	public void setDownloadParamName(String downloadParamName) {
		this.downloadParamName = downloadParamName;
	}

	public String getStaticUrlPrefix() {
		return staticUrlPrefix;
	}

	public void setStaticUrlPrefix(String staticUrlPrefix) {
		this.staticUrlPrefix = staticUrlPrefix;
	}

	public String getFileDir(FileSource fs) {

		switch (fs) {
		case SYSTEM:
			return systemFileDir;
		case ADMIN:
			return adminFileDir;
		case PORTAL:
			return portalFileDir;

		default:
			break;
		}
		return staticUrlPrefix;
	}

	public enum FileSource {
		SYSTEM, ADMIN, PORTAL;
	}
	
	public enum FileType {
		IMAGE(1),VOICE(2),VIDEO(3),NOMAL_FILE(4),UNKNOWN(0);
		
		private int code;
		private FileType(int code) {
			this.code = code;
		}
		
		public FileType fromCode(int code){
			 for (FileType filetype : FileType.values()) {
		            if (filetype.getCode() == code)
		                return filetype;
		        }
		        return FileType.UNKNOWN;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
		
	}

}
