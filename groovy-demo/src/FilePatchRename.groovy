/**
 * Created on 2018/10/29.
 *  批量重命名
 * @author DuanJiaNing
 */
class FilePatchRename {

    static int count = 1

    static void main(String[] args) {
        print 'input dir path: '
        def scanner = new BufferedReader(new InputStreamReader(System.in))
        def dirPath = scanner.readLine()
        print 'input target dir path: '
        def targetPath = scanner.readLine()
        scanner.close()

        def process = { file -> // md 文件

            file = (File) file

            // 重命名
            def regex = '(POST)|(GET)|(DELETE)|(PUT)'
            def desName = file.name.replaceAll(regex, '')
            println count + '\t' + file.name + ' -> ' + desName

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
            count++
        }

        def newDir = new File(targetPath)
        if (!newDir.exists()) newDir.mkdirs()
        loop(new File(dirPath), process)

        println 'process finished'
    }

    static void loop(File file, def process) {
        file.eachFile { f ->
            if (f.isDirectory()) {
                loop(f, process)
            }

            if (f.isFile()) {
                process(f)
            }
        }
    }

}
