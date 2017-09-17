package com.cs.codingtest.constants;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public enum CounterPartyEnum {
    PLUTO1,PLUTO2;

    public static boolean contains(String s)
    {
        for(CounterPartyEnum choice:values())
            if (choice.name().equals(s))
                return true;
        return false;
    }
}
