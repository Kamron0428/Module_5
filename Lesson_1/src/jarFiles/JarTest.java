package jarFiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JarTest {

    public static void main(String[] args) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (true) {
            System.out.println(sdf.format(new Date()));
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
