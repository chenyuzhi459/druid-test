package io.sugo.dataRobot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.joda.time.Interval;

import java.util.*;
import java.util.Map.Entry;


import java.util.concurrent.CountDownLatch;

/**
 * Created by qwe on 17-7-27.
 */
public class Test01 {
    private static final int SESSION_TIMEOUT=5000;
    private ZooKeeper zk;
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    public enum AnalysisType
    {
        CARDINALITY,
        SIZE,
        INTERVAL,
        AGGREGATORS,
        MINMAX,
        QUERYGRANULARITY;

        @JsonValue
        @Override
        public String toString()
        {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static AnalysisType fromString(String name)
        {
            return valueOf(name.toUpperCase());
        }

        public byte[] getCacheKey()
        {
            return new byte[]{(byte) this.ordinal()};
        }
    }

    public static final EnumSet<AnalysisType> DEFAULT_ANALYSIS_TYPES = EnumSet.of(
            AnalysisType.CARDINALITY,
            AnalysisType.SIZE,
            AnalysisType.INTERVAL,
            AnalysisType.MINMAX
    );
    public static void main(String[] args) throws Exception {
        Double n = 1.5454545;
        String s= String.format("%.4f",n);
        println(s);
    }












    public static void println(String str){
        System.out.println(str);
    }



}
