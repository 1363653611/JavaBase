package com.zbcn.utils.bloomfilter;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.BitSet;

/**
 *  @title BloomFilter
 *  @Description bloomFilter 布隆过滤器
 *  @author zbcn8
 *  @Date 2020/3/16 15:50
 */
public class BloomFilter {

	/* BitSet初始分配2^24个bit */
	private static final int DEFAULT_SIZE = 1 << 25;

	/* 不同哈希函数的种子，一般应取质数 */
	private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};

	private BitSet bits = new BitSet(DEFAULT_SIZE);

	/* 哈希函数对象 */
	private SimpleHash[] func = new SimpleHash[seeds.length];

	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
		}
	}

	// 将字符串标记到bits中
	public void add(String value) {
		for (SimpleHash f : func) {
			bits.set(f.hash(value), true);
		}
	}

	//判断字符串是否已经被bits标记
	public boolean contains(String value) {
		if (value == null) {
			return false;
		}
		boolean ret = true;
		for (SimpleHash f : func) {
			ret = ret && bits.get(f.hash(value));
		}
		return ret;
	}

	/* 哈希函数类 */
	public static class SimpleHash{
		private int cap;

		private int seed;

		public SimpleHash(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		//hash函数，采用简单的加权和hash
		public int hash(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			return (cap - 1) & result;
		}
	}

	public static void main(String[] args) {
		ArrayList<String> lists = Lists.newArrayList();
		lists.add("aa");
		lists.add("bb");
		lists.add("cc");
		lists.add("dd");
		lists.add("bb");
		lists.add("aa");
		BloomFilter bloomFilter = new BloomFilter();
		lists.forEach( l ->{
			if(bloomFilter.contains(l)){
				System.out.println("重复的数据:" + l);
			}else{
				bloomFilter.add(l);
			}
		});
	}
}
