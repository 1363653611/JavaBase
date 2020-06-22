package com.zbcn.GOF.commond.concrete;

import com.zbcn.GOF.commond.framework.Command;
import com.zbcn.GOF.commond.framework.Drawable;

/**
 * 某个类型的command
 */
public class DrawCommand implements Command {

    //具体的执行类
    private Drawable drawable;

    private String content;

    public DrawCommand(Drawable drawable, String content) {
        this.drawable = drawable;
        this.content = content;
    }

    @Override
    public void execute() {
        drawable.draw(content);
    }
}
