package io.sugo.query;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.DataUtil.http.MyHttpConnection;
import io.sugo.query.member.Context;
import io.sugo.Aggregation.CountAggregation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class TimeseriesQuery implements Query {
	private  final String queryType = "lucene_timeseries";
	private  String dataSource;
	private  String granularity;
	private  Context context;
	private  List aggregations = new LinkedList<>();
	private	 String intervals;
	static ObjectMapper jsonMapper = new ObjectMapper();

	public TimeseriesQuery(){

	}

	public TimeseriesQuery(String dataSource, String granularity, Context context, String intervals) {
		this(dataSource,granularity,context,null,intervals);
	}



	public TimeseriesQuery(String dataSource, String granularity, Context context, List<CountAggregation> aggregations, String intervals) {
		this.dataSource = dataSource;
		this.granularity = granularity;
		this.context = context;
		this.aggregations = aggregations;
		this.intervals = intervals;
	}

	@Override
	public String query(String url) throws JsonProcessingException {
		return MyHttpConnection.postData(url,jsonMapper.writeValueAsString(this));
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
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

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void setAggregations(List<CountAggregation> aggregations) {
		this.aggregations = aggregations;
	}

	public String getGranularity() {

		return granularity;
	}

	public Context getContext() {
		return context;
	}

	public <T> List<T> getAggregations() {
		return aggregations;
	}

	public <T> void addAggregation(T t){
		this.aggregations.add(t);
	}


}
