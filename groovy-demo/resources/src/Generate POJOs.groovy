package src

import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "***;" // 需手动配置
typeMapping = [
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "Date",
        (~/(?i)date/)                     : "Date",
        (~/(?i)time/)                     : "Date",
        (~/(?i)/)                         : "String"
]
/*typeMapping = [
  (~/(?i)int/)                      : "long",
  (~/(?i)float|double|decimal|real/): "double",
  (~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",
  (~/(?i)date/)                     : "java.sql.Date",
  (~/(?i)time/)                     : "java.sql.Time",
  (~/(?i)/)                         : "String"
]*/

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)

    // 实体类后缀需要手动设置，这里为 Entity
    new File(dir, className + "Entity.java").withPrintWriter { out -> generate(out, className + "Entity", fields) }
}

def generate(out, className, fields) {
    def date = new Date().format("yyyy/MM/dd")
    out.println "package $packageName"
    out.println "import java.io.Serializable;"
    out.println "import java.util.Date;"
    out.println ""
    out.println "/**"
    out.println " * Created on $date."
    out.println " *"
    out.println " * @author XX" // 可自定义
    out.println " */"
    out.println "public class $className implements Serializable {"
    out.println ""
    fields.each() {

        if (isNotEmpty(it.comment)) {
            out.println "\t/**"
            out.println "\t * ${it.comment}"
            out.println "\t */"
        }

        if (it.annos != "")
            out.println "\t${it.annos}"

        out.println "\tprivate ${it.type} ${it.name};"
        out.println ""

    }
    fields.each() {
        out.println ""
        out.println "\tpublic ${it.type} get${it.name.capitalize()}() {"
        out.println "\t\treturn ${it.name};"
        out.println "\t}"
        out.println ""
        out.println "\tpublic void set${it.name.capitalize()}(${it.type} ${it.name}) {"
        out.println "\t\tthis.${it.name} = ${it.name};"
        out.println "\t}"
    }
    out.println "}"
}

def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           comment: col.getComment(),
                           name   : javaName(col.getName(), false),
                           type   : typeStr,
                           annos  : ""]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    name = capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
