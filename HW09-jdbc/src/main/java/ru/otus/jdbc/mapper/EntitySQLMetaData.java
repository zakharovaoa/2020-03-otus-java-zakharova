package ru.otus.jdbc.mapper;

public interface EntitySQLMetaData {

    void init();

    boolean isInit();

    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();
}
