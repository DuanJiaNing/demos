#! 附加注释
//
/*

 */

/**
 * Created on 2018/9/28.
 * @author DuanJiaNing
 */
class Groovy6 {

    static void main(String[] args) {

        // new File("").eachLine { line -> println line }

        def classes = [String, List, Map]
        for (clazz in classes) {
            println clazz.'package'.name
        }

        println([String, List, Map].'package'.name)

        def xml = new XmlSlurper().parse(new File("../resources/test.xml"))
        for (customer in xml.corporate.customer) {
            println "${customer.@name} works for ${customer.@company}"
        }

        new File("../resources").eachFileRecurse { println it }

    }

}