package com.youkill.composeown.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FileEncryptByName {

    private static Set<String> generatedFilenames = new HashSet<>();

    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    public static synchronized String generateUniqueEncryptedFilename() {
        while (true) {
            long timestamp = generateTimestamp();
            if (timestamp < lastTimestamp) {
                // 时钟回退，等待下一毫秒
                continue;
            }

            lastTimestamp = timestamp;
            String filename = generateEncryptedFilename(timestamp);
            if (!generatedFilenames.contains(filename)) {
                generatedFilenames.add(filename);
                return filename;
            }
        }
    }

    private static long generateTimestamp() {
        return System.currentTimeMillis();
    }

    private static String generateEncryptedFilename(long timestamp) {
        try {
            // 创建一个MessageDigest对象并指定使用SHA-256算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将时间戳字符串转换为字节数组并进行哈希
            String timestampStr = String.valueOf(timestamp);
            byte[] hashedBytes = digest.digest(timestampStr.getBytes());

            // 将哈希字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            // 生成加密后的文件名
            return "file_" + hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
