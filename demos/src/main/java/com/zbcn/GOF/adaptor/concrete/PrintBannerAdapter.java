package com.zbcn.GOF.adaptor.concrete;

import com.zbcn.GOF.adaptor.interfaces.Print;
import sun.security.mscapi.PRNG;

public class PrintBannerAdapter implements Print {

    private BannerAdaptee banner;

    public PrintBannerAdapter(BannerAdaptee banner) {
        this.banner = banner;
    }

    public PrintBannerAdapter() {
    }

    public static Print buildAdapter(String value){
        return new PrintBannerAdapter(new BannerAdaptee(value));
    }

    @Override
    public void printWeak() {
        banner.showWithPattern();
    }

    @Override
    public void printStrong() {
        banner.showWithStar();
    }
}
