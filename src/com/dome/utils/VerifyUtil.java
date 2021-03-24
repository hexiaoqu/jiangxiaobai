package com.dome.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerifyUtil {
	
	public static  String  vuCode() {
		Map<String, Object> map = VerifyUtil.createImage();
		String code = VerifyUtil.code;	
		return code;
	}
	
    //�����Ұ�code�����ó�������Ϊ��̬������Ϊ�ľ�����controller�п��Է�����ã�Ȼ�����У��
    public static String code = null;

    // ��֤���ַ���
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // �ַ�����
    private static final int SIZE = 4;
    // ����������
    private static final int LINES = 8;
    // ���
    private static final int WIDTH = 80;
    // �߶�
    private static final int HEIGHT = 40;
    // �����С
    private static final int FONT_SIZE = 30;

    /**
     * ���������֤�뼰ͼƬ
     */
    public static Map<String, Object> createImage() {
        StringBuffer sb = new StringBuffer();
        // 1.�����հ�ͼƬ
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.��ȡͼƬ����
        Graphics graphic = image.getGraphics();
        // 3.���û�����ɫ
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.���ƾ��α���
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 5.������ַ�
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            // ȡ����ַ�����
            int n = ran.nextInt(CHARS.length);
            // ���������ɫ
            graphic.setColor(getRandomColor());
            // ���������С
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // ���ַ�
            graphic.drawString(CHARS[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
            // ��¼�ַ�
            sb.append(CHARS[n]);
        }
        // 6.��������
        for (int i = 0; i < LINES; i++) {
            // ���������ɫ
            graphic.setColor(getRandomColor());
            // �������
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.������֤���ͼƬ
        Map<String, Object> map = new HashMap<>();
        //��֤�븳ֵ��code
        code = sb.toString();
        //ͼƬ
        map.put("image", image);
        return map;
    }

    /**
     * ���ȡɫ
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
    }
}