fun main(){
    helloWorld()
    println(add(1, 2))
    string_template()
    checkNum(1)
    forAndWhile()
    nullcheck()
    ignoreNulls("apeach")
}



// 1. 함수
// 함수 기본형
// fun + 함수 이름 (매개변수) : (return이 void일 때 Unit) {}
fun helloWorld():Unit {
    println("Hello World")
}
// return type 이 없을 때 fun helloWorld() {} 으로도 작성 가능

// 함수 확장형 (return 과 parameter 가 있는 경우)
// fun + 함수 이름 (parameter : parameter type) : return type {}
fun add (a : Int, b : Int) : Int {
    return (a + b)
}
// 변수명을 type보다 먼저 작성
// return type이 존재하는 경우는 생략 불가능



// 2. val vs var
// val = value, var = variable
// val 은 불변 (상수), var 은 가변 (변수)
fun val_vs_var() {
    val a : Int = 10
    var b : Int = 9
    // a = 100 불가능
    b = 100 // 가능
    var c = 100 // 자동으로 변수 type 설정
    val me = "Morion"
}



// 3. String template
fun string_template() {
    val name = "Morion"
    val lastname = "002"
    // 원하는 자리에 변수 대입 가능 ${변수명}
    println("my name is ${name + lastname}")
    // $를 쓰고 싶을 때는 백슬래시 활용
    println("\$name")
}



// 4. Conditional Statement
// 최댓값 출력 (기본적인 방법)
fun maxBy (a : Int, b : Int) : Int {
    if (a > b){
        return a
    }
    else {
        return b
    }
}

// 최댓값 출력 (간단화된 방법)
// return (a > b) ? a : b 와 같은 삼항연산자가 없어서 다음과 같이 표현
fun maxBy2 (a : Int, b : Int) : Int = if (a > b) a else b

// when: switch의 역할
fun checkNum(score : Int) {
    when(score) {
        0 -> println("This is 0")
        1 -> println("This is 1")
        2, 3 -> println("This is 2 or 3")
        else -> println("I don't know")
    }
    // when 의 return 식 역할
    var b : Int = when(score) {
        1 -> 10
        2 -> 20
        // 변수 b는 score가 1일 때 10, 2일 때 20
        // return 형식으로 사용될 때는 else를 반드시 기입해 줘야 함
        else -> 0
    }
    println("b : ${b}")
    // when 의 범위 표현
    when(score) {
        in 90..100 -> println("Very good")
        in 0..80 -> println("Try again")
        else -> println("Okay")
    }
}

// Expression vs Statement
// Expression : 값을 만드는 경우, Statement : 값을 만들지 않는 경우
// Kotlin 의 모든 함수는 Expression (Unit 을 return 하기 때문에)
// Java 의 경우 void 개념은 Statement
// Java 에서 Statement 였던 if, when 구문이 Kotlin 에서는 Expression 으로 사용될 수 있음



// 5. Array and List
// Array: 메모리가 이미 정해져서 나오기 때문에 크기가 정해져 있음
// List: List (Immutable 수정 불가능) MutableList (Mutable 수정 가능)
fun array() {
    val array = arrayOf(1, 2, 3) // array 초기화
    val list = listOf(1, 2, 3) // list 초기화
    val array2 = arrayOf(1, "Hello", 1.0) // 다양한 type 을 요소로 가질 수 있음
    array[0] = 3 // array 는 Mutable (수정 가능)
    // list[0] = 2 list 는 Immutable 이라 수정 불가능

    // Mutable 한 list 의 예시: arraylist
    val arrayList = arrayListOf<Int>()
    arrayList.add(10) // 10 을 추가하는 method
    // arrayList 안의 구성값들은 변하지만 arrayList 참조값은 변하지 않기 때문에 val 로 선언 가능
}



// 6. Iteration (For / While)
fun forAndWhile() {
    // iterator 값 참조
    val students = arrayListOf("joice", "james", "jenny", "jennifer")
    for (name in students){
        println("${name}")
    }

    // 범위 값 참조 (index 로 활용)
    var sum = 0
    for (i in 1..10) {
        sum += i
    }
    println(sum)

    // iterator 간격 조정
    var sum2 = 0
    for (i in 1..10 step 2){
        sum2 += i
    }
    println(sum2)

    // 감소하는 iterator
    var sum3 = 0
    for (i in 10 downTo 1) {
        sum3 += i
    }
    println(sum3)

    // 상한 끝단값 포함하지 않는 iterator
    var sum4 = 0
    for (i in 1 until 10) { // [1, 10)
        sum4 += i
    }
    println(sum4)

    // While
    var index = 0
    while (index < 10){
        println("current Index: ${index}")
        index += 5
    }

    // index 와 value 함께 사용하고 싶을 때
    for ((index, name) in students.withIndex()) {
        println("${index + 1} 번째 학생: ${name}")
    }
}



// 7. Nullable / NonNull
fun nullcheck() {
    // NPE: NULL point Exception (runtime 에 예외 처리)
    // 불편함을 감소시키기 위해 Compile 시점에 Null check
    var name = "Morion" // NonNull type (NULL 이면 안 되는 type)
    var nullName : String? = null // Nullable 한 type (물음표 표시를 위하 type 작성 생략 불가능)

    // Null check 방법 (Compile time 에 check 해 줌)
    var nameInUpperCase = name.toUpperCase() // name 이 NonNull 이기 때문에 nameInUpperCase 도 NonNull
    var nullNameInUpperCase : String? = nullName?.toUpperCase() // nullName 이 Nullable 이라서 (NULL 값을 가지고 있을 수도 있어서 toUpperCase() 를 적용해도 되는지 혼란
        // nullName 이 NULL 이 아니라면 UpperCase 를 하고 NULL 이라면 NULL 반환

    // ?: 연산자
    // Nullable 값을 반환하는 method 나 함수에서 Default value 를 지정하고 싶을 때
    val lastName : String? = null
    val fulName = name + " " + (lastName?: "No lastName") // laseName 이 null 일 경우에는 No lastName 반환
    println(fulName)
}

// !! 연산자
// null 이 아니라고 Compiler 에게 말해 주는 연산자
fun ignoreNulls(str : String?) {
    val mNotNull : String = str!! // parameter 인 str 변수가 절대 null 일 리가 없을 때
    // 확실하게 null 이 아닐 떄만 사용
    val upper = mNotNull.toUpperCase()

    // let 함수
    // Receiver 객체를 자신의 Lambda Function 으로 옮겨서 사용하는 함수
    val email : String? = "morion@002.com"
    email?.let { // email 이 null 이 아닐 때 다음 구문을 실행해라
        // email 이 null 이 아니라면 let 이라는 lambda 식 내부로 email 옮김
        println("my email is ${email}")
    }
}
