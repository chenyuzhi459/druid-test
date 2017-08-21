package io.sugo.query.base;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by qwe on 17-7-27.
 */
public interface Query {
	public String query(String url) throws JsonProcessingException;
}
