/**
 * 闭包
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Groovy3 {

    static void main(String[] args) {

        def getFactorial = { num -> (num <= 1 ? 1 : num * call(num - 1)) }
        println getFactorial(4)

        def greeting = 'Goodbye'
        def sayGoodbye = { theName -> println "$greeting $theName " + getFactorial(5) }
        sayGoodbye('Derek')

        def numList = [1, 2, 3, 4]
        numList.each { println it }
        println numList.any { item -> item > 2 }
        println numList.collect { it -> it * 10 }

        def map = ['a': 1, 'b': 2]
        map.each { println "$it.key : $it.value" }

        def nameList = ['Doug', 'Sally', 'Sue']
        println nameList.find { item -> item == 'Sue' }

        // 闭包作为方法参数
        def getEven = { num -> num % 2 == 0 }
        println listEdit(numList, getEven)

    }

    static def listEdit(list, clo) {
        return list.findAll(clo)
    }
}
