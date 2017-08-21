package io.sugo.query.member.javaScriptConfig;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class JavaScriptConfig {
	private boolean disabled = true;

	public JavaScriptConfig(){
	}
	public JavaScriptConfig(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
