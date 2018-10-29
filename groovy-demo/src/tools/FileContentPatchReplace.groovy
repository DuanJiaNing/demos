package tools

/**
 * Created on 2018/10/29.
 *
 * @author DuanJiaNing
 */
class FileContentPatchReplace {

    static int count = 1

    static void main(String[] args) {

        def scanner = new BufferedReader(new InputStreamReader(System.in))
        print 'input dir path: '
        def dirPath = scanner.readLine()
        print 'input regex: '
        def regex = scanner.readLine()
        print 'input replacement: '
        def replacement = scanner.readLine()
        println ''

        def process = { file -> // 文件
            file = (File) file

            def ra = new RandomAccessFile(file, "rw")
            String line
            long lastPoint = 0, lineCount = 0
            boolean contain = false

            while ((line = ra.readLine()) != null) {
                lineCount++
                def point = ra.getFilePointer()

                def find = line.find(regex)
                if (find != null) {
                    def desStr = line.replaceAll(regex, replacement)
                    ra.seek(lastPoint)
                    ra.writeBytes(desStr)
                    ra.seek(point)

                    if (!contain) {
                        contain = true
                        println '\n' + count + '  ' + file.path
                        count++
                    }

                    println "    line: [$lineCount]  " + line + ' -> ' + desStr
                }
                lastPoint = point
            }
            ra.close()

        }

        Util.scanDir(new File(dirPath), process)

    }
}
