package cn.zhou9.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 飞机游戏的主窗口
 * @Auther:CN.Zhou9
 * @Date:2019/4/23
 * @Description:cn.zhou9.game
 * @version:1.0
 */
public class MyGameFrame extends Frame {

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    //int planeX = 250;
    //int planeY = 250;

    /*Plane[] planes =
        {
            new Plane(planeImg, 250, 250),
            new Plane(planeImg, 250, 250),
            new Plane(planeImg, 250, 250)
        };*/
    Plane plane = new Plane(planeImg, 250, 250);
    //Shell shell = new Shell();
    Shell[] shells = new Shell[40];

    Explode bao;
    Date startTime = new Date();
    Date endTime;
    int Period; //游戏持续的时间

    private Image offScreenImage = null;

    // 添加双缓冲技术
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT); //这是游戏窗口的宽度和高度
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) { //自动被调用，g相当于一只画笔。
        Color c = g.getColor();
        super.paint(g);
        g.drawImage(bg, 0, 0, null);
        //g.drawImage(plane, planeX, planeY, null);
        //planeX++;
        /*for (Plane temp : planes) {
            temp.drawSelf(g); //画飞机
            //System.out.println(temp);
        }*/
        plane.drawSelf(g);
        //shell.draw(g);

        //画出所有的炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);

            //飞机和炮弹的碰撞检测
            //判断是否跟飞机相碰(矩形)，如果返回true，则相碰。
            //必须都设定宽度高度才会有碰撞检测
            boolean peng = shells[i].getRect().intersects(plane.getRect());
            if (peng) {
                plane.live = false;
                if (bao == null) {
                    bao = new Explode(plane.x, plane.y); //只创建一个对象即可
                    endTime = new Date();
                    Period = (int)((endTime.getTime() - startTime.getTime()) / 1000);
                }
                bao.draw(g);
            }
            //计时功能，给出提示。
            if (!plane.live) {
                g.setColor(Color.red);
                Font f = new Font("微软雅黑", Font.BOLD, 50);
                g.setFont(f);
                g.drawString("时间：" + Period + "秒", 150, 250);
            }
        }
        g.setColor(c);
    }

    //内部类帮助我们反复的重画窗口
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint(); //重画
                try {
                    Thread.sleep(40); //1s = 1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //定义键盘监听的内部类（直接可以调用外部类）
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("按下：" + e.getKeyCode());
            //planes[1].addDirection(e);
            //planes[2].addDirection(e);
            //planes[3].addDirection(e);
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("抬起:" + e.getKeyCode());
            //planes[1].minusDirection(e);
            //planes[2].minusDirection(e);
            //planes[3].minusDirection(e);
            plane.minusDirection(e);
        }
    }

    /**
     * 功能描述:初始化窗口
     */
    public void launchFrame() {
        //在游戏窗口打印标题
        this.setTitle("Made in Zhou9");
        //窗口默认不可见，设为可见。
        this.setVisible(true);
        //窗口大小设置
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        //增加关闭窗口监听，这样可以点关闭图标，关闭游戏程序。
        //this.addWindowFocusListener(new WindowAdapter() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start(); //启动重画窗口的线程
        addKeyListener(new KeyMonitor()); //给窗口添加键盘的监听

        //初始化50个炮弹
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
