package com.zbcn.GOF.mediator.concrete;

import com.zbcn.GOF.mediator.framework.Colleague;
import com.zbcn.GOF.mediator.framework.Mediator;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ColleagueCheckBox extends Checkbox implements ItemListener, Colleague {

    private Mediator mediator;

    public ColleagueCheckBox(String label, boolean state, CheckboxGroup group) throws HeadlessException {
        super(label, state, group);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        mediator.colleagueChanges();
    }
}
