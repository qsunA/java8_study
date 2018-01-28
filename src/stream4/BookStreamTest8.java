package stream4;

import vo.BookVO;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BookStreamTest8 {

    public static void main(String[] args){

        Date date = new Date();

        List<BookVO> list = new ArrayList<>();
        BookVO vo1 = new BookVO("Resmiserable","11","victor wigo",1503,30000, date,"minum");
        BookVO vo2 = new BookVO("Karamajofe","22","dostoyepski",2005,36000, date,"yolin");
        BookVO vo3 = new BookVO("rolita","33","naboski",700,12000, date,"minum");
        BookVO vo4 = new BookVO("dragon of eden","44","kal seigan",307,8000, date,"yolin");
        BookVO vo5 = new BookVO("joke","55","millan kundera",602,10000, date,"munhack");
        BookVO vo6 = new BookVO("Resmiserable","66","victor wigo",1764,33000, date,"munhack");
        BookVO vo7 = new BookVO("Sapiens","77","Harari",636,23000, date,"minum");
        BookVO vo8 = new BookVO("Animal Fram","88","George orwell",193,8500, date,"yolin");
        BookVO vo9 = new BookVO("1984","99","George orwell",442,9000, date,"minum");
        BookVO vo10 = new BookVO("My name is red","100","Orhan pamuk",1000,32000, date,"yolin");
        BookVO vo11 = new BookVO("mouse","101","supigerlman",602,10000, date,"munhack");
        BookVO vo12 = new BookVO("martian","102","Andy wier",600,15000, date,"munhack");

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);
        list.add(vo7);
        list.add(vo8);
        list.add(vo9);
        list.add(vo10);
        list.add(vo11);
        list.add(vo12);

        // yolin 출판사 책 이름 2권을 추출
        // 외부 반복
        List<String> result = new ArrayList<>();

        for(BookVO vo : list){
            if("yolin".equals(vo.getPublisher())){
                result.add(vo.getTitle());
            }
        }

        for(int i = 0 ; i<2; i++){
            System.out.println(result.get(i));
        }

        System.out.println("=========Stream===============");
        // 내부 반복
        List<String> titles = list.stream()
                                   .filter(b -> "yolin".equals(b.getPublisher()))
                                   .map(BookVO :: getTitle)
                                   .limit(2)
                                   .collect(toList());

        titles.forEach(System.out :: println);

        System.out.println("=================filter========================");
        // filtering
        List<BookVO> gorgeBooks = list.stream()
                                       .filter(b -> "George orwell".equals(b.getWriter()))
                                       .collect(toList());

        gorgeBooks.forEach(System.out :: println);

        System.out.println("=================distinct before========================");
        List<String> resTitle = list.stream()
                                    .filter(b->"Resmiserable".equals(b.getTitle()))
                                    .map(BookVO :: getTitle)
                                    .collect(toList());

        resTitle.forEach(System.out :: println);

        System.out.println("=================distinct after========================");
        List<String> resDistinctTitle = list.stream()
                .filter(b->"Resmiserable".equals(b.getTitle()))
                .map(BookVO :: getTitle)
                .distinct()
                .collect(toList());

        resDistinctTitle.forEach(System.out :: println);

        System.out.println("=================skip========================");
        //skip
        List<BookVO> skipBooks = list.stream()
                .filter(b -> b.getPrice()>10000)
                .skip(3)
                .collect(toList());

        skipBooks.forEach(System.out :: println);

       System.out.println("=================map========================");

        List<Integer> bookTItleLength = list.stream()
                                            .filter(b-> b.getTitle().length()>10)
                                            .map(BookVO :: getTitle)
                                            .map(String :: length)
                                            .collect(toList());

        bookTItleLength.forEach(System.out :: println);

        System.out.println("=================flatmap========================");

        List<String> longTItleList = list.stream()
                .filter(b-> b.getTitle().length()>10)
                .map(BookVO :: getTitle)
                .collect(toList());

        longTItleList.forEach(System.out :: println);

         List<String> uniqueWords = longTItleList.stream()
                    .map(title -> title.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(toList());

        uniqueWords.forEach(System.out :: println);

        System.out.println("=================match========================");

        if(list.stream().anyMatch(b->"Resmiserable".equals(b.getTitle()))){
            System.out.println("한 권 이상의 레미제라블 책이 있습니다.");
        }

        if(list.stream().allMatch(b->b.getPrice()>10000)){
            System.out.println("모든 책이 만원을 넘습니다.");
        }else if(list.stream().allMatch(b->b.getPrice()>5000)){
            System.out.println("모든 책들이 오천원을 넘습니다.");
        }else{
            System.out.println("책들이 모두 저렴한 가격입니다.");
        }

        if(list.stream().noneMatch(b->"Tolstoy".equals(b.getWriter()))){
            System.out.println("여기에는 톨스토이의 책이 없습니다.");
        }

       System.out.println("=================find========================");
        // filtering
        List<BookVO> gorgeBooks2 = list.stream()
                .filter(b -> "George orwell".equals(b.getWriter()))
                .collect(toList());

        gorgeBooks2.forEach(System.out :: println);

        Optional<BookVO> gorgeBooks3 = list.stream()
                .filter(b -> "George".equals(b.getWriter()))
                .findAny();

        System.out.println(gorgeBooks3);

        list.parallelStream()
                .filter(b -> "George orwell".equals(b.getWriter()))
                .findFirst()
                .ifPresent(b->System.out.println(b));

        System.out.println("=================Reducing========================");

        int sum =0;
        for (BookVO bookVO : list) {
            sum += bookVO.getPrice();
        }
        System.out.println("모든 책의 값 : "+sum);

        int reSum = 0;
        reSum = list.stream()
                .map(BookVO :: getPrice)
                .reduce(0,(a,b) -> a+b);

        System.out.println("모든 책의 값2(확인 필요!) : "+reSum);

        int minSum = list.stream()
                .filter(b-> b.getPrice()<5000)
                .map(BookVO :: getPrice)
                .reduce(0,(a,b) -> a+b);

        System.out.println("5천원 이하의 책들의 합계 : "+minSum);

        Optional<Integer> minSum2 = list.stream()
                .filter(b-> b.getPrice()<5000)
                .map(BookVO :: getPrice)
                .reduce((a,b) -> a+b);

        System.out.println("5천원 이하의 책들의 합계 : "+minSum2);

        Optional<Integer> min = list.stream()
                                        .map(BookVO :: getPrice)
                                        .reduce(Integer::min);
        System.out.println("가장 저렴한 책 가격 :"+min);

        Optional<Integer> min2 = list.stream()
                .map(BookVO :: getPrice)
                .reduce((x,y) -> x<y ? x:y);
        System.out.println("가장 저렴한 책 가격 :"+min2);


        System.out.println("=================iterate========================");

        Stream.iterate(0,n->n+2)
                .limit(5)
                .forEach(System.out :: println);

        System.out.println("=================generate========================");

        Stream.generate(Math :: random)
                .limit(5)
                .forEach(System.out :: println);
    }
}
