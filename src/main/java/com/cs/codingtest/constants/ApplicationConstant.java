package com.cs.codingtest.constants;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public class ApplicationConstant {
    public final static String SUCCESS = "SUCCESS";
    public final static String NON_WORKING_DAY_VALIDATION = "Value date cannot fall on weekend or non-working day for currency";
    public final static String NON_CP_VALIDATION = "Counterparty (Customer) is not supported";
    public final static String NON_VALID_ISO_VALIDATION = "Non valid ISO currency codes";
    public final static String NON_EXIST_OPTIONS_STYLE_VALIDATION = "Option Style is not supported";
    public final static String NON_EX_DATE_VALIDATION = "Excercise Start Date,has to be after the trade date but before the expiry date";
    public final static String NON_DEL_DATE_VALIDATION = "Expiry date and premium date shall be before delivery date ";
    public final static String NON_CCY_PAIR_VALIDATION = "CurrencyPair in Wrong Format";
    public final static String NON_VALUE_DATE_PROD_TYPE_VALIDATION = "Value date against the product type is failed";
    public final static String NON_VALUE_DATE_VALIDATION = "Value date cannot be before trade date";
    public final static String NON_EXIST_PRODUCT_TYPE_STYLE_VALIDATION = "Product Type not Supported";

}
