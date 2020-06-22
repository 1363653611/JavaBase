package com.zbcn.GOF.mediator.concrete;

import com.zbcn.GOF.mediator.framework.Colleague;
import com.zbcn.GOF.mediator.framework.Mediator;

import java.awt.*;

public class ColleagueButton extends Button implements Colleague {

    private Mediator mediator;

    public ColleagueButton(String caption) {
        super(caption);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }
}
