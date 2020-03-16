package com.zbcn.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zbcn.utils.option.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BuilderDemonTest {

	public static void main(String[] args) {
		GirlFriend build = Builder.of(GirlFriend::new)
				.with(GirlFriend::addHobby, "hobby")
				.with(GirlFriend::addGift, "gift", "手表").build();
		System.out.println(JSON.toJSONString(build));
	}
}


@Data
class GirlFriend {

	private String name;
	private int age;
	private int bust;
	private int waist;
	private int hips;
	private List<String> hobby;
	private String birthday;
	private String address;
	private String mobile;
	private String email;
	private String hairColor;
	private Map<String, String> gift;


	public void addHobby(String hob){
		this.hobby = Optional.ofNullable(this.hobby).orElse(Lists.newArrayList());
		this.hobby.add(hob);
	}

	public void addGift(String day, String gift) {
		this.gift = Optional.ofNullable(this.gift).orElse(new HashMap<>());
		this.gift.put(day, gift);
	}

	public void setVitalStatistics(int bust, int waist, int hips) {
		this.bust = bust;
		this.waist = waist;
		this.hips = hips;
	}

}
