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
mapper = [
        'bj_room'          : ['cn.com.artemis.bj.dao.connect.BjRoomMapper', 'cn.com.artemis.bj.to.connect.BjRoomTO'],
        'bj_user'          : ['cn.com.artemis.bj.dao.connect.BjUserMapper', 'cn.com.artemis.bj.to.connect.BjUserTO'],
        'bj_user_login_log': ['cn.com.artemis.bj.dao.connect.BjUserLoginLogMapper', 'cn.com.artemis.bj.to.connect.BjUserLoginLogTO'],
        'bj_user_setting'  : ['cn.com.artemis.bj.dao.connect.BjUserSettingMapper', 'cn.com.artemis.bj.to.connect.BjUserSettingTO'],
        'sys_param'        : ['cn.com.artemis.bj.dao.connect.SysParamMapper', 'cn.com.artemis.bj.to.connect.SysParamTO'],
]


FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def mapperName = mapperName(table.getName(), true) + 'Mapper'
    def fields = calcFields(table)
    new File(dir, mapperName + ".xml").withPrintWriter { out -> generate(table, out, mapperName, fields) }
}

def generate(table, out, mapperName, fields) {
    def baseResultMap = 'BaseResultMap'
    def base_Column_List = 'Base_Column_List'
    def date = new Date().format("yyyy/MM/dd")
    def tableName = table.getName()
    def info = mapper.get(tableName)

    out.println mappingsStart(info[0])
    out.println sql(fields, base_Column_List)
    out.println selectById(tableName, fields, baseResultMap, base_Column_List)
    out.println deleteById(tableName, fields)
    out.println delete(tableName, fields, info[1])
    out.println insert(tableName, fields, info[1])
    out.println update(tableName, fields, info[1])
    out.println selectList(tableName, fields, info[1], base_Column_List)
    out.println mappingsEnd()

}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        fields += [[
                           comment     : col.getComment(),
                           name        : mapperName(col.getName(), false),
                           sqlFieldName: col.getName(),
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
            ''' + testNotNullStrWhere(fields) + '''
        </trim>
    </insert>
'''

}
// ------------------------------------------------------------------------ update
static def update(tableName, fields, parameterType) {

    return '''
    <update id="update" parameterType="''' + parameterType + ''''">
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
static def selectList(tableName, fields, parameterType, base_Column_List) {

    return '''
    <select id="selectList" parameterType="''' + parameterType + '''">
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