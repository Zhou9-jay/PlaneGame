package cn.zhou9.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * 加载图片代码
 * @Auther:CN.Zhou9
 * @Date:2019/4/23
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class GameUtil {
    //工具类最好将构造器私有化
    private GameUtil() {}

    /**
     * 返回指定路径文件的图片对象
     * @param path
     * @return
     */
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bi;
    }
}
