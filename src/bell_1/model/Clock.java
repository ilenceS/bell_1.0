package bell_1.model;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nietz on 17-4-11.
 */
public class Clock {
    static Calendar calendar=Calendar.getInstance();
    private static int HH=calendar.get(Calendar.HOUR_OF_DAY);
    private static int mm=calendar.get(Calendar.MINUTE);
    private static int ss=calendar.get(Calendar.SECOND);

    public void setHH(int HH) {
        calendar.set(Calendar.HOUR_OF_DAY,HH);
    }

    public void setmm(int mm) {
        calendar.set(Calendar.MINUTE,mm);
    }

    public void setss(int ss) {
        calendar.set(Calendar.SECOND,ss);
    }

    public int getHH(){
        HH=calendar.get(Calendar.HOUR_OF_DAY);
        return HH;
    }
    public int getmm(){
        mm=calendar.get(Calendar.MINUTE);
        return mm;
    }
    public int getss(){
        ss=calendar.get(Calendar.SECOND);
        return ss;
    }
    public Clock(){
        Timer clockTimer= new Timer();
        clockTimer.scheduleAtFixedRate(new timerTask(),0,1000);
    }
    class timerTask extends TimerTask {
        @Override
        public void run() {
            calendar.add(Calendar.SECOND,1);
            //System.out.println(getHH()+":"+getmm()+":"+getss());
        }
    }
    public static void main(String args[]){
        Clock a=new Clock();
    }
}
