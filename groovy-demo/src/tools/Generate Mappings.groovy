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

// entity(dto)、mapper(dao) 与数据库表的对应关系在这里手动指明,idea Database 窗口里只能选下列配置了的 mapper
// tableName(key) : [mapper(dao),entity(dto)]
typeMapping = [
        (~/(?i)int/)                      : "INTEGER",
        (~/(?i)float|double|decimal|real/): "DOUBLE",
        (~/(?i)datetime|timestamp/)       : "TIMESTAMP",
        (~/(?i)date/)                     : "TIMESTAMP",
        (~/(?i)time/)                     : "TIMESTAMP",
        (~/(?i)/)                         : "VARCHAR"
]

basePackage = "**" // 包名需手动填写

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def baseName = mapperName(table.getName(), true)
    def fields = calcFields(table)
    new File(dir, baseName + "Mapper.xml").withPrintWriter { out -> generate(table, out, baseName, fields) }
}

def generate(table, out, baseName, fields) {
    def baseResultMap = 'BaseResultMap'
    def base_Column_List = 'Base_Column_List'
    def date = new Date().format("yyyy/MM/dd")
    def tableName = table.getName()

    def dao = basePackage + ".dao.${baseName}Mapper"
    def to = basePackage + ".to.${baseName}TO"

    out.println mappingsStart(dao)
    out.println resultMap(baseResultMap, to, fields)
    out.println sql(fields, base_Column_List)
    out.println selectById(tableName, fields, baseResultMap, base_Column_List)
    out.println deleteById(tableName, fields)
    out.println delete(tableName, fields, to)
    out.println insert(tableName, fields, to)
    out.println update(tableName, fields, to)
    out.println selectList(tableName, fields, to, base_Column_List, baseResultMap)
    out.println mappingsEnd()

}

static def resultMap(baseResultMap, to, fields) {

    def inner = ''
    fields.each() {
        inner += '\t\t<result column="' + it.sqlFieldName + '" jdbcType="' + it.type + '" property="' + it.name + '"/>\n'
    }

    return '''\t<resultMap id="''' + baseResultMap + '''" type="''' + to + '''">
        <id column="id" jdbcType="INTEGER" property="id"/>
''' + inner + '''\t</resultMap>
'''
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           comment     : col.getComment(),
                           name        : mapperName(col.getName(), false),
                           sqlFieldName: col.getName(),
                           type        : typeStr,
                           annos       : ""]]
    }

}

def mapperName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    name = capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

// ------------------------------------------------------------------------ mappings
static def mappingsStart(mapper) {
    return '''<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="''' + mapper + '''">
'''
}

// ------------------------------------------------------------------------ mappings
static def mappingsEnd() {
    return '''</mapper>'''
}

// ------------------------------------------------------------------------ selectById
static def selectById(tableName, fields, baseResultMap, base_Column_List) {
    return '''
    <select id="selectById" parameterType="java.lang.Integer" resultMap="''' + baseResultMap + '''">
        select
        <include refid="''' + base_Column_List + '''"/>
        from ''' + tableName + '''
        where id = #{id}
    </select>'''
}

// ------------------------------------------------------------------------ insert
static def insert(tableName, fields, parameterType) {

    return '''
    <insert id="insert" parameterType="''' + parameterType + '''">
        insert into ''' + tableName + '''
        <trim prefix="(" suffix=")" suffixOverrides=",">
            ''' + testNotNullStr(fields) + '''
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            ''' + testNotNullStrSet(fields) + '''
        </trim>
    </insert>
'''

}
// ------------------------------------------------------------------------ update
static def update(tableName, fields, parameterType) {

    return '''
    <update id="update" parameterType="''' + parameterType + '''">
        update ''' + tableName + '''
        <set>
            ''' + testNotNullStrWhere(fields) + '''
        </set>
        where id = #{id}
    </update>'''
}

// ------------------------------------------------------------------------ deleteById
static def deleteById(tableName, fields) {

    return '''
    <delete id="deleteById" parameterType="java.lang.Integer">
        delete
        from ''' + tableName + '''
        where id = #{id}
    </delete>'''
}

// ------------------------------------------------------------------------ delete
static def delete(tableName, fields, parameterType) {

    return '''
    <delete id="delete" parameterType="''' + parameterType + '''">
        delete from ''' + tableName + '''
        where 1 = 1
        ''' + testNotNullStrWhere(fields) + '''
    </delete>'''
}

// ------------------------------------------------------------------------ selectList
static def selectList(tableName, fields, parameterType, base_Column_List, baseResultMap) {

    return '''
    <select id="selectList" parameterType="''' + parameterType + '''" resultMap="''' + baseResultMap + '''">
        select
        <include refid="''' + base_Column_List + '''"/>
                from ''' + tableName + '''
        where 1 = 1
        ''' + testNotNullStrWhere(fields) + '''
        order by id desc
    </select>'''
}

// ------------------------------------------------------------------------ sql
static def sql(fields, base_Column_List) {
    def str = '''\t<sql id="''' + base_Column_List + '''">
        @inner@
    </sql> '''

    def inner = ''
    fields.each() {
        inner += ('\t\t' + it.sqlFieldName + ',\n')
    }

    return str.replace("@inner@", inner.substring(0, inner.length() - 2))

}

static def testNotNullStrWhere(fields) {
    def inner = ''
    fields.each {
        inner += '''
        <if test="''' + it.name + ''' != null">
            and ''' + it.sqlFieldName + ''' = #{''' + it.name + '''}
        </if>\n'''
    }

    return inner
}

static def testNotNullStrSet(fields) {
    def inner = ''
    fields.each {
        inner += '''
        <if test="''' + it.name + ''' != null">
            #{''' + it.name + '''},
        </if>\n'''
    }

    return inner
}

static def testNotNullStr(fields) {
    def inner1 = ''
    fields.each {
        inner1 += '''
        <if test = "''' + it.name + ''' != null" >
        \t''' + it.sqlFieldName + ''',
        </if>\n'''
    }

    return inner1
}