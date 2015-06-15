package com.bresume.core.common.lucene.config;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ConfigurationLoader {

	static Logger logger = Logger.getLogger(ConfigurationLoader.class);

	private static List<ConfigBean> confs;

	public static List<ConfigBean> getConfs() {
		return confs;
	}

	public static void setConfs(List<ConfigBean> confs) {
		ConfigurationLoader.confs = confs;
	}

	public static ConfigBean getConf(String name) {
		if (StringUtils.isEmpty(name))
			return null;
		for (ConfigBean configBean : confs) {
			if (name.equals(configBean.getIndexName())) {
				return configBean;
			}
		}
		return null;
	}

}
