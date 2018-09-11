package obj

/**
 * OOP
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Groovy5 {

    static void main(String[] args) {

        // 无构造参数时需指定
//        def king = new Animal(name: 'King', sound: 'sod')
        def king = new Animal('King', 'sod')
        println "$king.name says $king.sound"

        king.setName 'Grrrr'
        println king

        println new Dog('guf', 'c', 'me')

    }

}
