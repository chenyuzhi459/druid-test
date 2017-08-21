package io.sugo.testQuery.queryException;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class QueryResultMissMatchException extends RuntimeException {
	public static final String MESSAGE = "the query results do not match the original file results";

	public QueryResultMissMatchException() {
	}

	public QueryResultMissMatchException(String message) {
		super(message);
	}
}
