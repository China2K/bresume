package com.bresume.core.common.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;

import com.bresume.core.common.lucene.config.ConfigBean;
import com.bresume.core.common.lucene.config.ConfigBean.Field;
import com.bresume.core.common.lucene.config.ConfigurationLoader;
import com.bresume.core.common.lucene.model.BaseIndexBean;

public abstract class BaseSearchService<T extends BaseIndexBean> {
	public  ConfigBean getConf(){
		return ConfigurationLoader.getConf(this.getClass().getName());
	}

	public List<T> search(String keywords) throws IOException, ParseException {
		ConfigBean config = this.getConf();
		List<Field> fields = config.getFields();

		List<T> result = new ArrayList<T>();
		String index = getConf().getStorePath();
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths
				.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		/*
		 * Analyzer analyzer = new StandardAnalyzer(); QueryParser parser = new
		 * QueryParser("sn", analyzer); Query query = parser.parse(keywords);
		 */
		keywords = keywords.trim().toLowerCase();
		BooleanQuery query = new BooleanQuery();
		String[] ks = keywords.split(" ");

		for (Field field : fields) {
			if (field.getIndexOption() > 0) {
				String fieldName = field.getName();
				query.add(
						new TermQuery(new Term(fieldName, QueryParser
								.escape(keywords))), BooleanClause.Occur.SHOULD);
				query.add(new WildcardQuery(new Term(fieldName, "*"
						+ QueryParser.escape(keywords) + "*")),
						BooleanClause.Occur.SHOULD);
				for (String k : ks) {
					if (StringUtils.isNotEmpty(k.trim())) {
						String k_trim = k.trim();
						query.add(
								new TermQuery(new Term(fieldName, QueryParser
										.escape(k_trim))),
								BooleanClause.Occur.SHOULD);
						query.add(new WildcardQuery(new Term(fieldName, "*"
								+ QueryParser.escape(k_trim) + "*")),
								BooleanClause.Occur.SHOULD);
					}
				}
			}
		}

		TopDocs results = searcher.search(query, 100);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");
		for (ScoreDoc scoreDoc : hits) {
			Document doc = searcher.doc(scoreDoc.doc);
			result.add(getItemFromDoc(doc, config));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private T getItemFromDoc(Document doc, ConfigBean config) {
		Class<T> clazz;
		T obj = null;
		try {
			clazz = (Class<T>) Class.forName(config.getClassName());
			obj = clazz.newInstance();
			List<Field> fields = config.getFields();
			for (Field field : fields) {
				BeanUtils.setProperty(obj, field.getName(),
						doc.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
