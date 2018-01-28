CHAPTER 3. 람다 표현식

    3.1 람다란 무엇인가
        익명함수를 단순화
        파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트 등 가질 수 있음
        * 익명 
        * 함수 - 특정 클래스에 종속되지 않음
        * 전달 - 메서드 인수로 전달하거나 변수로 저장 가능
        * 간결성
        
        람다는 세 부분으로 나뉘어져 있음
        * 파라미터 리스트
        * 화살표 (->) : 람다의 파라미터 리스트와 바디를 구분
        * 바디 : 람다의 반환 값이 해당하는 표현식
        
        (parameters) -> expression
        (parameters) ->{statements};
        
        expression : 어떤 다른 값을 산출하는 값, 상수, 변수, 연산자, 함수의 조합
        statemenets :동작을 수행하는 기본이 되는 요소로
        
        람다 예제
        - 불린 표현식  : (List<String> list) -> list.isEmpty()
        - 객체 생성 : () -> new Apple(10)
        - 객체에서 소비 : (Apple a) -> {System.out.println(a.getWeight());}
        - 객체에서 선택/ 추출 : (String s) -> s.length()
        - 두 값을 조합 : (int a, int b) -> a* b
        - 두 객체 비교 : (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
          

    3.2 어디에, 어떻게 사용할까?
        3.2.1 함수형 인터페이스
        * 정확히 하나의 추상 메서드를 지정하는 인터페이스(디폴트 메서드 포함 가능)
        * Comparator, Runnable, ActionListener, Callable, PrivilegedAction
        * 전체 표현식을 함수형 인터페이스의 인스턴스로 취급
        
        3.2.2 함수 디스크립터
        * 람다 표현식의 시그니처를 서술하는 메서드
        * Runnable 인터페이스는 인수와 반환값이 없는 시그니처
        
    3.3 람다 활용: 실행 어라운드 패턴
        * 자원을 열고, 처리한 다음, 자원을 닫는 순서
    
    3.4 함수형 인터페이스 사용 
        3.4.1 Predicate
        boolean test(T t); 
        3.4.2 Consumer
        void accept(T t);
        T형식의 객체를 인수로 받아서 어떤 동작을 수행하고싶을 때 
        3.4.3 Function
        R apply(T t);
        입력을 출력으로 매핑하는 람다 정의 시 사용
        
        * 제너릭 파라미터에는 참조형만 가능
        * 참조 -> 기본 언박싱
        * 박싱 <-> 언박싱 : 오토박싱
        * 오토 박싱은 메모리를 더 소비하며, 이러한 오토박싱을 막기 위해 IntPredicate, LongBinaryOperator 등 다양한 인터페이스 제공
        * Predicate<T>  T-> boolean
        Consumer<T>  T -> void
        Function<T,R> T-> R
        Supplier<T> ()-> T
        UnaryOperator<T> T-> T
        BinaryOperator<T> (T,T)-> T
        BiPredicate<L,R> (L,R) -> boolean
        BiConsumer<T,U> (T,U) -> void
        BiFunction<T,U,R> (T,U) -> R
        
    3.5 형식 검사, 형식 추론, 제약 
        3.5.1 형식 검사
        * 람다가 사용되는 콘텍스트를 이용하여 람다의 형식 추론 가능
        * 콘텍스트 ; 람다가 전달될 메서드 파라미터나 람다가 할당되는 변수
        * 대상 형식(target type) 어떤 콘텍스트에서 기대되는 람다 표현식의 형식
        * List<Apple> heavierThan150g = filter(inventory,(Apple a) -> a.getWeight() > 150);
        1) filter 메서드 선언을 확인
        2) filter 메서드는 두 번째 파라미터로 Predicate<Apple> 형식을 기대
        3) Predicate<Apple> 은 test라는 한 개의 추상 메서드를 정의하는 함수형 인터페이스
        4) test 메서드는 Apple을 받아 boolean을 반환하는 함수 디스크립터를 묘사
        5) filter 메서드로 전달된 인수는 이와 같은 요구사항을 만족해야함
        
        3.5.2 같은 람다, 다른 함수형 인터페이스
        * 대상형식이라는 특징으로 같은 람다 표현식이라도 호환되는 추상 메서드를 가진 다른 함수형 인터페이스로 사용될 수 있다. 
        3.5.3 형식 추론
        * 대상 형식을 이용해서 함수 디스크립터를 알 수 있으므로 컴파일러는 람다의 시그니처도 추론 가능 
        3.5.4 지역 변수 사용
        * 자유변수(free variable) : 파라미터로 넘겨진 변수가 아닌 외부에서 정의된 변수
        * 람다 캡처링(captuering lambda) : 람다 표현식에서는 익명 함수가 하는 것처럼 자유변수를 활용할 수 있다. 
        * 인스턴스 변수와 정적 변수가 자유롭게 캡처링 가능. 지역변수에서는 변경이 불가능하도록 final이 선언 되어야함
        
    3.6 메서드 레퍼런스 
        메서드 레퍼런스를 이용하면 기존의 메서드 정의를 재활용해서 람다를 전달
        3.6.1 요약
        * 메서드 레퍼런스는 특정 메서드만 호출하는 람다의 축약형이라고 생각
        * 메서드명 앞에 구분자(::) 를 붙이는 방식으로 메서드 레퍼런스를 활용
        * Apple :: getWeight  - Apple 클래스에 정의된 getWeight의 메서드 레퍼런스이다. 
        * 메서드레퍼런스를 만드는 방법
        1) 정적 메서들 레퍼런스
        2) 다양한 형식의 인스턴스 메서드 레퍼런스
        3) 기존 객체의 인스턴스 메서드 레퍼런스 
        
        3.6.2 생성자 레퍼런스
        
    3.7 람다, 메서드 레퍼런스 활용하기
        3.7.1 1단계 : 코드 전달
        3.7.2 2단계 : 익명 클래스 사용
        3.7.3 3단계 : 람다 표현식 사용
        3.7.4 4단계 : 메서드 레퍼런스 사용
        
    3.8 람다 표현식을 조합할 수 있는 유용한 메서드
        * 여러 개의 람다 표현식을 조합해서 복잡한 람다 표현식을 만들 수 있음 
        3.8.1 Comparator 조합
        3.8.2 Predicate 조합
            * negate, and, or 세 가지 메서드 제공
        3.8.3 Function 조합
            * andThen, compose 메서드를 제공
 
        
        
        
