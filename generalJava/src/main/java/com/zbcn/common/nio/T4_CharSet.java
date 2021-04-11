package com.zbcn.common.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 字符集
 */
public class T4_CharSet {

    public static void main(String[] args) {
        Charset cs = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs.newEncoder();
        //解码器
        CharsetDecoder cd = cs.newDecoder();
        CharBuffer cBuff = CharBuffer.allocate(1024);
        cBuff.put("我看看测试问题！我想学习。");
        cBuff.flip();

        try {
            //编码
            ByteBuffer bBuff = ce.encode(cBuff);
            for (int i = 0; i < bBuff.limit(); i++){
                System.out.println(bBuff.get());
            }
            //解码
            bBuff.flip();
            CharBuffer decode = cd.decode(bBuff);
            int i = 0;
            while (i < decode.limit()){
                System.out.println(decode.get());
                i++;
            }
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}
