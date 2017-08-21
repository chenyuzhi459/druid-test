package io.sugo.query.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.utils.http.MyHttpConnection;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class BaseQuery implements Query{
	static Logger logger = Logger.getLogger(BaseQuery.class);
	protected final String queryType;
	protected String dataSource;
	protected Map<String, Object> context;
	protected static ObjectMapper jsonMapper = new ObjectMapper();

	public BaseQuery(String queryType) {
		this.queryType = queryType;
	}

	public BaseQuery(String queryType,String dataSource) {
		this(queryType);
		this.dataSource = dataSource;
		this.context = null;
	}

	public BaseQuery(String queryType,String dataSource,Map<String, Object> context) {
		this(queryType);
		this.dataSource = dataSource;
		this.context = context;
	}


	public String getQueryType() {
		return queryType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

	@Override
	public String query(String url)  {
		String str = null;
		try {
			str =  MyHttpConnection.postData(url,jsonMapper.writeValueAsString(this));
		} catch (JsonProcessingException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return str;
	}
}
