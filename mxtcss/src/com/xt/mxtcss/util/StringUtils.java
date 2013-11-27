package com.xt.mxtcss.util;
/**
	�ַ���������
 */
public class StringUtils {

	
    /**
     *	�ж��Ƿ�Ϊ�ջ���Ϊ���ַ���
     * @param str String
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equals(String str1, String str2) {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }

    /**
     
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }

    /**
     *
     
     */
    public static boolean contains(String str1, String str2) {
        return str1 != null && str1.contains(str2);
    }

    /**
    
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }
}
