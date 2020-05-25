package com.shahrdari.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by amir on 11/29/17.
 */

public class NumberUtil {

    private static final char[] persianNumbers = {'\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};
    private static final char[] englishNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String getPriceFormat(long price) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat) numberFormat;
        formatter.applyPattern("#,###.##");
        return formatter.format(price);
    }

    public static String appendCurrency(String text) {
        return text + " تومان";
    }

    public static String convertNumbersToPersian(String text) {
        String str = text;
        for (int i = 0; i < persianNumbers.length; i++) {
            str = str.replace(englishNumbers[i], persianNumbers[i]);
        }
        return str;
    }

    public static String convertNumbersToEnglish(String text) {
        String str = text;
        for (int i = 0; i < englishNumbers.length; i++) {
            str = str.replace(persianNumbers[i], englishNumbers[i]);
        }
        return str;
    }
}
