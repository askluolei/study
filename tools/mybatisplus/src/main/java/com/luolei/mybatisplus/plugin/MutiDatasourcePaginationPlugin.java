package com.luolei.mybatisplus.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/30
 */
public class MutiDatasourcePaginationPlugin extends PluginAdapter {

    private String primaryKey;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.primaryKey = properties.getProperty("primaryKey");
        if (this.primaryKey == null || this.primaryKey.isEmpty()) {
            this.primaryKey = "id";//没有传主键名 就默认是id
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 修改生成的Example类
     * 添加两个字段
     * pageSize  分页大小   int
     * pageNo    查询的页数，从第一页开始 int
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        //---------添加字段 pageNo
        Field pageNo = new Field("pageNo", new FullyQualifiedJavaType("java.lang.Integer"));
        pageNo.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(pageNo, introspectedTable);
        topLevelClass.addField(pageNo);

        //---------添加字段 pageSize
        Field pageSize = new Field("pageSize", new FullyQualifiedJavaType("java.lang.Integer"));
        pageSize.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(pageSize, introspectedTable);
        topLevelClass.addField(pageSize);

        //---------添加字段 startIndex  从1开始  分页查询包含startIndex 和 endIndex
        Field startIndex = new Field("startIndex", new FullyQualifiedJavaType("java.lang.Integer"));
        startIndex.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(startIndex, introspectedTable);
        topLevelClass.addField(startIndex);

        //---------添加字段 endIndex
        Field endIndex = new Field("endIndex", new FullyQualifiedJavaType("java.lang.Integer"));
        endIndex.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(endIndex, introspectedTable);
        topLevelClass.addField(endIndex);

        //---------添加字段 startIndexFrom0  从0开始计数
        Field startIndexFrom0 = new Field("startIndexFrom0", new FullyQualifiedJavaType("java.lang.Integer"));
        startIndexFrom0.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(startIndexFrom0, introspectedTable);
        topLevelClass.addField(startIndexFrom0);

        //---------添加字段 endIndexFrom0
        Field endIndexFrom0 = new Field("endIndexFrom0", new FullyQualifiedJavaType("java.lang.Integer"));
        endIndexFrom0.setVisibility(JavaVisibility.PROTECTED);
        commentGenerator.addFieldComment(endIndexFrom0, introspectedTable);
        topLevelClass.addField(endIndexFrom0);

        //---------添加方法 setPageNo
        Method setPageNo = new Method("setPageNo");
        setPageNo.setVisibility(JavaVisibility.PUBLIC);
        setPageNo.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "pageNo"));
        String bodyLine = "this.pageNo = pageNo;";
        setPageNo.addBodyLine(bodyLine);
        setPageNo.addBodyLine("addData();");
        commentGenerator.addGeneralMethodComment(setPageNo, introspectedTable);
        topLevelClass.addMethod(setPageNo);

        //---------添加方法 setPageSize
        Method setPageSize = new Method("setPageSize");
        setPageSize.setVisibility(JavaVisibility.PUBLIC);
        setPageSize.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "pageSize"));
        bodyLine = "this.pageSize = pageSize;";
        setPageSize.addBodyLine(bodyLine);
        setPageSize.addBodyLine("addData();");
        commentGenerator.addGeneralMethodComment(setPageSize, introspectedTable);
        topLevelClass.addMethod(setPageSize);

        //---------添加方法 getPageNo
        Method getPageNo = new Method("getPageNo");
        getPageNo.setVisibility(JavaVisibility.PUBLIC);
        getPageNo.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        bodyLine = "return this.pageNo;";
        getPageNo.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(getPageNo, introspectedTable);
        topLevelClass.addMethod(getPageNo);

        //---------添加方法 getPageSize
        Method getPageSize = new Method("getPageSize");
        getPageSize.setVisibility(JavaVisibility.PUBLIC);
        getPageSize.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        bodyLine = "return this.pageSize;";
        getPageSize.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(getPageSize, introspectedTable);
        topLevelClass.addMethod(getPageSize);

        //---------添加方法 addData  当同时设置了pageNo 和 pageSize 的时候，认为是分页查询，自动填充一些其他的分页信息
        Method addData = new Method("addData");
        addData.setVisibility(JavaVisibility.PROTECTED);
        addData.addBodyLine("if (this.pageNo != null && this.pageSize != null && this.pageNo != 0 && this.pageSize != 0) {");
        addData.addBodyLine("this.startIndex = this.pageSize * (this.pageNo - 1) + 1;");
        addData.addBodyLine("this.endIndex = this.pageSize * this.pageNo;");
        addData.addBodyLine("this.startIndexFrom0 = this.startIndex - 1;");
        addData.addBodyLine("this.endIndexFrom0 = this.endIndex - 1;");
        addData.addBodyLine("}");
        topLevelClass.addMethod(addData);
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin

        List<Element> elements = parentElement.getElements();
        List<Element> selectElements = new ArrayList<>();
        for (Element element: elements) {
            if (element instanceof XmlElement) {
                XmlElement xmlElement = (XmlElement) element;
                //选择select 节点
                if ("select".equalsIgnoreCase(xmlElement.getName())) {
                    List<Attribute> attributes = xmlElement.getAttributes();
                    boolean isSelectByExample = false;
                    for (Attribute attribute : attributes) {
                        if ("id".equalsIgnoreCase(attribute.getName()) && "selectByExample".equalsIgnoreCase(attribute.getValue())) {
                            isSelectByExample = true;
                            break;
                        }
                    }
                    if (isSelectByExample) {
                        selectElements.add(element);
                        //选择 生成的 selectByExample  这个是withoutBlob

                    }
                }
            }
        }

        for (Element element : selectElements) {
            XmlElement xmlElement = (XmlElement) element;
            List<Attribute> attributes = xmlElement.getAttributes();
            //--------------------------------------MySQL 分页
            XmlElement mysql = new XmlElement("select");
            for (Attribute attribute : attributes) {
                mysql.addAttribute(attribute);
            }
            mysql.addAttribute(new Attribute("databaseId", "mysql"));
            for (Element e : xmlElement.getElements()) {
                mysql.addElement(e);
            }
            XmlElement mysqlPage = new XmlElement("if");
            mysqlPage.addAttribute(new Attribute("test", "pageNo != 0"));
            mysqlPage.addElement(new TextElement("limit #{startIndexFrom0} , #{pageSize}"));
            mysql.addElement(mysqlPage);
            parentElement.addElement(mysql);

            //--------------------------------------Oracle 分页
            XmlElement oracle = new XmlElement("select");
            for (Attribute attribute : attributes) {
                oracle.addAttribute(attribute);
            }
            oracle.addAttribute(new Attribute("databaseId", "oracle"));
            for (Element e : xmlElement.getElements()) {
                oracle.addElement(e);
            }
            XmlElement pageStart = new XmlElement("if");
            pageStart.addAttribute(new Attribute("test", "pageNo != 0"));
            pageStart.addElement(new TextElement("select "));
            XmlElement orclInclude = new XmlElement("include");
            orclInclude.addAttribute(new Attribute("refid", "Base_Column_List"));
            pageStart.addElement(orclInclude);
            pageStart.addElement(new TextElement(" from ( select row_.*, rownum rownum_ from ( "));

            XmlElement pageEnd = new XmlElement("if");
            pageEnd.addAttribute(new Attribute("test", "pageNo != 0"));
            pageEnd.addElement(new TextElement("<![CDATA[ ) row_ ) where rownum_ >= #{startIndex} and rownum_ <= #{endIndex} ]]>"));
            oracle.addElement(0, pageStart);
            oracle.addElement(pageEnd);
            parentElement.addElement(oracle);

            //--------------------------------------Sql Server 分页
            XmlElement sqlServer = new XmlElement("select");
            for (Attribute attribute : attributes) {
                sqlServer.addAttribute(attribute);
            }
            sqlServer.addAttribute(new Attribute("databaseId", "sqlserver"));
            XmlElement notPage = new XmlElement("if");
            notPage.addAttribute(new Attribute("test", "pageNo == 0"));
            for (Element e : xmlElement.getElements()) {
                notPage.addElement(e);
            }
            sqlServer.addElement(notPage);

            XmlElement page = new XmlElement("if");
            page.addAttribute(new Attribute("test", "pageNo != 0"));

            page.addElement(new TextElement("select "));
            XmlElement sqlPageInclude = new XmlElement("include");
            sqlPageInclude.addAttribute(new Attribute("refid", "Base_Column_List"));
            page.addElement(sqlPageInclude);
            page.addElement(new TextElement(" from ( select "));
            page.addElement(sqlPageInclude);
            page.addElement(new TextElement(" , row_number() over ( "));
            XmlElement notOrder = new XmlElement("if");
            notOrder.addAttribute(new Attribute("test", "orderByClause == null"));
            notOrder.addElement(new TextElement(" order by " + this.primaryKey + " ) as row"));
            XmlElement order = new XmlElement("if");
            order.addAttribute(new Attribute("test", "orderByClause != null"));
            order.addElement(new TextElement("order by ${orderByClause} ) as row"));

            page.addElement(notOrder);
            page.addElement(order);

            page.addElement(new TextElement(" from " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));
            System.out.println(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
            XmlElement where = new XmlElement("if");
            where.addAttribute(new Attribute("test", "_parameter != null"));
            XmlElement whereCondition = new XmlElement("include");
            whereCondition.addAttribute(new Attribute("refid", "Example_Where_Clause"));
            where.addElement(whereCondition);
            page.addElement(where);

            page.addElement(new TextElement(" ) as t where t.row between #{startIndex} and #{endIndex}"));
            sqlServer.addElement(page);
            parentElement.addElement(sqlServer);
        }

//        // 产生分页语句前半部分
//
//        XmlElement oraclePaginationPrefixElement = new XmlElement("sql");
//        oraclePaginationPrefixElement.addAttribute(new Attribute("id",
//                "OracleDialectPrefix"));
//        XmlElement pageStart = new XmlElement("if");
//        pageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'oracle'"));
//        pageStart.addElement(new TextElement(
//                "select * from ( select row_.*, rownum rownum_ from ( "));
//        oraclePaginationPrefixElement.addElement(pageStart);
//        parentElement.addElement(oraclePaginationPrefixElement);
//
//        // 产生分页语句后半部分
//
//        XmlElement oraclePaginationSuffixElement = new XmlElement("sql");
//        oraclePaginationSuffixElement.addAttribute(new Attribute("id",
//                "OracleDialectSuffix"));
//        XmlElement pageEnd = new XmlElement("if");
//        pageEnd.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'oracle'"));
//        pageEnd.addElement(new TextElement(
//                "<![CDATA[ ) row_ ) where rownum_ > #{pageSize * (pageNo - 1)} and rownum_ <= #{pageSize * pageNo} ]]>"));
//        oraclePaginationSuffixElement.addElement(pageEnd);
//        parentElement.addElement(oraclePaginationSuffixElement);
//        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end
//
//
//        // ----------------------------------------------------------------------------mysql 分页
//        XmlElement mysqlPaginationSuffixElement = new XmlElement("sql");
//        mysqlPaginationSuffixElement.addAttribute(new Attribute("id",
//                "MysqlDialect"));
//        XmlElement mysqlPage = new XmlElement("if");
//        mysqlPage.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'mysql'"));
//        mysqlPage.addElement(new TextElement("limit #{pageSize * (pageNo - 1)} , #{pageSize * pageNo}"));
//        mysqlPaginationSuffixElement.addElement(mysqlPage);
//        parentElement.addElement(mysqlPaginationSuffixElement);
//        // ----------------------------------------------------------------------------mysql 分页
//
//
//        XmlElement sqlserverPaginationPrefixElement = new XmlElement("sql");
//        sqlserverPaginationPrefixElement.addAttribute(new Attribute("id", "SqlServerDialectPrefix"));
//        XmlElement mssqlPageStart = new XmlElement("if");
//        mssqlPageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'sqlserver'"));
//        mssqlPageStart.addElement(new TextElement("select top #{pageSize} "));
//        XmlElement ref = new XmlElement("include");
//        ref.addAttribute(new Attribute("refid", "Base_Column_List"));
//        mssqlPageStart.addElement(ref);
//        mssqlPageStart.addElement(new TextElement(" FROM ( "));
//        parentElement.addElement(mssqlPageStart);
//
//        XmlElement sqlserverPaginationMiddleElement = new XmlElement("sql");
//        sqlserverPaginationPrefixElement.addAttribute(new Attribute("id", "SqlServerDialectMiddle"));
//        XmlElement mssqlPageMiddle = new XmlElement("if");
//        mssqlPageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'sqlserver'"));
//        mssqlPageStart.addElement(new TextElement(" ROW_NUMBER() OVER (ORDER BY #{orderByClause}) AS RowNumber, "));
//        parentElement.addElement(sqlserverPaginationMiddleElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {

//        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin
//
//        XmlElement oraclePageStart = new XmlElement("include"); //$NON-NLS-1$
//
//        oraclePageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
//        element.getElements().add(0, oraclePageStart);
//
//
//        XmlElement oraclePageEnd = new XmlElement("include"); //$NON-NLS-1$
//
//        oraclePageEnd.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
//        element.getElements().add(oraclePageEnd);
//        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end
//
//
//        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  begin
//
//        XmlElement mysqlPage = new XmlElement("include"); //$NON-NLS-1$
//
//        mysqlPage.addAttribute(new Attribute("refid", "MysqlDialect"));
//        element.getElements().add(mysqlPage);
//        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  end


        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
                introspectedTable);
    }

    private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.luolei.mybatisplus.model.Page"));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType("com.luolei.mybatisplus.model.Page"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("com.luolei.mybatisplus.model.Page"), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("com.luolei.mybatisplus.model.Page"));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }
}
