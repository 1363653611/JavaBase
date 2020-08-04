package com.zbcn.pattern.jzz;

import com.zbcn.pattern.jzz.cppojo.Person;

/**
 * Title: PersonBuilder.java8
 * <p>
 * Description: 角色Builder
 *
 * @author likun
 * @version V1.0
 * @created 2018-3-15 上午11:26:52
 */
public interface PersonBuilder {
    void builderHead();

    void buildBody();

    void buildFoot();

    Person buildPerson();
}
