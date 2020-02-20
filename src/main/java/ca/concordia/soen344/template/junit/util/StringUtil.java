// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.util;

import java.text.NumberFormat;

public class StringUtil
{
    protected StringUtil() {
    }
    
    public static String elapsedTimeAsString(final long runTime) {
        return NumberFormat.getInstance().format(runTime / 1000.0);
    }
    
    public static String extractClassName(final String className) {
        if (className.startsWith("Default package for")) {
            return className.substring(className.lastIndexOf(".") + 1);
        }
        return className;
    }
    
    public static String truncate(String s, final int length) {
        if (s.length() > length) {
            s = String.valueOf(s.substring(0, length)) + "...";
        }
        return s;
    }
}
