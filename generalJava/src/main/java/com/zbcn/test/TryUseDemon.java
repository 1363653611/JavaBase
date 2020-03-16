package com.zbcn.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zbcn.utils.option.Try;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Function;

public class TryUseDemon {

	public static void main(String[] args) {
		Try<Object> failure = Try.failure(new RuntimeException("报错测试"));
		System.out.println(failure.isFailure());
		Commodity zhangsan = new Commodity("zhangsan", 200.00d);
		Try<Commodity> success = Try.success(zhangsan);
		System.out.println(success.isSuccess());
		String zhangsan1 = success.filter(m -> m.name.equals("zhangsan")).get().getName();
		System.out.println(zhangsan1);

		//Function<T,R> 对象，的T 必须是可以 使用 try-resource 关闭的对象，即实现AutoCloseable 接口
		Function<Commodity, JSONObject> function = book -> (JSONObject) JSON.toJSON(book);
		Function<Commodity, Try<JSONObject>> apply = Try.apply(function);
		JSONObject jsonObject = apply.apply(zhangsan).get();
		System.out.println(jsonObject);
	}

	@Data
	@AllArgsConstructor
	static class Commodity implements AutoCloseable{
		private String name;
		private Double price;

		@Override
		public void close() {
			System.out.println("自动关闭流。。。。");
		}
	}
}
