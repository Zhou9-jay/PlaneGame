package cn.zhou9.game;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Auther:CN.Zhou9
 * @Date:2019/4/25
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class Plane extends GameObject {
    boolean left,up,right,down;
    boolean  live = true;

    @Override
    public void drawSelf(Graphics g) {
        if (live) {
            g.drawImage(img, (int)x, (int)y, null); //死了飞机就没了
            if (left) {
                if (x <= 10) {
                    x -= 0;
                } else {
                    x -= speed;
                }
            }
            if (right) {
                if (x >= (Constant.GAME_WIDTH - 40)) {
                    x += 0;
                } else {
                    x += speed;
                }
            }
            if (up) {
                if (y <= 40) {
                    y -= 0;
                } else {
                    y -= speed;
                }
            }
            if (down) {
                if (y >= (Constant.GAME_HEIGHT - 40)) {
                    y += 0;
                } else {
                    y += speed;
                }
            }
        } else {
        }
    }

    public Plane(Image img, double x, double y) {
        super(img, x, y);
        this.speed = 12;
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null); //获取图片宽度
        this.height = img.getHeight(null); //获取图片高度
    }

    /**
     * 功能描述:按下某个键，增加相应的方向。
     */
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    /**
     * 功能描述:按下某个键，取消相应的方向。
     */
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}
