package bell_1.dao;


import bell_1.model.Clock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;

/**
 * Created by nietz on 17-4-11.
 */
public class JpanelDao extends JFrame{
    private static Clock rt=new Clock();

    public static Clock getRt() {
        return rt;
    }

    public static class DrawBell extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            drawOuter(g);
            paintSecond(g);
            paintMinute(g);
            paintHour(g);
        }
        void drawOuter(Graphics g){
            Graphics2D g2=(Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawOval(31,61,150,150);
            g.drawOval(28,58,156,156);

            g2.setStroke(new BasicStroke(5.0f));
            g.drawLine(106,136,106,136);

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2.0f));
            for(int i=0;i<60;i++){
                int angle=i*6;
                double []xx=getxy(1,angle);
                if(i%5==0) {
                    double[] xx5 = getxy(0.92, angle);
                    Line2D lines=new Line2D.Double(xx5[0]+106,136-xx5[1],xx[0]+106,136-xx[1]);
                    g2.draw(lines);
                }
                Ellipse2D rect = new Ellipse2D.Double(105 + xx[0], 135 - xx[1], 75 * 0.04, 75 * 0.04);
                g2.fill(rect);
            }
        }
        void paintSecond(Graphics g){
            Graphics2D g2=(Graphics2D)g;
            g2.setStroke(new BasicStroke(1.0f));
            int seconds=rt.getss();
            int angle=270+seconds*6;
            double[]xx=getxy(0.9,angle);
            double[]xx1=getxy(0.15,angle+180);
            Line2D lines=new Line2D.Double(106+xx1[0],136+xx1[1],106+xx[0],xx[1]+136);
            g2.draw(lines);
        }
        void paintMinute(Graphics g){
            Graphics2D g2=(Graphics2D)g;
            g2.setStroke(new BasicStroke(2.0f));
            int minute=rt.getmm();
            int angle=270+minute*6;
            double[]xx=getxy(0.8,angle);
            Line2D lines=new Line2D.Double(106,136,106+xx[0],xx[1]+136);
            g2.draw(lines);
        }
        void paintHour(Graphics g){
            Graphics2D g2=(Graphics2D)g;
            g2.setStroke(new BasicStroke(2.0f));
            int hour=rt.getHH();
            int minute=rt.getmm();
            double angle=270+(hour+minute/60.0)*30;
            double[]xx=getxy(0.6,angle);
            Line2D lines=new Line2D.Double(106,136,106+xx[0],xx[1]+136);
            g2.draw(lines);
        }
        double [] getxy(double r,double angle){
            double radian=Math.toRadians(angle);
            double x=r*75*Math.cos(radian);
            double y=r*75*Math.sin(radian);
            return new double[]{x,y};
        }
    }

    public static class SetGetTimes extends JPanel{
        public JTextField HHt=new JTextField();
        public JTextField mmt=new JTextField();
        public JTextField sst=new JTextField();
        int HH=rt.getHH();
        int mm=rt.getmm();
        int ss=rt.getss();
        public SetGetTimes(){
            setLayout(null);
            add(HHt);
            add(mmt);
            add(sst);
            HHt.setBounds(0,0,25,25);
            mmt.setBounds(24,0,25,25);
            sst.setBounds(48,0,25,25);
            myGetTime();
            HHt.addActionListener(new T1());
            mmt.addActionListener(new T2());
            sst.addActionListener(new T3());
        }
        class T1 implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                rt.setHH(Integer.valueOf(HHt.getText()));

                HH=rt.getHH();
                if(HH<10){
                    HHt.setText("0"+String.valueOf(HH));
                }
                else
                    HHt.setText(String.valueOf(HH));
            }
        }
        class T2 implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                rt.setmm(Integer.valueOf(mmt.getText()));
                mm=rt.getmm();
                if(mm<10){
                    mmt.setText("0"+String.valueOf(mm));
                }
                else
                    mmt.setText(String.valueOf(mm));
            }
        }
        class T3 implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                rt.setss(Integer.valueOf(sst.getText()));

            }
        }
        void myGetTime(){
            if(mm<10){
                mmt.setText("0"+String.valueOf(mm));
            }
            else
                mmt.setText(String.valueOf(mm));
            if(HH<10){
                HHt.setText("0"+String.valueOf(HH));
            }
            else
                HHt.setText(String.valueOf(HH));

            if(ss<10){
                sst.setText("0"+String.valueOf(ss));
            }
            else
                sst.setText(String.valueOf(ss));
        }
    }

    public static class AlarmClock extends JPanel {
        JTextField HHat = new JTextField("00");
        JTextField mmat = new JTextField("00");
        JButton setAlarm = new JButton("设置");
        public int alarmHelp = 0;
        public int hh1;
        public int mm1;
        public int HH1;
        public int MM1;

        public AlarmClock() {
            setLayout(null);
            add(HHat);
            add(mmat);
            add(setAlarm);
            setAlarm.addActionListener(new setAlarms());
            HHat.setBounds(0, 0, 25, 25);
            mmat.setBounds(24, 0, 25, 25);
            setAlarm.setBounds(58, 0, 40, 25);
            setAlarm.setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        class setAlarms implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                hh1 = Integer.valueOf(HHat.getText());
                mm1 = Integer.valueOf(mmat.getText());
            }
        }

    }
}
