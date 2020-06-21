package com.zbcn.GOF.commond.concrete;

import com.zbcn.GOF.commond.framework.Command;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class MacroCommand implements Command {

    //命令集合
    private Stack<Command> commands = new Stack();


    @Override
    public void execute() {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()){
            Command next = iterator.next();
            next.execute();
        }
    }


    /**
     * 添加命令
     * @param command
     */
    public void addCommand(Command command){
        if(Objects.nonNull(command)){
            commands.push(command);
        }
    }

    /**
     * 删除最后一条记录
     */
    public void undo(){
        if(!commands.isEmpty()){
            commands.pop();
        }
    }

    /**
     * 清除记录
     */
    public void clear(){
        commands.clear();
    }
}
