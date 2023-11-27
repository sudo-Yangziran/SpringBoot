package com.youkill.composeown.utils;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUpload {
    public static String photograph(String name){
        /**
        * @Author: youkill
        * @Description: 对于图片的一个上传检测
        * @DateTime: 2023/10/23 15:15
        */
        HashSet<String> last_name=new HashSet<>();
        last_name.add(".png");
        last_name.add(".jpg");
        last_name.add(".webapp");

        String Reg_name=find_last_name(name);
        if (Reg_name!="") {
            for (String lastname : last_name
            ) {
                if (Reg_name.equals(lastname)){
                    return lastname;
                }
            }
        }
        return "";
    }
    public static String find_last_name(String name) {
        String input = name;
        String pattern = "(\\.[^.]+)$"; // 匹配最右边的点及其后面的内容

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);

        if (m.find()) {
            String result = m.group(1); // 获取匹配的结果
            return result;
        } else {
            return "";
        }
    }
}
