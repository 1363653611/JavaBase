package com.zbcn.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

	public static void main(String[] args) {
		String type ="jwt";
		String subject = "{\"uid\":\"1234556\",\"role\":\"admin\",\"name\":\"zhangsan\"}";
		String s = creatJwt(type, subject, 600000);
		System.out.println(s);
		Claims parse = parse(s);
		System.out.println(parse.toString());
	}

	private static String SECRET_KEY = "zbcn";
	/**
	 * 创建 Jwt
	 * @param type 数据类型 ：jwt
	 * @param subject  主题
	 * @param ttlMillis 过期时长
	 * @return
	 */
	public static String creatJwt(String type,String subject,long ttlMillis){
		//签名算法
		SignatureAlgorithm alg = SignatureAlgorithm.HS512;
		long startMillis = System.currentTimeMillis();
		Date date = new Date(startMillis);//签名时间
		Map<String, Object> claims = new HashMap<>(); //创建有效载荷(payload)
		claims.put("oid","91d2465c-77d9-429a-b4cf-4d61cbad8e3b");
		claims.put("org","www.zbcn.com");
		SecretKey key = key();
		JwtBuilder jwtBuilder = Jwts.builder().
				setClaims(claims)
				.setId(type)
				.setIssuedAt(date) //签发时间
				.setSubject(subject) //主题:是JWT 主题的拥有者,如:uuid,email,roles 等
				.signWith(alg, key);
		if(ttlMillis > 0){
			long expiredMillis = startMillis + ttlMillis;
			Date expiredDate = new Date(expiredMillis);
			jwtBuilder.setExpiration(expiredDate); //签名过期时间
		}
		return jwtBuilder.compact(); //生成jwt
	}

	/**
	 * 生成签名密钥
	 * @return
	 */
	private static SecretKey key(){
		byte[] decode = Base64Codec.BASE64.decode(SECRET_KEY);
		SecretKey key = new SecretKeySpec(decode, 0, decode.length, "AES");
		return key;
	}

	/**
	 * 解析jwt
	 * @param jwt
	 * @return
	 */
	public static Claims parse(String jwt){
		SecretKey key = key(); //获取签名密钥
		Claims body = Jwts.parser() //开始解析
				.setSigningKey(key)//设置密钥信息
				.parseClaimsJws(jwt)//解析主题信息
				.getBody();
		return body;
	}
}
