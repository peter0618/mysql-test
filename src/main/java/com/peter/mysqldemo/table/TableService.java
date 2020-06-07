package com.peter.mysqldemo.table;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class TableService {

    final DataSource dataSource;
    final JdbcTemplate jdbcTemplate;

    public TableService(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String hello(String tableSchema, String tableName) {
        StringBuilder sb = new StringBuilder();
        try(Connection connection = dataSource.getConnection()){

            String sql = "select COLUMN_NAME AS colName\n" +
                         "      ,COLUMN_TYPE AS colType\n" +
                         "      ,COLUMN_COMMENT AS colCmnt\n" +
                         "  from INFORMATION_SCHEMA.columns\n" +
                         " where table_schema='" + tableSchema + "'\n" +
                         "   and table_name='"+ tableName + "'\n" +
                         " order by ordinal_position";

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);

            int maxLength = 0; // 가장 긴 컬럼의 길이 => 해당 길이에 맞춰서 right padding
            while(resultSet.next()) {
                String colName = resultSet.getString("colName");
                maxLength = maxLength < colName.length() ? colName.length() : maxLength;
            }
            resultSet.beforeFirst();

            sb.append("SELECT\n ");
            while(resultSet.next()) {
                sb.append(String.format("%-" + maxLength + "s", resultSet.getString("colName")));
                sb.append("  -- ");
                sb.append(resultSet.getString("colCmnt"));
                sb.append("\n");
                if(!resultSet.isLast()){
                    sb.append(",");
                }
            }
            sb.append("FROM ").append(tableSchema).append(".").append(tableName).append(";");
            System.out.println(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}