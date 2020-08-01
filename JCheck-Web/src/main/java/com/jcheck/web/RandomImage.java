package com.jcheck.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.WeakHashMap;


/**
 * 适用于jsp的 获取图片验证码的方法一
 * 使用getOutputStream 传输图片到前端或者jsp
 */
public class RandomImage extends HttpServlet {

    public static final int WEIDTH=120;
    public static final int HEIGHT=35;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage image=new BufferedImage(WEIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

        Graphics graphics = image.getGraphics();

        //设置背景色
        setBackground(graphics);

        //设置边框
        setBorder(graphics);

        //画随机干扰线
        drawRandomLine(graphics);

        //写随机数
        drawRandomNumber((Graphics2D) graphics);

        //将图片回写给浏览器
        resp.setContentType("image/jpeg");
        //设置不缓存图片
        resp.setDateHeader("expries",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        ImageIO.write(image,"jpg",resp.getOutputStream());

    }

    private void drawRandomNumber(Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("宋体",Font.BOLD,20));

        //汉字取值范围：\u4E00→\u9FA5
        int start = 0x4E00;
        int end =0x9FA5;


        int x=5;
        for (int i=0;i<4;i++){
            //计算旋转角度
            int degree = new Random().nextInt()%30;
            char c = (char) (start+new Random().nextInt(end-start));
            //旋转
            graphics.rotate(degree*Math.PI/180,x,20);
            graphics.drawString(String.valueOf(c),x,20);
            //将旋转角度复位
            graphics.rotate(-degree*Math.PI/180,x,20);
            x+=30;

        }
    }

    private void drawRandomLine(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        for (int i=0;i<5;i++){
            int x1=new Random().nextInt(WEIDTH);
            int y1=new Random().nextInt(HEIGHT);

            int x2=new Random().nextInt(WEIDTH);
            int y2=new Random().nextInt(HEIGHT);

            graphics.drawLine(x1,y1,x2,y2);
        }
    }

    private void setBorder(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect(0,0,WEIDTH-1,HEIGHT-1);
    }

    private void setBackground(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,WEIDTH,HEIGHT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
