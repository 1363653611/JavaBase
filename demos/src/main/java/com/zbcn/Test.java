package com.zbcn;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(2.54);
		System.out.println(bigDecimal.toString());

		//testMaps1();
		Map<String, Object> map = Maps.newHashMap();
		map.put("Vqweqwe","aaa");
		map.put("V12345","1009");
		map.put("v2019","2019");
		map.put("V12345A","1009");
		map.put("v2009","2009");
		map.put("v2029","2029");
		Map<String, String> stringStringMap = packageHead(map);
		System.out.println(stringStringMap);
	}

	private static void testMaps1() {
		Pattern compile = Pattern.compile("v[0-9]{4,}$");
		Matcher match1 = compile.matcher("v20182");
		Matcher match2 = compile.matcher("v2018a");
		System.out.println(match1.matches());
		System.out.println(match2.matches());
		Map<Object, Object> map = Maps.newHashMap();
		map.put("aa","");
		map.put("bb","bb");
		map.put("cc",0);
		Predicate<Object> predicate = new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {
				if(input instanceof String){
					return StringUtils.isNotBlank((String)input);
				}
				return true;
			}
		};
		Map<Object, Object> map1 = Maps.filterValues(map, predicate);
		System.out.println(map1);
	}

	private static Map<String, String> packageHead(Map<String, Object> map) {
		Map<String, String> head = Maps.newLinkedHashMap();
		head.put("区划名称", "vDnm");
		head.put("区划代码", "vDcd");
		head.put("指标名称", "vInm");
		head.put("指标代码", "vIcd");
		head.put("执行人", "svUserName");
		head.put("执行人", "svUserName");
		Pattern compile = Pattern.compile("v[0-9]{4,}$");
		map.entrySet().stream()
				.filter(entry -> {
					Object key = entry.getKey();
					Matcher matcher = compile.matcher((String) key);
					return matcher.matches();
				}).sorted((o1, o2) -> {
			String key1 = o1.getKey();
			String k1 = StringUtils.remove(key1, "v");
			String key2 = o2.getKey();
			String k2 = StringUtils.remove(key2, "v");
			return Integer.parseInt(k1) - Integer.parseInt(k2);
		}).forEach(entity -> {
			String k = entity.getKey();
			Matcher matcher = compile.matcher((String) k);
			if (matcher.matches()) {
				String v1 = StringUtils.remove((String) k, "v");
				head.put(v1 + "年", k);
			}
		});
		return head;
	}
}
