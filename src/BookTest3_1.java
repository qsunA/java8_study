import vo.BookVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BookTest3_1 {

    public static void main(String[] args) {

        Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };

        process(r1);
        process(r2);

        process((()->System.out.println("Hello Wolrd3")));
    }

    public static void process(Runnable r){
        r.run();
    }
}

