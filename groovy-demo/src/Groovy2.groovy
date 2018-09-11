/**
 * 方法
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Groovy2 {

    static void main(String[] args) {

        def name = 'this is name'
        passByValue(name)
        println name

        println doubleList([1, 2, 3])
        println sumAll(1, 2, 3)
        println factorial(4)

    }

    // 阶乘
    static def factorial(num) {
        if (num <= 1) {
            return 1
        } else {
            return (num * factorial(num - 1))
        }
    }

    static def sumAll(int ... nums) {
        def sum = 0
        nums.each { sum += it } // 遍历
        return sum
    }

    // 传值而不是对象本身
    static void passByValue(value) {
        value = 'pass by'
        println(value)
    }

    static def sayHello() {
        println "Hello"
    }

    static def getSum(n1 = 1, n2 = 2) {
        return n1 + n2
    }

    static def doubleList(list) {
        def newLsit = list.collect { it * 2 } // 取出每一个元素并重新赋值
        return newLsit
    }

}
