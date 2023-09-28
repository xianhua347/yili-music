package com.bilitech.yilimusic.config;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.stereotype.Component;

/**
 * JpaInterceptor 是一个实现了 StatementInspector 接口的类，用于拦截和修改 SQL 语句 打印原生sql而不是Hsql。
 */
@Log4j2
@Component
public class JpaInterceptor implements StatementInspector {

  private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("([a-z]+[\\d]+(_)(\\.)[a-z]+)");
  private static final Pattern AS_PATTERN = Pattern.compile("((as)(\\s)[a-z]+([\\d|a-z]+(_)+)+)");
  private static final Pattern SQL_START_PATTERN = Pattern.compile("^(select|insert|delete)",
      Pattern.CASE_INSENSITIVE);
  private static final boolean SIMPLE = false;

  /**
   * 拦截和修改 SQL 语句的方法。
   *
   * @param sql 要检查和修改的 SQL 语句
   * @return 修改后的 SQL 语句
   */
  @Override
  public String inspect(String sql) {
    // 将 SQL 语句转换为小写并去除换行符
    String lowerSql = sql.toLowerCase().replace("\n", "");

    // 使用正则表达式匹配 SQL 语句的起始部分
    Matcher sqlStartMatcher = SQL_START_PATTERN.matcher(lowerSql);

    // 如果 SQL 语句不以 "select"、"insert" 或 "delete" 开头，则不做任何修改
    if (! sqlStartMatcher.find()) {
      return sql;
    }

    // 提取包含别名的表名
    List<String> aliasedTableNames = extractAliasedTableNames(lowerSql);

    // 修改 SQL 语句
    lowerSql = modifySql(lowerSql, aliasedTableNames);

    // 输出修改后的 SQL 语句到日志
    log.info(lowerSql);

    // 返回原始 SQL 语句
    return sql;
  }

  /**
   * 提取 SQL 语句中包含别名的表名。
   *
   * @param lowerSql 要提取表名的 SQL 语句
   * @return 包含别名的表名列表
   */
  private List<String> extractAliasedTableNames(String lowerSql) {
    return TABLE_NAME_PATTERN
        .matcher(lowerSql)
        .results()
        .map(MatchResult :: group)
        .map(s -> s.split("\\.")[0])
        .collect(Collectors.toList());
  }

  /**
   * 修改 SQL 语句，去除表名和别名。
   *
   * @param lowerSql   要修改的 SQL 语句
   * @param tableNames 包含别名的表名列表
   * @return 修改后的 SQL 语句
   */
  private String modifySql(String lowerSql, List<String> tableNames) {
    String modifiedSql = tableNames.stream()
        .reduce(lowerSql, (sqlString, s) -> sqlString.replace(s, ""),
            (a, b) -> b);

    // 如果不是简化模式，去除点号
    if (! SIMPLE) {
      modifiedSql = modifiedSql.replace(".", "");
    }

    // 使用正则表达式匹配 "as" 关键字并移除
    Matcher asMatcher = AS_PATTERN.matcher(modifiedSql);
    modifiedSql = asMatcher.replaceAll("");

    return modifiedSql;
  }
}


