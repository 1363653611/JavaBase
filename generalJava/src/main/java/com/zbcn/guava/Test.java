package com.zbcn.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<String, Object> temp = Maps.newLinkedHashMap();
		temp.put("aa","");
		temp.put("bb","123");
		Map<String, Object> map = Maps.filterValues(temp, new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {

				return ObjectUtils.notEqual(input,null) && ObjectUtils.notEqual(input, StringUtils.EMPTY) ? true :false;
			}
		});
		System.out.println(map);
	}
}
