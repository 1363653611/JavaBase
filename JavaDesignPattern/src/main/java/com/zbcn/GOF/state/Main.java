package com.zbcn.GOF.state;

import com.zbcn.GOF.state.concrete.SafeContext;

public class Main {

    public static void main(String[] args) {
        SafeContext safeContext = new SafeContext();
        for (int i = 0; i < 24; i++) {
            safeContext.doing(i);
        }
    }


}
