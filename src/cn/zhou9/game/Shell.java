package cn.zhou9.game;

import java.awt.*;

/**
 * 炮弹类
 * @Auther:CN.Zhou9
 * @Date:2019/4/26
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class Shell extends GameObject {
    double degree; //角度（随机角度进行飞行）
    public Shell() {
       x = 200;
       y = 200;
       width = 6;
       height = 6;
       speed = 3;
       degree = Math.random()*Math.PI*2; //弧度0 - 2Π
    }

    public void draw(Graphics g) {

        Color c = g.getColor(); //保存之前的颜色

        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, width, height);

        //炮弹沿着任意角度飞去
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);

        //碰到边界反弹
        if (x < 0 || x > Constant.GAME_WIDTH - width) {
            degree = Math.PI - degree;
        }
        if (y < 30 || y > Constant.GAME_WIDTH - height) {
            degree = - degree;
        }

        g.setColor(c); //还回之前的颜色
    }
}
