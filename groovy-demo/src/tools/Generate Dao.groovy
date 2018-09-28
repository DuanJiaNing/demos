package tools

import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "**;" // 需手动配置 生成的 dao 所在包位置

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def baseName = javaName(table.getName(), true)
    new File(dir, baseName + "Mapper.java").withPrintWriter { out -> generateInterface(out, baseName) }
}

def generateInterface(out, baseName) {
    def date = new Date().format("yyyy/MM/dd")
    out.println "package $packageName"
    out.println "import cn.xx.entity.${baseName}Entity;" // 需手动配置
    out.println "import org.springframework.stereotype.Repository;"
    out.println ""
    out.println "/**"
    out.println " * Created on $date."
    out.println " *"
    out.println " * @author XX" // 可自定义
    out.println " */"
    out.println "@Repository"
    out.println "public interface ${baseName}Dao extends BaseDao<${baseName}Entity> {" // 可自定义
    out.println ""
    out.println "}"
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    name = capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
