package com.bresume.core.common.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bresume.core.common.upload.UploadConfig.FileSource;
import com.bresume.core.common.upload.UploadConfig.FileType;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.FileUploadUtils;

public class FileUploadHandler {

	private static final Logger logger = Logger
			.getLogger(FileUploadHandler.class);

	public static final Properties uploadConf = new Properties();

	private static boolean uploadEnable = false;

	public static UploadConfig UPLOAD_CONFIG;

	private static int index = 0;

	private static final Object LOCK = new Object();

	static {
		InputStream is = FileUploadHandler.class
				.getResourceAsStream("/upload.properties");
		if (is != null) {
			uploadEnable = true;
			UPLOAD_CONFIG = new UploadConfig();
			try {
				uploadConf.load(is);

				UPLOAD_CONFIG.setRootFileDir(uploadConf
						.getProperty("root.file.dir"));
				UPLOAD_CONFIG.setAdminFileDir(uploadConf
						.getProperty("admin.file.dir"));
				UPLOAD_CONFIG.setDownloadParamName(uploadConf
						.getProperty("download.param.name"));
				UPLOAD_CONFIG.setPortalFileDir(uploadConf
						.getProperty("portal.file.dir"));
				UPLOAD_CONFIG.setStaticUrlPrefix(uploadConf
						.getProperty("static.url.prefix"));
				UPLOAD_CONFIG.setSystemFileDir(uploadConf
						.getProperty("system.file.dir"));

				String types = uploadConf.getProperty("image.type");
				UPLOAD_CONFIG.setImageTypes(Arrays.asList(types.split(",")));

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}

	private static String getDir(Map<String, String> map, String dir) {
		if (CommonUtils.isEmpty(map) || CommonUtils.isEmpty(dir)
				|| dir.indexOf("{") < 0) {
			return dir;
		}
		// 遍历Map中的所有Key，将得到的value值替换模板字符串中的变量值
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			dir = dir.replace("{" + key + "}", map.get(key));
		}
		return dir;
	}

	/**
	 * 
	 * @param img
	 * @param dir
	 *            if null,is default dir else storage.dir+dir
	 * @return
	 */
	public static String uploadFile(byte[] file, FileSource source,
			String suffix, FileType type, Map<String, String> params) {
		if (uploadEnable) {
			StringBuilder fileID = new StringBuilder();

			boolean qualified = false;

			switch (type) {
			case IMAGE:
				qualified = vaildate_image(file, suffix);
			case NOMAL_FILE:
				qualified = true;// TODO 暂无
			case UNKNOWN:
				qualified = true;// TODO 暂无
			}
			if (qualified) {
				String fileRootDir = UPLOAD_CONFIG.getRootFileDir();

				String dir = UPLOAD_CONFIG.getFileDir(source);
				dir = getDir(params, dir);
				String finalDir = fileRootDir + dir;
				File finalFileDir = new File(finalDir);
				if (!finalFileDir.exists()) {
					finalFileDir.mkdirs();
				}
				fileID.append(System.currentTimeMillis());
				fileID.append("_");
				fileID.append(getIndex());
				if (CommonUtils.isNotEmpty(suffix)) {
					fileID.append("." + suffix);
				}
				File imgFile = new File(finalDir + fileID.toString());
				try {
					FileOutputStream fos = new FileOutputStream(imgFile);
					fos.write(file);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
				return dir + fileID.toString();
			} else {
				try {
					throw new Exception("File doing uploading is wrong !"
							+ type + "验证失败");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new Exception(
						"can't find upload.properties ,  upload is not enable!");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return null;
	}

	private static int getIndex() {
		int i = index;
		synchronized (LOCK) {
			if (index == 9) {
				index = 0;
			} else {
				index++;
			}
		}
		return i;
	}

	public static boolean isUploadEnabled() {
		return uploadEnable;
	}

	private static boolean vaildate_image(byte[] img, String suffix) {
		if (CommonUtils.isNotEmpty(suffix)
				&& UPLOAD_CONFIG.getImageTypes().contains(suffix.toLowerCase())) {
			return FileUploadUtils.validate_Image(img);
		}
		return false;
	}
}
