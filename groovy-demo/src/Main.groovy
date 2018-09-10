/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
class Main {

    static void main(String[] args) {
        // print hello world
        // println("Hello world")

        println "Hello world"

        def main = new Main()
//        main.printFile()
        main.writeToFile("hello aa", 0..100)

    }

    void writeToFile(def obj, def time) {
        def file = new File("C:\\Users\\shiti\\Desktop\\groovy.txt")
        for (t in time) {
            file.withWriter('utf-8') {
                writer -> writer.append(obj + "\n")
            }
        }
    }

    def printFile() {
        def file = new File("C:\\Users\\shiti\\Desktop\\K12\\K12\\README.md")

        /*file.eachLine {
            line -> println "line: $line"
        }*/

        println file.text

    }

    def mm() {

        def a = 1
        def _b = "aaa"
        def aFloat = 12.33
        def c = false

        int a_ = 1
        String _b_ = "aaa"
        float aFloat_ = 12.33
        boolean c_ = false
        Boolean c__ = false

        def range = 3..5
        def cd = range.get(2)

    }

    def ma() {

    }

    int mc(byte c = 1, String str = "str") {
        return 1
    }

}
