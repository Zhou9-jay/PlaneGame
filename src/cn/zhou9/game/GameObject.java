package cn.zhou9.game;

import sun.net.idn.Punycode;

import java.awt.*;

/**
 * 游戏物体的父类
 * @Auther:CN.Zhou9
 * @Date:2019/4/25
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class GameObject {
    Image img;
    double x, y;
    int speed, width, height;

    public void drawSelf(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }
    
    /**
     * 功能描述:返回物体所在的矩形，便于后续的碰撞检测。
     * @return:
     */
    public Rectangle getRect() {
        return new Rectangle((int)x, (int)y, width, height); //根据长度宽度返回矩形
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }
    public GameObject() {
    }
}
