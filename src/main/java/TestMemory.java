import java.util.ArrayList;
import java.util.List;

/**
 * -Xms5m -Xmx5m
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class TestMemory {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i =0;
        while (true){
            i++;
            if(i%10000 == 0){
                System.out.println("i:" + i);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            list.add(new Object());
        }
    }


}
