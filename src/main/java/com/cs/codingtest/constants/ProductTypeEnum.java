package com.cs.codingtest.constants;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public enum ProductTypeEnum {
    Spot,Forward,VanillaOption;

    public static boolean contains(String s)
    {
        for(ProductTypeEnum choice:values())
            if (choice.name().equals(s))
                return true;
        return false;
    }

    public static boolean isSpotOrForward(String s) {
        if (ProductTypeEnum.Spot.name().equals(s)
                || ProductTypeEnum.Forward.name().equals(s))
            return true;

        return false;
    }

    public static boolean isOption(String s) {
        if (ProductTypeEnum.VanillaOption.name().equals(s))
            return true;
        return false;
    }
}
