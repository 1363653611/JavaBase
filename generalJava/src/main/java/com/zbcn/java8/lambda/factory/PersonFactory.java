package com.zbcn.java8.lambda.factory;

import com.zbcn.java8.bean.Person;

/**
 * 创建Person对象的对象工厂接口
 * @param <P>
 */
public interface PersonFactory<P extends Person> {

    P create(String firstName, String secondName);
}
