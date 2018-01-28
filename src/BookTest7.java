import vo.BookVO;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BookTest7 {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Date date = new Date();

        List<BookVO> list = new ArrayList<>();
        BookVO vo1 = new BookVO("Resmiserable","11","victor wigo",1503,30000, date,"minum");
        BookVO vo2 = new BookVO("Karamajofe","22","dostoyepski",2005,36000, date,"yolin");
        BookVO vo3 = new BookVO("rolita","33","naboski",700,12000, date,"minum");
        BookVO vo4 = new BookVO("dragon of eden","44","kal seigan",307,8000, date,"yolin");
        BookVO vo5 = new BookVO("joke","55","millan kundera",602,10000, date,"munhack");
        BookVO vo6 = new BookVO("Resmiserable","66","victor wigo",1764,33000, date,"munhack");

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);

        List<Integer> evenNumbers = new ArrayList<>();
        evenNumbers.add(4);
        evenNumbers.add(5);
        evenNumbers.add(6);
        evenNumbers.add(7);
        evenNumbers.add(8);

        list.sort(new Comparator<BookVO>() {
            @Override
            public int compare(BookVO o1, BookVO o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });

        System.out.println("정렬1");
        for (BookVO vo :list){
            System.out.println(vo.getIsbn()+": "+vo.getTitle()+":"+vo.getPrice());
        }

        list.sort(
                (BookVO b1, BookVO b2) -> b2.getPrice().compareTo(b1.getPrice())
        );

        System.out.println("정렬2");
        for (BookVO vo :list){
            System.out.println(vo.getIsbn()+": "+vo.getTitle()+":"+vo.getPrice());
        }

    }



}

