/**
 * Created on 2018/10/29.
 * @author DuanJiaNing
 */
class FilePatchRename {

    static void main(String[] args) {
        print 'input dir path: '
        def scanner = new BufferedReader(new InputStreamReader(System.in))
        def dirPath = scanner.readLine()
        print 'input target dir path: '
        def targetPath = scanner.readLine()

        def newDir = new File(targetPath)
        if (!newDir.exists()) newDir.mkdirs()

        def regex = '(POST)|(GET)|(DELETE)|(PUT)'
        new File(dirPath).eachFile { file ->
            def desName = file.name.replaceAll(regex, '')
            println file.name + ' -> ' + desName
            file.renameTo(targetPath + File.separator + desName)
        }

        println 'process finished'
    }

}
