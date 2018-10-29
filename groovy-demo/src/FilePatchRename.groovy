/**
 * Created on 2018/10/29.
 *  批量重命名
 * @author DuanJiaNing
 */
class FilePatchRename {

    static void main(String[] args) {
        print 'input dir path: '
        def scanner = new BufferedReader(new InputStreamReader(System.in))
        def dirPath = scanner.readLine()
        print 'input target dir path: '
        def targetPath = scanner.readLine()
        scanner.close()

        def newDir = new File(targetPath)
        if (!newDir.exists()) newDir.mkdirs()

        def regex = '(POST)|(GET)|(DELETE)|(PUT)'
        new File(dirPath).eachFile { file ->

            // 重命名
            def desName = file.name.replaceAll(regex, '')
            println file.name + ' -> ' + desName

            // md 标题规范 ##aa -> ## aa
            def targetFile = new File(targetPath + File.separator + desName)
            def writer = new BufferedWriter(new FileWriter(targetFile))

            // 对每行内容进行处理，否则 File.renameTo 直接亦可
            file.eachLine { lineStr ->
                def newLine = lineStr
                if (lineStr.startsWith("##")) newLine = "## " + lineStr.substring(2, lineStr.length())
                writer.write(newLine)
                writer.newLine()
            }

            writer.flush()
            writer.close()
        }

        println 'process finished'
    }

}
