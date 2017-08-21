package io.sugo.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by chenyuzhi on 17-8-12.
 */
public class JsonParseUtil {
	static ObjectMapper jsonMapper = new ObjectMapper();
	static Logger logger = Logger.getLogger(JsonParseUtil.class);
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void main(String[] args) {
		JsonParseUtil jsonParseUtil = new JsonParseUtil();
		jsonParseUtil.test();
	}



	public void test(){
		//JSON类型的字符串
		String strJson = "{a:1,b:2,c:3,d:[{a1:11,a2:22,a3:33},{a1:{a2:222,a3:333}}]}";

		// println(strJson.substring(1,strJson.length()-1));
		Map jsonMap = new HashMap();
		parseJSON2Map(jsonMap,checkFormat(strJson),null);
		printJsonMap(jsonMap);
	}


	public static String checkFormat(String str){
		String _str = str.trim();
		if(_str.startsWith("[") && _str.endsWith("]")){
			return  _str.substring(1,_str.length()-1);
		}
		return  _str;
	}

	/**
	 * 打印Map中的数据
	 * @param map
	 */
	public static void printJsonMap(Map map){
		Set entrySet = map.entrySet();
		Iterator<Map.Entry<String, Object>> it = entrySet.iterator();
		//最外层提取
		while(it.hasNext()){
			Map.Entry<String, Object> e = it.next();
			System.out.println("Key 值："+e.getKey()+"     Value 值："+e.getValue());
		}
	}



	/**
	 * JSON 类型的字符串转换成 Map
	 */
	public static void parseJSON2Map(Map jsonMap,String jsonStr,String parentKey){
		//字符串转换成JSON对象
		JSONObject json = JSONObject.fromObject(jsonStr);
		//最外层JSON解析
		for(Object k : json.keySet()){
			//JSONObject 实际上相当于一个Map集合，所以我们可以通过Key值获取Value
			Object v = json.get(k);
			//构造一个包含上层keyName的完整keyName
			String fullKey = (null == parentKey || parentKey.trim().equals("") ? k.toString() : parentKey + "." + k);

			if(v instanceof JSONArray){

				 //如果内层还是数组的话，继续解析
				Iterator it = ((JSONArray) v).iterator();
				while(it.hasNext()){
					JSONObject json2 = (JSONObject)it.next();
					parseJSON2Map(jsonMap,json2.toString(),fullKey);
				}
			} else if(isNested(v)){
				parseJSON2Map(jsonMap,v.toString(),fullKey);
			}
			else{
				jsonMap.put(fullKey, v);
			}
		}
	}


	public static List parseJsonToList(String jsonStr) {
		List<Map<String,Object>> list = null;
		try {
			list = jsonMapper.readValue(jsonStr,List.class);
			/*for(Iterator it = list.iterator();it.hasNext();){
				Map obj = (HashMap)it.next();
							//println(((Map)obj.get("event")));
				println(obj);

			}*/
		} catch (IOException e) {
			logger.error("json转换出错",e);
			e.printStackTrace();
		}
		return list;
	}



	public static boolean isNested(Object jsonObj){

		return jsonObj.toString().contains("{");
	}

	public static void println(Object str){
		System.out.println(str);
	}
}
