package com.zbcn.GOF.commond;

import com.zbcn.GOF.commond.concrete.ConcreteDraw;
import com.zbcn.GOF.commond.concrete.DrawCommand;
import com.zbcn.GOF.commond.concrete.MacroCommand;

public class Main {

    public static void main(String[] args) {
        //具体的命令
        DrawCommand drawCommand = new DrawCommand(new ConcreteDraw(), "test me!!!");
        // 命令管理类
        MacroCommand macroCommand = new MacroCommand();
        macroCommand.addCommand(drawCommand);
        macroCommand.execute();
        macroCommand.undo();
        macroCommand.execute();
    }
}
