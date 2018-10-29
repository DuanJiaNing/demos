package tools

/**
 * Created on 2018/10/29.
 * @author DuanJiaNing
 */
class Util {

    static void scanDir(File file, def process) {
        file.eachFile { f ->
            if (f.isDirectory()) {
                scanDir(f, process)
            }

            if (f.isFile()) {
                process(f)
            }
        }
    }

}
