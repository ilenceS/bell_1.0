package bell_1.view;

import bell_1.control.bell_control;
import bell_1.dao.SwingConsole;
import javax.swing.*;
import java.util.*;

/**
 * Created by nietz on 17-4-11.
 */
public class bell_view extends JFrame{
    bell_control bc=new bell_control();
    bell_control.ClockJTap cj=new bell_control.ClockJTap();
    bell_control.AlarmJTap ac=new bell_control.AlarmJTap();
    JTabbedPane jtp=new JTabbedPane();
    public bell_view(){
        jtp.addTab("时钟",cj);
        jtp.addTab("闹钟",ac);
        add(jtp);
        java.util.Timer drawTimer= new java.util.Timer();
        drawTimer.scheduleAtFixedRate(new drawTimerTask(),0,1000);
    }
    class drawTimerTask extends TimerTask{
        @Override
        public void run(){
            repaint();
            cj.setGetTimesRun(cj.sg);
            for(int i=0;i<ac.i;i++) {
                ac.alarmClockRun(ac.ac[i]);
            }
        }
    }

    public static void main(String args[]){
        SwingConsole.run(new bell_view(),420,400);
    }
}
