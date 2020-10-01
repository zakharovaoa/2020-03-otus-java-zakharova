package ru.otus.jdbc.mapper;

public interface EntitySQLMetaData {

    boolean isInit();

    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();
}
