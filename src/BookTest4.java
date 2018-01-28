import vo.BookVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookTest4 {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Date date = new Date();

        List<BookVO> list = new ArrayList<>();
        BookVO vo1 = new BookVO("Resmiserable","11","victor wigo",1500,30000, date,"minum");
        BookVO vo2 = new BookVO("Karamajofe","22","dostoyepski",2000,36000, date,"yolin");
        BookVO vo3 = new BookVO("rolita","33","naboski",700,12000, date,"minum");
        BookVO vo4 = new BookVO("dragon of eden","44","kal seigan",300,8000, date,"yolin");
        BookVO vo5 = new BookVO("joke","55","millan kundera",600,10000, date,"munhack");
        BookVO vo6 = new BookVO("Resmiserable","66","victor wigo",1700,33000, date,"munhack");

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);

        List<BookVO> returnList= filterBook(list, new BookPredicate() {
                    @Override
                    public boolean test(BookVO book) {
                        return "joke".equals(book.getTitle());
                    }
                }
        );
                System.out.println("joke 로 구분");
        for (BookVO vo :returnList){
            System.out.println(vo.getIsbn()+": "+vo.getTitle());
        }

        List<BookVO> returnlist2=filterBook(list, new BookPredicate() {
            @Override
            public boolean test(BookVO book) {
                return 10000<book.getPrice();
            }
        });

        System.out.println("가격으로 구분");
        for(BookVO vo : returnlist2){
            System.out.println(vo.getIsbn()+":"+vo.getTitle()+":" + vo.getPrice());
        }


    }



    public static List<BookVO> filterBook(List<BookVO> invetory, BookPredicate p){
        List<BookVO> result = new ArrayList<>();
        for(BookVO book:invetory){
            if(p.test(book)){
                result.add(book);
            }
        }

        return result;
    }
}

interface BookPredicate{
    boolean test (BookVO book);
}