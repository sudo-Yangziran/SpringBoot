package com.youkill.composeown.utils;

/**
* @Author: youkill
* @Description: 检查是否有违规的名称或者是内容
* @DateTime: 2023/10/19 15:29
*/
public class CheckString {
    public static boolean CheckString(String str){
        if (uploadName((str))){
            return true;
        }
        return false;
    }
    public static boolean uploadName(String str){
        return true;
    }
}
