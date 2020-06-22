package com.zbcn.GOF.decorator.framework;

/**
 *  @title Display
 *  @Description 显示多行字符的抽象类
 *  @author zbcn8
 *  @Date 2020/6/10 13:31
 */
public abstract class Display {

    /**
     * 获取横向字符数
     * @return
     */
    public abstract int getColumns();

    /**
     * 获取纵向行数
     * @return
     */
    public abstract int getRows();

    /**
     * 获取第row 行的字符串
     * @param row
     * @return
     */
    public abstract  String getRowText(int row);

    /**
     * 全部显示
     */
    public final void show(){
        for (int i = 0; i < getRows() ; i++) {
            System.out.println(getRowText(i));
        }
    }
}
