package io.sugo.testQuery;

import io.sugo.query.FirstNQuery;
import io.sugo.query.FunnelQuery;
import io.sugo.query.SelectQuery;
import io.sugo.query.TopNQuery;
import org.apache.log4j.Logger;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class Test {
	static Logger looger = Logger.getLogger(Test.class);
	public static void main(String[] args) {
		looger.info("start test...");
		FirstNQueryTest.test();
		RetentionQueryTest.test();
		FunnelQueryTest.test();
		UserGroupQueryTest.test();
		TopNQueryTest.test();
		MultiHavingQueryTest.test();
		ScanQueryTest.test();
		SegmentMetadataQueryTest.test();
		TimeseriesQueryTest.test();
		SelectQueryTest.test();
		TimeBoundaryQueryTest.test();
		GroupByQueryTest.test();

		looger.info("end test...");
	}
}
