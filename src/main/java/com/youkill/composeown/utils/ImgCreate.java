package com.youkill.composeown.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImgCreate {
    public static void TextAndImg(String outputPath, String text) {
        File outputImage = new File(outputPath);

        if (outputImage.exists()) {
            System.out.println("文件已存在，不进行替换：" + outputPath);
            return;
        }

        int width = 1000; // 图片宽度
        int height = 400; // 图片高度
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        Font font = new Font("微软雅黑", Font.PLAIN, 40);
        g2d.setFont(font);
        String truncatedText = truncateText(text, 30); // 截取并添加省略号
        int textX = (width - g2d.getFontMetrics().stringWidth(truncatedText)) / 2;
        int textY = height / 2;
        g2d.drawString(truncatedText, textX, textY);

        try {
            ImageIO.write(image, "png", outputImage);
            System.out.println("图片已保存到：" + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            g2d.dispose();
        }
    }

    private static String truncateText(String text, int maxLength) {
        int length = 0;
        StringBuilder truncatedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (length < maxLength) {
                truncatedText.append(c);
                length += isChineseCharacter(c) ? 2 : 1;
            } else {
                truncatedText.append("…");
                break;
            }
        }

        return truncatedText.toString();
    }

    // 判断字符是否是中文字符
    private static boolean isChineseCharacter(char c) {
        return c >= 0x4e00 && c <= 0x9fff;
    }
}
