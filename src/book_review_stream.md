# CHAPTER 4. 스트림 소개

## 4.1 스트림이란 무엇인가?
        데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소
        * 선언형
        * 조립할 수 있음
        * 병렬화 
        
   ## 4.2 스트림 시작하기
        * 연속된 요소 - 특정된 요소 형식으로 이루어진 연속된 값 집합의 인터페이스 제공
            - 컬렉션의 주제는 데이터. 스트림의 주제는 계산
            
        * 소스 - 컬렉션, 배열 I/O 자원 등 데이터 제공 소스로부터 데이터를 소비 
        * 데이터 처리 연산 - filter, map, reduce, find, match, sort 등으로 데이터 조작
        * 파이프 라이닝 - 스트림 연산끼리 연결해서 커다란 파이프라인을 구성할 수 있도록 스트림 자센을 반환 
        * 내부 반복
        
        
    4.3 스트림과 컬렉션 
        * 데이터를 언제 계산하는냐의 차이 
        * 컬렉션은 현재 자료구조가 포함하는 모든 값을 메모리에 저장 
        * 스트림은 요청할 때만 요소를 계산하는 고정된 자료 구조. 게으르게 만들어지는 컬렉션과 같음 


```java
List<String> titles = list.stream()
                        .filter(b -> "yolin".equals(b.getPublisher()))
                                              .map(BookVO :: getTitle)
                                              .limit(2)
                                              .collect(toList());
           
titles.forEach(System.out :: println); 
```
        
        4.3.1 딱 한 번만 탐색할 수 있다.
            탐색된 스트림의 요소는 소비된다. 다시 탐색하려면 초기 데이터 소스에서 새로운 스트림을 만들어야함
        4.3.2 외부 반복과 내부 반복
            컬렉션은 사용자가 직접 요소를 반복 (외부 반복 external iteration)
            스트림은 반복을 알아서 처리하고 결과 스트림을 어딘가에 저장 (내부 반복 internal iteration)
            * 내부 반복은 작업을 투명하게 병렬 처리하거나, 최적화된 다양한 순서로 처리 가능 
        
    4.4 스트림 연산
        스트림 연산은 중간 연산(intermedate operation) 과 최종 연산 (terminal operation)으로 나뉨
        4.4.1 중간 연산 
            다른 스트림을 반환
            여러 중간 연산을 연결하여 질의를 만들 수 있음
            스트림 파이프라인에 실행하기 전까지 아무 연산을 수행하지 않음 (lazy)
            
        4.4.2 최종 연산
            최종 연산에 의해 List, Interger, void 등 스트림 이외의 결과가 반환
        
        4.4.3 스트림 이용하기 
            * 질의를 수행할 데이터 소스 
            * 스트림 파이프라인을 구성할 중간 연산 연결
            * 스트림 파이프라인을 실행하고 결과를 만들 최종 연산
                 
