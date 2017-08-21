package io.sugo.query.member.extractionFn.base;

/**
 * Created by chenyuzhi on 17-8-8.
 */
public class BaseExtractionFn {
	protected  final  String type;

	public BaseExtractionFn(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
