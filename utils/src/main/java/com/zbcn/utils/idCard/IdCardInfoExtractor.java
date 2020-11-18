package com.zbcn.utils.idCard;

import com.zbcn.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  身份证号拓展类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/20 16:26
 */
public class IdCardInfoExtractor {
    // 省份
    private String province;
    // 城市
    private String city;
    // 区县
    private String region;
    // 年份
    private int year;
    // 月份
    private int month;
    // 日期
    private int day;
    // 性别
    private String gender;
    // 出生日期
    private Date birthday;
    //年龄
    private int age;



    private IdCardValidator validator = null;


    /**
     * 通过构造方法初始化各个成员属性
     */
    public IdCardInfoExtractor(String idcard) {
        try {
            validator = new IdCardValidator();
            if (validator.isValidatedAllIdCard(idcard)) {
                if (idcard.length() == 15) {
                    idcard = validator.convertIdcarBy15bit(idcard);
                }
                // 获取省份
                this.province = IdCardUtil.getProvinceBy18bit(idcard);

                // 获取性别
                this.gender = IdCardUtil.getGenderBy18bit(idcard);

                // 获取出生日期
                String birthday = idcard.substring(6, 14);
                Date birthdate = new SimpleDateFormat("yyyyMMdd")
                        .parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);

                //获取年龄
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
                String year=simpleDateFormat.format(new Date());
                this.age=Integer.parseInt(year)-this.year;

            }
        } catch (Exception e) {
            throw new RuntimeException("创建身份证解析对象失败。",e);
        }
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："
                + DateUtils.getDateYYYYMMDD(this.birthday) + ",年龄：" + this.age;
    }

    public static void main(String[] args) {
        String idcard = "310112850409522";
        IdCardInfoExtractor ie = new IdCardInfoExtractor(idcard);
        System.out.println(ie.toString());
    }
}