# CHAPTER 5. 스트림 활용  

   ## 5.1 필터링과 슬라이싱
        5.1.1 프레디케이트로 필터링
            Predicate<T>  (T-> boolean)을 인수로 받아서 프레디케이트와 일치하는 모든 요소를 포함하는 스트림 반환
             
        5.1.2 고유 요소 필터링
            distinct 메서드 제원 (객체의 hashcode, equals 등으로 결정) 
            // String으로 2권이 있는 책을 검색 했을 때, distinct 로 한권만 나오게 할 수 있음
                             
   ```java
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
```                  
        
        5.1.3 스트림 축소 
            주어진 사이즈 이하의 크기를 갖는 새로운 스트림을 반환 limit(n)
        
        5.1.4 요소 건너뛰기
            처음 n개 요소를 제외한 스트림을 반환하는 skip(n) 메서드 
            
            // 기존 3갠너 건너뛰고 시작
            '''        System.out.println("=================skip========================");
                       //skip
                       List<BookVO> skipBooks = list.stream()
                               .filter(b -> b.getPrice()>10000)
                               .skip(3)
                               .collect(toList());
               
                       skipBooks.forEach(System.out :: println);
                 
   ## 5.2 매핑
        SQL의 테이블에서 특정 열만 선택하는 것과 같은 작업. 스트림 API 에서는 map과 flatMap 등을 제공한다.
        
        5.2.1 스트림의 각 요소에 함수 적용하기
            인수로 제공된 함수는 각 요소에 적용되며 함수를 적용한 결과가 새로운 요소로 매핑
            map() 매핑한다. 
            -- BookVO 의 모든 변수를 가지는 것이 아닌, 특정 변수만을 가지는 리스트로 변환할 때 map함수를 이용한다.
            -- Select title from book; 에서 'title'에 해당하는 것이 map()의 기능 
            
        5.2.2 스트림 평면화
            스트림의 각 값을 다른 스트림으로 만든 다음에 모든 스트림을 하나의 스트림으로 연결하는 기능을 수행
            
            // flatMap은 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑한다.
            '''List<String> uniqueWords = longTItleList.stream()
                                   .map(title -> title.split(""))
                                   .flatMap(Arrays::stream)
                                   .distinct()
                                   .collect(toList());
            
   ## 5.3 검색과 매칭
        특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리에 자주 사용
        allMatch, anyMatch, noneMatch, findFirst, findAny 등 제공 
        
        5.3.1 프레디케이트가 적어도 한 요소와 일치하는지 확인
            anyMatch 메서드를 이용하며 boolen을 반환하는 최종 연산자이다. 
            
            '''
            if(list.stream().anyMatch(b->"Resmiserable".equals(b.getTitle()))){
                           System.out.println("한 권 이상의 레미제라블 책이 있습니다.");
                       }
        5.3.2 프레디케이트가 모든 요소와 일치하는지 검사
            allMatch 모든 요소가 일치하는지 검사
            '''
            if(list.stream().allMatch(b->b.getPrice()>10000)){
                        System.out.println("모든 책이 만원을 넘습니다.");
                    }else if(list.stream().allMatch(b->b.getPrice()>5000)){
                        System.out.println("모든 책들이 오천원을 넘습니다.");
                    }else{
                        System.out.println("책들이 모두 저렴한 가격입니다.");
                    }
            
            noneMatch 반대로 모든 요소가 불일치 하는지 검사
            '''
            if(list.stream().noneMatch(b->"Tolstoy".equals(b.getWriter()))){
                        System.out.println("여기에는 톨스토이의 책이 없습니다.");
                    }
                    
            ** 쇼트서킷 평가
                전체 스트림을 처리하지 않더라도 원하는 요소를 확인했다면 결과를 반환할 수 있다. 
                스트림을 유한한 크기로 줄일 수 있는 유용한 연산
            
        5.3.3 요소 검색
            findAny 현재 스트림에서 임의의 요소를 반환한다. 
            * collect  vs findAny
            - collect는 스트림에 있는 모든 결과값을 가지고 오는 반면, findAny는 그 중 하나만 찾은 후 즉시 실행을 종료한다. 
            쇼트서킷이냐의 여부
            
            ```
            List<BookVO> gorgeBooks2 = list.stream()
                            .filter(b -> "George orwell".equals(b.getWriter()))
                            .collect(toList());
            
                    gorgeBooks2.forEach(System.out :: println);
            
                    Optional<BookVO> gorgeBooks3 = list.stream()
                            .filter(b -> "George".equals(b.getWriter()))
                            .findAny();
            
                    System.out.println(gorgeBooks3);
            
            *Optional 
            값의 존재나 부재 여부를 표현하는 컨테이너 클래스 
            
            list.stream()
                            .filter(b -> "George orwell".equals(b.getWriter()))
                            .findAny()
                            .ifPresent(b->System.out.println(b));
            
        5.3.4 첫 번째 요소 찾기     
            findFirst : 첫 번째 요소 찾기
            * findFirst vs findAny
                병렬성의 차이 
                병렬 실행에서는 첫 번째 요소를 찾기 힘듬. 
                
   ## 5.4 리듀싱  
        스트림 요소를 조합해서 더 복잡한 질의를 표현
        리듀싱 연산 : 모든 스트림 요소를 처리해서 값으로 도출
        
        5.4.1 요소의 합
            reduce(초기값, 두 요소를 조합해서 새로운 값을 만드는 BinaryOperator<T>)
            reduce로 람다식을 넘기면 모든 요소에 해당 람다식이 적용된 결과를 볼 수 있다. 
            
            // sum은 0으로 초기화됨. list에 값이 없더라도 sum은 0이 나온다. 
            //0부터 시작하여 for문을 돌며 sum 변수에다가 각 요소가 누적되어 합해진다.
            int sum =0;
                    for (BookVO bookVO : list) {
                        sum += bookVO.getPrice();
                    }
                    System.out.println("모든 책의 값 : "+sum);
            
            // for문과 같은 효과. 0에서부터 값이 누적되어서 합해진다.
                    int reSum = 0;
                    reSum = list.stream()
                            .map(BookVO :: getPrice)
                            .reduce(0,(a,b) -> a+b);
            초기값이 없는 reduce의 경우 아무 요소가 없는 스트림이 있을 수 있으므로 Optional이 반환된다.
            
        5.4.2 최대값 최소값
             reduce연산은 새로운 값을 이용해서 스트림의 모든 요소를 소비할 때까지 람다를 반복 수행한다. 
             
             ** reduce 메서드
             내부 반복이 추상화되면서 내부 구현에서 병렬로 reduce를 실행할 수 있게된다. 
             reduce에 넘겨준 람다의 상태가 바뀌지 말하야 하며 연산이 어떤 순서로 실행되더라도 결과가 바뀌지 않는 구조
             숫자를 전부 합할때, 병렬적으로 분할하여 각자 합한 후 다시 합하는 방식을 이용하는 것과 같다. 
             그러나 이런 가변 누적자 패턴은 병렬화에는 어울리지 않음
    
   ## 5.5 실전연습
    
   ## 5.6 숫자형 스트림
        박싱과정에서 일어나는 효율성과 관련 
        
        5.6.1 기본형 특화 스트림
            maptoInt(), mapToDouble(), mapToLong() 각 타입에 맞는 IntStream, DoubleStream, LongStream 등을 반환
            max,min,average 등의 메서드 이용 가능
            * 객체 스트림으로 복원하기 
                intStream.box() : box 메서드를 이용하여 원상태인 특화되지 않는 스트림으로 복원
            * OptionalInt, OptionalDouble, OptionalLong 등 제공
        
        5.6.2 숫자 범위        
            특정 범위 숫자를 이용해야할 때 range(), rangeClosed() 를 이용.
            range()  시작값과 종료값이 결과에 포함되지 않음
            rangeClosed() 시작값과 종료값을 결과에 표현함
    
   ## 5.7 스트림 만들기 
        스트림은 데이터 질의를 표현하는 강력한 도구. 
        
        5.7.1 값으로 스트림 만들기         
            임의의 수를 인수로 받는 정적 메서드 Stream.of를 이용해서 스트림을 만들 수 있다. 
        5.7.2 배열로 스트림 만들기 
            Arrays.stream 을 이용하여 스트림을 만들 수 있다. 
        5.7.3 파일로 스트림 만들기
            java.nio.file.Files에서는 많은 정적 메서드가 스트림을 반환한다. 
            
        5.7.4 함수로 무한 스트림 만들기 
            함수에서 스트림을 만들 수 있는 Stream.iterate, Stream.genrate 제공
            크기가 고정되지 않는 무한 스트림 (infinite stream)을 만들 수 있다. 
            
            * iterate
                람다를 인수로 받아서 새로운 값을 끊임없이 생산할 수 있다. 
                기존 결과를 의존해서 순차적으로 연산을 수행
                언바운드 스트림 (unbounded stream) : 
                iterate는 요청할 때 마다 값을 생산할 수 있으며 끝이 없는 무한 스트림을 만든다.  
                
                ``` 
                // 제한을 걸어두지 않으면 끊임없이 스트림이 생성되므로 limit 함수를 이용해야한다.
                        Stream.iterate(0,n->n+2)
                                //.limit(5)
                                .forEach(System.out :: println);
            * generate
                generate 요구할 때 값을 계산하는 무한 스트림을 만들 수 있다. 
                generate는 Supplier<T>를 인수로 받아 새로운 값을 계산한다. 
                