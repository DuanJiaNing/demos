/**
 * 数据结构，基本语法
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Groovy1 {

    static void main(String[] args) {

        def name = "Derek"

        println(' I am ${name} $name\n') // ${name} 变量将被忽略
        println(" I am $name")

        def multStr = '''
I am String goes on many
lines
'''
        println(multStr)

        println("3rd Index of Name " + name[3])
        println("3rd Index of r " + name.indexOf('r'))
        println("1rd 3 chars " + name[0..2])
        println("3 chars " + name[0, 2, 4])
        println("substring at 1 to 4 " + name.substring(1, 4))

        println("MyName".concat(' is Duan'))
        println("rep Name 3 time | " * 3)
        println("rep".equals('rpe'))

        def repeatStr = 'What I said is ' * 2
        println(repeatStr - 'What I') // 移除指定字符

        println(repeatStr.split(' '))
        println(repeatStr.toList())
        println(repeatStr.split(' ').toList())

        // compare
        println(" Ant <=> Banana : " + ('Ant' <=> 'Banana'))
        println(" 1 <=> 2 : " + (1 <=> 2))
        println(" 1 <=> 1 : " + (1 <=> 1))
        println(" 2 <=> 1 : " + (2 <=> 1))


        def randStr = 'Random'
        println("A $randStr string")
        printf("A %s string \n", randStr)
        printf("%-10s %d %.2f %10s\n", ['Stuff', 10, 1.234, 'Random'])

//        printf("Whats your name ")
//        def fName = System.console().readLine()
//        println("Hello $fName")
//        def num = System.console().readLine().toDouble()
//        println("num $num")

// ----------------------------------------------- array
        def primes = [2, 3, 4, 5, 6, 11, 23]
        println("2nd primes " + primes[2])
        println("2nd primes " + primes.get(2))
        primes.add(12)

        primes + [222] // 不会影响本体 primes
        println(primes + [22, 133])
        println(primes - [2])

        primes << 19 // 影响本体
        println(primes)

        println(primes.isEmpty())
        println(primes.sort())
        println(primes.reverse())
        println(primes.pop())
        println(primes.intersect([2, 3, 4, 2222])) // 交集

        def employee = ['Derek', 40, 6.23, [1, 2, 3]]
        println(employee[3][2])

// ----------------------------------------------- map
        def paulMap = [
                'name'   : 'Paul',
                'age'    : 35,
                'address': '123 Main St',
                'list'   : [1, 2, 3]
        ]
        println('name ' + paulMap['name'])
        println('age ' + paulMap.get('age'))
        println('of list ' + paulMap['list'][1])

        paulMap.put('city', 'Pittsburgh')
        println(paulMap.containsKey('name'))

// ----------------------------------------------- range
        def oneTo10 = 1..10
        def aToZ = 'a'..'z'
        def zToA = 'Z'..'A'
        println(oneTo10)
        println(aToZ)
        println(zToA)
        println(zToA.size())
        println(zToA.get(2))
        println(zToA.contains('v'))
        println(zToA.last())
        println(zToA.getTo())
        println(zToA.getFrom())

// -----------------------------------------------
        switch (23) {
            case 2..33: println(23); break
            case 12: println(12); break
            case 12..100: println(12..100); break
        }

        for (j in 2..6) {
            print(j + ' ')
        }

        println ""

        def custs = [
                100: 'a',
                300: 'b',
                200: 'c'
        ]
        for (cust in custs) {
            println("$cust.value : $cust.key")
        }

    }

}
