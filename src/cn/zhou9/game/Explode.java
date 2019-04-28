package cn.zhou9.game;

import java.awt.*;

/**
 * 爆炸类
 * @Auther:CN.Zhou9
 * @Date:2019/4/26
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class Explode {
    double x, y; //爆炸的位置

    static Image[] imgs = new Image[16];
    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
            imgs[i].getWidth(null);
        }
    }

    int count;

    public void draw(Graphics g) {
        if (count <= 15) { //轮循加载图片
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
