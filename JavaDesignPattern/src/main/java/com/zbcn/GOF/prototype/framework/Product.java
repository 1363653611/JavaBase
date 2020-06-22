package com.zbcn.GOF.prototype.framework;

/**
 * 复制功能接口
 */
public interface Product extends Cloneable {

    /**
     *   使用方法
     * @param s
     */
    public void use(String s);

    /**
     * 创建克隆产品
     * @return
     */
    public Product createClone();
}
