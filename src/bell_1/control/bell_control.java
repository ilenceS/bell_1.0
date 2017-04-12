package bell_1.control;

import bell_1.dao.JpanelDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bell_1.dao.JpanelDao.getRt;

/**
 * Created by nietz on 17-4-11.
 */
public class bell_control {
    public static void drawBell(JPanel a, int x, int y){
        JpanelDao.DrawBell db=new JpanelDao.DrawBell();
        a.add(db);
        db.setBounds(x,y,250,250);
    }
    public static JpanelDao.SetGetTimes setGetTimes(JPanel a, int x, int y){
        JpanelDao.SetGetTimes js=new JpanelDao.SetGetTimes();
        a.add(js);
        js.setBounds(x,y,100,50);
        return js;
    }
    public static JpanelDao.AlarmClock alarmClock(AlarmJTap a, int x, int y){
        JpanelDao.AlarmClock ac=new JpanelDao.AlarmClock();
        a.add(ac);
        ac.setBounds(x,y,100,45);
        return ac;
    }

    public static class ClockJTap extends  JPanel{
        public JpanelDao.SetGetTimes sg=null;
        public ClockJTap(){
            setLayout(null);
            drawBell(this,0,0);
            sg=setGetTimes(this,280,158);
        }
        public void setGetTimesRun(JpanelDao.SetGetTimes a){
            int ss;
            int mm;
            int HH;
            ss = getRt().getss();
            if(ss==0){
                mm=getRt().getss();
                if(mm<10){
                    a.mmt.setText("0"+String.valueOf(mm));
                }
                else
                    a.mmt.setText(String.valueOf(mm));
            }
            if(getRt().getmm()==0){
                HH=getRt().getHH();
                if(HH<10){
                    a.HHt.setText("0"+String.valueOf(HH));
                }
                else
                    a.HHt.setText(String.valueOf(HH));
            }
            if(ss<10){
                a.sst.setText("0"+String.valueOf(ss));
            }
            else
                a.sst.setText(String.valueOf(ss));
        }
    }

    public static class AlarmJTap extends JPanel{
        public JpanelDao.AlarmClock ac[]=new JpanelDao.AlarmClock[5];
        JButton addAlarm=new JButton("增加闹钟");
        public static int i=1;
        AlarmJTap a=this;
        public AlarmJTap(){
            setLayout(null);
            ac[0]=alarmClock(this,220,40);
            add(ac[0]);
            add(addAlarm);
            addAlarm.addActionListener(new AddAlarmAction());
            addAlarm.setBounds(100,40,80,25);
            addAlarm.setBorder(new EmptyBorder(0,0,0,0));
        }
        class AddAlarmAction implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(i<5){
                    ac[i]=alarmClock(a,220,40+i*40);
                    i+=1;
                }
                else{
                    JOptionPane.showMessageDialog(null,"最多只能添加 5 个闹钟！","警告:",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        public void alarmClockRun(JpanelDao.AlarmClock a){
            a.HH1 = getRt().getHH();
            a.MM1 = getRt().getmm();
            if (a.alarmHelp == 0 && a.HH1 == a.hh1 && a.MM1 == a.mm1) {
                JOptionPane.showMessageDialog(null, "闹钟" + a.HH1 + ":" + a.MM1 + " 时间到！", "闹钟", JOptionPane.INFORMATION_MESSAGE);
                a.alarmHelp = 1;
            }
            if (a.alarmHelp == 1 && a.HH1 == a.hh1 && a.MM1 != a.mm1) {
                a.alarmHelp = 0;
            }
        }
    }
}
