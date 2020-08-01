
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;


/**
 * @BelongsProject: jcheck
 * @Author: AnthonyChen
 * @CreateTime: 2019-09-30 14:17
 * @Description: 适用于前后端分离项目的生成base64图片验证码：
 * 按照controller的方法或者下文中注释的使用顺序使用。
 * 主要步骤：图片验证码随机生成后，并进行base64转换。（也可将base64图片字符串转换为图片生成）
 */
public class MakeImageBase64 {
    private static SecureRandom random = new SecureRandom();
    protected static final Logger logger = Logger.getLogger(MakeImageBase64.class);

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            //256
            color = color << 8;

            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }

    /**
     * 1 生成指定长度的随机数字和字母
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            switch (charOrNum) {
                case "char":
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    val += (char) (random.nextInt(26) + temp);
                    break;
                case "num":
                    val += String.valueOf(random.nextInt(10));
                    break;
            }
        }
        return val;
    }

    /**
     * 2 将第一步得到的数据传入 并生成Base64编码的验证码图片
     * @param w
     * @param h
     * @param code
     * @return
     * @throws Exception
     */
    public static String imageToBase64(int w, int h, String code) throws Exception {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        SecureRandom rand = new SecureRandom();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.GRAY);// 设置边框色
        g2.fillRect(0, 0, w, h);

        Color c = getRandColor(200, 250);
        g2.setColor(c);// 设置背景色
        g2.fillRect(0, 2, w, h - 4);

        // 绘制干扰线
        SecureRandom random = new SecureRandom();
        g2.setColor(getRandColor(160, 200));// 设置线条的颜色
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(g2, w, h, c);// 使图片扭曲

        g2.setColor(getRandColor(100, 160));
        int fontSize = h - 4;
        Font font = new Font("Arial", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (w / verifySize) * i + fontSize / 2, h / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
        }
        g2.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return new BASE64Encoder().encode(baos.toByteArray());
    }

    /**
     * 3 如果需要 可以将Base64位编码的图片进行解码，并保存到指定目录
     * 一般情况不需要此步，只需要让前端发送回用户输入的验证码和第一步的验证码
     * @param base64
     *            base64编码的图片信息
     * @return
     */
    public static void base64ToImage(String base64) {

        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream write = null;
        try {
            File file = new File("D:/test.jpg");
            write = new FileOutputStream(file);
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            if (write!=null){
                try {
                    write.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        base64ToImage("/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a\r\nHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy\r\nMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAmAHIDASIA\r\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\r\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\r\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\r\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\r\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\r\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\r\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\r\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD2Gisu\r\n81pLe3uZoYHlW2JWR2YIoYfw8856YwCDkc+nOSRaxqWnTazc6m1jEF3QxKxUbe3T17dSfyrQUIcy\r\nvfQ6+e8igcREl5iMrFGNzkeuOwzxk4HvVeU3kwj8wNAkjYEURy54J+ZwCF4yDjvjDcgHL8H6pHe2\r\nL2wt0imgwXZFVVkzkZwO/Azx6fhtzbluYs3axrJlFjIGXbGRtP0DkjB9eMct6bg4uLcWZ1/a2Elo\r\n1lcXdxb+ey5Ek7MTg8AFie+OlWpJIbexjgglAKKsaZLEjooyByeoz09SRisK/M134v020l2TfZE8\r\n1zGNvzdeQScchePetwTfbIw4jZHVgDgElVODgjgglcHBHGRwe62Caskr+ZImQigtcNx94MGz78f5\r\n/LggkneSYecrKrgRhkAJG0eh9d3OB06cZOdKli7TSSpOsYx5ksirGWAdvlJOCVXBJzwyuMFsnLNX\r\nM9hpVzerKGZFwvzbsZbCkZ74IJ/rgUNNEcrbSQ3UPEssFybCztBcX2SCAcquO5x+fXjviqyp4sm+\r\ncahZxuefIKjI9vu/1p3hyzk07T0udqmW5UOzv3B5HP8A9f8A+tpRa/p1zfPYTHy51OCHGFJ9j/n2\r\noWmiNr8rair2MpvEtzFDc2uoxGz1GOJniZfuOcccHI/mDWrpF/cvpNpdag6gSL8zlQMlmwvTp6dO\r\ncj8cnxpYY0uCaJNyQPtz/cU8Y+mcVFp2n6jrmmwCW+eztIoljSGMHLjGNzdOuOOv+L6FuMJQUttT\r\nrmz/AKyI7s9t3BHt71Fc3FsI4hPOIPMlRU3uU3PuBC5yMkkdO/oRmuZ8OC6SbUrHzZLhbS4jVf3h\r\nTgOQTnPA+UHHOQCMHNdPLFI43K/kzbgfkYHeobocjuODxkZ4Pela2hhOPLKwx9NhkkZzLdgsSSFu\r\npAPwAbAoqSO8jaNGkVonIBaN8Eoe4OMjI9iRRU8sexlyx7DNTs0v9NntpA5V16RkBiRyMZ4zx3rm\r\nHgbWdWbSld10vTQAy55cjgDj6EfQeprqnhke7SVbj90o5ix/FnrkEdiwIORkKRgg557WNFkfWPtO\r\nn3clpcTgK+0lQ55OAcjJwpOBnpk46mkzopS6XM27eeDxdJLoluzNHGqzRKAB2GPb+H8RXQzNPLdJ\r\nDIpaOSKQyR+USkn3V+b5eeDgAkZB6HGQ7RdOg0a3dMyvPId0srIcsf19f1qnqviRNJkktkt5Hd0L\r\nxPvLAsxbrnkAcYA7HAwAKNxt87Sj0MzTrvzta1bUngEz8QxKMhWOcDkAkDhcnBxnNdGZ7Zhjy5FA\r\nIPByMjkHH1H6Cszw3Z22n6bbS3JhS7kZ8+YvzgMBlR3Gdik/Q/hqXN1aJbJdTSRoUdZFEsYdo+MM\r\nAF5ztLDIJxuPUcUnqyKjvPR6LQjtbxVt4mEzsTGo/eo4PA7gnIP156Zpl3HbajBPbzFEjlQKpReV\r\nbk7ic89uMDoeeeEa5v8AUw0dnYtZxtw9xdrtceu1RznoQc44p9v4fRbkXN5dzXU23bjhIxz1Cr07\r\nZ5wcZxmhErR3uYGl+IW0aE6dq0cjLGuI3iYNxjIHX39eOOlU9VvtP1porfTLC5e8dyVdm5GTkjqc\r\njk9wF7cV3rWtu8Jhe3iaI8lCgKn8KitdMsbKZ5ba1iidxhii449Paq8zVVIp89tTi9Xj1Wz8OR29\r\n/HIVLj9554OD2BA68Z7nsetbsL6lFawQnw9HMIoBCHa4TJQgZHI4BwMj2rQ1vSBrVklsZjEFkDlg\r\nuc4BGOvvWl0FHSxMpXireZw/h6+lSXUb1tPuZYbm4yTbHcyuCWwQCCRz16du9bdvr+n28aWzNNHK\r\nH2iCeNYmjUtwD0UKqnjnOAOrddPT9OtdMgaG0jKRs28gsTk4A7/QVPLDFPGY5o0kjbqrqCD+BouK\r\npNSd0JAIVt4xbhBCFAjEeNoXHGMcYxRVI6Fp5JPlSLnsszqB9ADgD2FFP3Tmcq3SK+9/5FgWaBty\r\nrEhaQvIUQqWzn0PX7vJz06c8P8l15WZ/+Bc0UVCilojZ66jJFuzEypJGrHgPjJAz19M4/DPrVK91\r\ng6a0a3MQcy7inl8Y2qCc5+tFFLzGormZQguLrWLuaG2it7QREec5YyE55G0YA7c/Wt23soLY7lUs\r\n/d2OT/nmiimkDSRYooopiCiiigAooooAKKKKACiiigD/2Q==");

    }
}

