import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCpu {

    public static void main(String[] args) {
        /*for (int i=0; ; i++){
            if(i%10000 ==0) {
                System.out.println("for:" + i);
            }
        }*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(convertCalendarToStringAddMonth(-1)));
    }

    public static Date convertCalendarToStringAddMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.add(Calendar.MONTH, month);
        } catch (Exception e) {
        }
        return calendar.getTime();
    }


}
