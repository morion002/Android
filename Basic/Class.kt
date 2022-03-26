// 1. Class
// Class 이름과 File 이름이 동일하지 않아도 됨
// 한 File 안에 여러 개의 Class 생성 가능
// class + class 이름 + {}
class Human {
    // Class 안 상수나 변수는 property
    val name = "Morion"
    // Class 안 함수는 method
    fun eatingCake() {
        println("This is so YUMMY")
    }
}



// 2. Constructor
// 생성된 객체의 모습을 사용자가 원하는 대로 지정
// class + class 이름 + constructor (생략 가능) + (입력해 줄 property) + {}
class Human2 constructor(val name : String = "Anonymous") {
    // class Human2 constructor (name : String) {
            // val name = name 으로 사용 가능
    fun eatingCake() {
        println("This is so YUMMY")
    }

    // init 을 활용해 객체가 생성되었을 때 처음으로 실행할 동작 설정 가능
    init {
        println ("New human has been born")
    }

    // java 의 생성자 오버로딩 기능 구현 가능
    // java
    /*
    * class Person {
    *   public Person(String name) {}
    *   public Person(String name, int age) {]
    *  }
    * */
    // init 이라는 주생성자와 constructor 라는 부생성자 이용
    // 부생성자는 주생성자의 위임을 받아야 함 (this 이용)
    constructor(name : String, age : Int) : this(name) {
        println("my name is ${name}, ${age} years old")
    }
}



// 3. Inheritance
// java
// class Korean extend Human
// Kotlin 의 Class 는 기본적으로 final 이라 같은 file 에 있어도 접근 불가능
// class 를 open 해 줘야 함
open class OpenHuman(val name : String = "Anonymous") {
    init {
        println ("New human has been born")
    }

    constructor(name : String, age : Int) : this(name) {
        println("my name is ${name}, ${age} years old")
    }

    open fun singaSong() {
        println("lalala")
    }
}

class Korean : OpenHuman() {
    // Overriding: 상속받은 method 를 자식 Class 에서 새롭게 정의해서 사용
    // Method 도 기본이 final 이라 override 위해서는 open 필요
    override fun singaSong() {
        println("라라라")
    }

    fun sing() { // override 된 상위 class 의 함수를 호출하는 방법: 접근자 super 사용
        super.singaSong()
    }
}

class American : OpenHuman() {
}



fun main() {
    val human = Human() // new Human() 으로 객체 생성했던 Java 와 달리 new 키워드 불필요
    println("this human's name is ${human.name}")
    human.eatingCake()

    val human2 = Human2("Minsu") // parameter 입력 필요
    println("this human's name is ${human2.name}")

    val stranger = Human2() // Default value 적용
    println("this human's name is ${stranger.name}")

    val mom = Human2("Kurl", 52) // 주생성자 먼저 실행 -> 부생성자 다음 실행

    val american = American()
    american.singaSong() // american 의 Class 인 American 이 OpenHuman 을 상속받고 있어 OpenHuman 의 method 호출
    val korean = Korean()
    korean.singaSong() // korean 의 Class 인 Korean 이 OpenHuman 의 Method 인 singaSong 을 override 하고 있어 Korean 의 singaSong method 호출
    korean.sing()
}
