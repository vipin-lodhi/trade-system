package com.cs.codingtest.constants;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public enum OptionsStyleEnum {
    AMERICAN,EUROPEAN;

    public static boolean contains(String s)
    {
        for(OptionsStyleEnum choice:values())
            if (choice.name().equals(s))
                return true;
        return false;
    }
}
