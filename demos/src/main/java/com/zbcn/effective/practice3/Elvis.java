package com.zbcn.effective.practice3;
/**
 * singleton with public final field
 *
 * @author likun
 * @since 2021/8/4 10:18
 */
public class Elvis {

    public static final Elvis INSTANCES = new Elvis();

    private Elvis() {
    }

}
