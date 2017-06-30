package com.luolei.mybatisplus.plugin;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

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
        pageNo.setVisibility(JavaVisibility.PRIVATE);
        commentGenerator.addFieldComment(pageNo, introspectedTable);
        topLevelClass.addField(pageNo);

        //---------添加字段 pageSize
        Field pageSize = new Field("pageSize", new FullyQualifiedJavaType("java.lang.Integer"));
        pageSize.setVisibility(JavaVisibility.PRIVATE);
        commentGenerator.addFieldComment(pageSize, introspectedTable);
        topLevelClass.addField(pageSize);

        //---------添加方法 setPageNo
        Method setPageNo = new Method("setPageNo");
        setPageNo.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "pageNo"));
        String bodyLine = "this.pageNo = pageNo;";
        setPageNo.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(setPageNo, introspectedTable);
        topLevelClass.addMethod(setPageNo);

        //---------添加方法 setPageSize
        Method setPageSize = new Method("setPageSize");
        setPageNo.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "pageSize"));
        bodyLine = "this.pageSize = pageSize;";
        setPageSize.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(setPageSize, introspectedTable);
        topLevelClass.addMethod(setPageSize);

        //---------添加方法 getPageNo
        Method getPageNo = new Method("getPageNo");
        getPageNo.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        bodyLine = "return this.pageNo;";
        getPageNo.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(getPageNo, introspectedTable);
        topLevelClass.addMethod(getPageNo);

        //---------添加方法 getPageSize
        Method getPageSize = new Method("getPageSize");
        getPageSize.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        bodyLine = "return this.pageSize;";
        getPageSize.addBodyLine(bodyLine);
        commentGenerator.addGeneralMethodComment(getPageSize, introspectedTable);
        topLevelClass.addMethod(getPageSize);

//        addPage(topLevelClass, introspectedTable, "page");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin

        // 产生分页语句前半部分

        XmlElement oraclePaginationPrefixElement = new XmlElement("sql");
        oraclePaginationPrefixElement.addAttribute(new Attribute("id",
                "OracleDialectPrefix"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'oracle'"));
        pageStart.addElement(new TextElement(
                "select * from ( select row_.*, rownum rownum_ from ( "));
        oraclePaginationPrefixElement.addElement(pageStart);
        parentElement.addElement(oraclePaginationPrefixElement);

        // 产生分页语句后半部分

        XmlElement oraclePaginationSuffixElement = new XmlElement("sql");
        oraclePaginationSuffixElement.addAttribute(new Attribute("id",
                "OracleDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'oracle'"));
        pageEnd.addElement(new TextElement(
                "<![CDATA[ ) row_ ) where rownum_ > #{pageSize * (pageNo - 1)} and rownum_ <= #{pageSize * pageNo} ]]>"));
        oraclePaginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(oraclePaginationSuffixElement);
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end


        // ----------------------------------------------------------------------------mysql 分页
        XmlElement mysqlPaginationSuffixElement = new XmlElement("sql");
        mysqlPaginationSuffixElement.addAttribute(new Attribute("id",
                "MysqlDialect"));
        XmlElement mysqlPage = new XmlElement("if");
        mysqlPage.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'mysql'"));
        mysqlPage.addElement(new TextElement("limit #{pageSize * (pageNo - 1)} , #{pageSize * pageNo}"));
        mysqlPaginationSuffixElement.addElement(mysqlPage);
        parentElement.addElement(mysqlPaginationSuffixElement);
        // ----------------------------------------------------------------------------mysql 分页


        XmlElement sqlserverPaginationPrefixElement = new XmlElement("sql");
        sqlserverPaginationPrefixElement.addAttribute(new Attribute("id", "SqlServerDialectPrefix"));
        XmlElement mssqlPageStart = new XmlElement("if");
        mssqlPageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'sqlserver'"));
        mssqlPageStart.addElement(new TextElement("select top #{pageSize} "));
        XmlElement ref = new XmlElement("include");
        ref.addAttribute(new Attribute("refid", "Base_Column_List"));
        mssqlPageStart.addElement(ref);
        mssqlPageStart.addElement(new TextElement(" FROM ( "));
        parentElement.addElement(mssqlPageStart);

        XmlElement sqlserverPaginationMiddleElement = new XmlElement("sql");
        sqlserverPaginationPrefixElement.addAttribute(new Attribute("id", "SqlServerDialectMiddle"));
        XmlElement mssqlPageMiddle = new XmlElement("if");
        mssqlPageStart.addAttribute(new Attribute("test", "pageNo != 0 and _databaseId == 'sqlserver'"));
        mssqlPageStart.addElement(new TextElement(" ROW_NUMBER() OVER (ORDER BY #{orderByClause}) AS RowNumber, "));
        parentElement.addElement(sqlserverPaginationMiddleElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {

        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  begin

        XmlElement oraclePageStart = new XmlElement("include"); //$NON-NLS-1$

        oraclePageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        element.getElements().add(0, oraclePageStart);


        XmlElement oraclePageEnd = new XmlElement("include"); //$NON-NLS-1$

        oraclePageEnd.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
        element.getElements().add(oraclePageEnd);
        //----------------------------------------------------------------------------oracle 分页，生成相关 sql 部分语句  end


        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  begin

        XmlElement mysqlPage = new XmlElement("include"); //$NON-NLS-1$

        mysqlPage.addAttribute(new Attribute("refid", "MysqlDialect"));
        element.getElements().add(mysqlPage);
        //----------------------------------------------------------------------------mysql 分页，生成相关 sql 部分语句  end


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
