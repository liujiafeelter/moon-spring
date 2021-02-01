import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 水印中文乱码问题
 * 服务器上没有相应的字体库
 * linux：/usr/share/fonts
 * windows：c:\windows\Fonts
 *
 * 查看服务器是否安装字体库: # fc-list   fc-list :lang=zh-cn
 * 上传宋体，从windos上传即可  simsun.ttc
 * 字体生效:fc-cache -fv
 */
public class ImageUtils {

    private static final String PICTRUE_FORMATE_JPG = "jpg";

    /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));

            //倾斜水印开始,图片也是倾斜的
            g.rotate(Math.toRadians(-45),
                    (double) bufferedImage.getWidth() / 2,
                    (double) bufferedImage.getHeight() / 2);
            //倾斜水印结束

            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }

            //   g.drawString(pressText, x, y + height_1);
            g.drawString(pressText, (int) (x*1.5), (y + height_1)/3);
            //           g.drawString(pressText, (int) (x*1.5), (y + height_1)/2);
            //      g.drawString(pressText, (int) (x*1.5), (int) ((y + height_1)*1.5));
            //     g.drawString(pressText, x/2, (int) ((y + height_1)*1.5));


            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    public static void main(String[] args) throws IOException {
        pressText("C:\\Users\\liujia54\\Desktop\\图片\\111.png", "审核专用", "宋体",
                Font.BOLD | Font.ITALIC, 120, Color.RED, -1, 1000, 0.30f);//0.30  0.62   y=-1
        System.out.println("给图片添加水印文字结束...");
    }


}
