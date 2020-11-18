package com.zbcn.utils.idCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  身份证信息分析工具类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/20 16:35
 */
public class IdCardUtil {

    private static Map<String, String> cityCodeMap = new HashMap<String, String>(){
        {
            this.put("11", "北京");
            this.put("12", "天津");
            this.put("13", "河北");
            this.put("14", "山西");
            this.put("15", "内蒙古");
            this.put("21", "辽宁");
            this.put("22", "吉林");
            this.put("23", "黑龙江");
            this.put("31", "上海");
            this.put("32", "江苏");
            this.put("33", "浙江");
            this.put("34", "安徽");
            this.put("35", "福建");
            this.put("36", "江西");
            this.put("37", "山东");
            this.put("41", "河南");
            this.put("42", "湖北");
            this.put("43", "湖南");
            this.put("44", "广东");
            this.put("45", "广西");
            this.put("46", "海南");
            this.put("50", "重庆");
            this.put("51", "四川");
            this.put("52", "贵州");
            this.put("53", "云南");
            this.put("54", "西藏");
            this.put("61", "陕西");
            this.put("62", "甘肃");
            this.put("63", "青海");
            this.put("64", "宁夏");
            this.put("65", "新疆");
            this.put("71", "台湾");
            this.put("81", "香港");
            this.put("82", "澳门");
            this.put("91", "国外");
        }
    };


    /**
     * 获取cityCodeMap
     *
     * @return
     */
    public static  Map<String, String> getCityCodeMap(){
        return cityCodeMap;
    }

    /**
     * 获取身份证分析信息
     * @param idCard 身份证号：18或者15位
     * @return
     */
    public static IdCardInfoExtractor getIdCardInfo(String idCard){
        return  new IdCardInfoExtractor(idCard);
    }

    /**
     * 依据18为身份证号获取省份信息
     * @param idCard
     * @return
     */
    public static String getProvinceBy18bit(String idCard){
        // 获取省份
        String provinceId = idCard.substring(0, 2);
        Set<String> key = cityCodeMap.keySet();
        for (String id : key) {
            if (id.equals(provinceId)) {
                return cityCodeMap.get(id);
            }
        }
        return null;
    }

    /**
     * 获取省份信息
     * @param idCard
     * @return
     */
    public static String getProvince(String idCard){
        idCard = getConvertIdcCard(idCard);
        // 获取省份
        return getProvinceBy18bit(idCard);
    }


    /**
     * 获取性别
     * @param idCard
     * @return
     */
    public static String getGender(String idCard){
        idCard = getConvertIdcCard(idCard);
        return getGenderBy18bit(idCard);
    }

    /**
     * 获取18位身份证号的性别
     * @param idCard
     * @return
     */
    public static String getGenderBy18bit(String idCard){
        String gender = "";
        String id17 = idCard.substring(16, 17);
        if (Integer.parseInt(id17) % 2 != 0) {
            gender = "男";
        } else {
            gender = "女";
        }
        return gender;
    }


    /**
     * 获取出生日期
     * @param idCard
     * @return
     * @throws ParseException
     */
    public static Date getBirthday(String idCard) throws ParseException {
        idCard = getConvertIdcCard(idCard);
        return getBirthdayBy18bit(idCard);
    }

    /**
     * 获取18位身份证号的出生日期
     * @param idCard
     * @return
     */
    public static Date getBirthdayBy18bit(String idCard) throws ParseException {
        String birthday = idCard.substring(6, 14);
        return new SimpleDateFormat("yyyyMMdd")
                .parse(birthday);
    }


    /**
     * 获取年龄
     * @param idCard
     * @return
     */
    public static Integer getAge(String idCard){
        try {
            Date birthday = getBirthday(idCard);
            Instant instant = birthday.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = instant.atZone(zoneId).toLocalDate();
            LocalDate now = LocalDate.now();
            int year = localDate.getYear();
            int nowYear = now.getYear();
            if(year > nowYear){
                throw new RuntimeException("非法的出生日期：" + localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            return nowYear - year;
        } catch (ParseException e) {
            throw new RuntimeException("身份证解析年龄异常。",e);
        }
    }


    /**
     * 获取转换后的身份证格式，如果为15位，则转换为18位
     * @param idCard
     * @return
     */
    public static String getConvertIdcCard(String idCard){
        IdCardValidator validator = new IdCardValidator();
        if (validator.isValidatedAllIdCard(idCard)) {
            if (idCard.length() == 15) {
                idCard = validator.convertIdcarBy15bit(idCard);
            }
            return idCard;
        }else{
            throw new IllegalArgumentException("非法的身份证格式");
        }

    }


    /**
     *  判断是否为有效的身份证
     * @param idCard
     * @return
     */
    public static boolean isValidatedAllIdCard(String idCard){
        IdCardValidator validator = new IdCardValidator();
        return validator.isValidatedAllIdCard(idCard);
    }


    public static void main(String[] args) {
        String idcard15 = "310112850409522";
        String idcard18 = "";
        IdCardInfoExtractor idCardInfo = getIdCardInfo(idcard15);
        System.out.println("15位身份证：" + idCardInfo.toString());
    }

}
