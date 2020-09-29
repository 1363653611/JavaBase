package com.zbcn;

public class ExceptionTest {

    public static void main(String[] args) {

        try {
            throw new RuntimeException("异常！");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
