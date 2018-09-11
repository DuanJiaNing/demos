/**
 * 文件
 * Created on 2018/9/11.
 * @author DuanJiaNing
 */
class Groovy4 {

    static void main(String[] args) {

        def file = new File('file.txt')
        file.withWriter('UTF-8') {
            writer -> (1..13).each { it -> writer.writeLine("line $it") }
        }

        file.eachLine { line -> println line }

        println "file is file ? ${file.isFile()}"
        println "file is file ? ${file.isDirectory()}"

        def newFile = new File("file2.txt")
        newFile << "file-content-txt" // 直接追加内容
        newFile << " 1file-content-txt"
        println newFile.size()
        newFile.eachLine { line -> println line }
        newFile.delete() // 小心误删磁盘文件

        def dir = new File("").listRoots()
        dir.each { it -> println it.absolutePath }

    }

}
