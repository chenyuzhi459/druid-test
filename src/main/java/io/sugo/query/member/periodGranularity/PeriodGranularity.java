package io.sugo.query.member.periodGranularity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;

/**
 * Created by chenyuzhi on 17-8-19.
 * 类似的格式如下:
 *
 	{
      "type": "period",
      "period": "P7D",
      "timeZone": "+08:00"
    }
 */

public class PeriodGranularity {
	private final String type = "period";
	private String period;
	private String origin;
	private String timeZone;

	public PeriodGranularity() {
	}

	public PeriodGranularity(String period, String origin, String timeZone) {
		this.period = period;
		this.origin = origin;
		this.timeZone = timeZone;
	}

	public String getType() {
		return type;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
