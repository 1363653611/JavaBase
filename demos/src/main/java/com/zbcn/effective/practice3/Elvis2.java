package com.zbcn.effective.practice3;
/**
 * singleton with public final field
 *
 * @author likun
 * @since 2021/8/4 10:18
 */
public class Elvis2 {

    private static final Elvis2 INSTANCES = new Elvis2();

    private Elvis2() {
    }

    public static Elvis2 getInstance(){
        return INSTANCES;
    }

}
