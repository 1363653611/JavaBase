package com.zbcn.GOF.mediator.framework;

import com.zbcn.GOF.mediator.concrete.ColleagueButton;
import com.zbcn.GOF.mediator.concrete.ColleagueCheckBox;
import com.zbcn.GOF.mediator.concrete.ColleagueTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 具体的仲裁者，即媒介
 */
public class LoginFrame extends Frame implements Mediator, ActionListener {

    private ColleagueCheckBox checkBoxGuest;

    private ColleagueCheckBox checkLogin;

    private ColleagueTextField textUser;

    private ColleagueTextField textPwd;

    private ColleagueButton buttonOk;
    private ColleagueButton buttonCancel;

    public LoginFrame (String title){
        super(title);
        setBackground(Color.lightGray);
        //生成4*2 窗口
        setLayout(new GridLayout(10,20));

        //生成各个Colleague
        createColleagues();
        add(checkBoxGuest);
        add(checkLogin);
        add(new Label("UserName:"));
        add(textUser);
        add(new Label("password:"));
        add(textPwd);
        add(buttonOk);
        add(buttonCancel);

        //设置初始的禁用状态
        colleagueChanges();

        //显示
        pack();
        show();

    }



    //设置背景色
    //设置布局
    //调用 createColleagues方法 生成Colleague
    //配置 colleague
    //设置初始状态
    // 显示
    @Override
    public void createColleagues() {
        CheckboxGroup p = new CheckboxGroup();
        checkBoxGuest = new ColleagueCheckBox("guest", true, p);
        checkLogin = new ColleagueCheckBox("login", true, p);

        textUser = new ColleagueTextField("", 10);
        textPwd = new ColleagueTextField("", 10);
        textPwd.setEchoChar('*');
        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("CANCEL");

        //设置Mediator
        checkBoxGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPwd.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);

        //设置监听器
        checkBoxGuest.addItemListener(checkBoxGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPwd.addTextListener(textPwd);
        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    @Override
    public void colleagueChanges() {
        if(checkBoxGuest.getState()){
            textUser.setColleagueEnabled(false);
            textPwd.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(true);
        }else{
            textUser.setColleagueEnabled(true);
            userPassChanged();
        }
    }

    //当 textUser 或者 textPass文本输入框的输入文字发生变化时，判断各Colleague 的状态
    private void userPassChanged() {

        if(textUser.getText().length() > 0){
            textPwd.setColleagueEnabled(true);
            if(textPwd.getText().length() > 0){
                buttonOk.setColleagueEnabled(true);
            }else{
                buttonOk.setColleagueEnabled(false);
            }
        }else{
            textPwd.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }
}
