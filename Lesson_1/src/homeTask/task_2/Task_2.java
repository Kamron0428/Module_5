package homeTask.task_2;

import java.util.concurrent.TimeUnit;

public class Task_2 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i < 100; i++) {
            System.out.println(i);
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
