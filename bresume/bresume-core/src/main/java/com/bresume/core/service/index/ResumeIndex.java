package com.bresume.core.service.index;

import org.springframework.stereotype.Service;

import com.bresume.core.common.lucene.BaseSearchService;
import com.bresume.core.common.lucene.config.ConfigBean;
import com.bresume.core.common.lucene.config.ConfigurationLoader;
import com.bresume.core.model.dto.ResumeDto;

@Service
public class ResumeIndex extends BaseSearchService<ResumeDto>{

	@Override
	public ConfigBean getConf() {
		return ConfigurationLoader.getProductConf();
	}
	
}
