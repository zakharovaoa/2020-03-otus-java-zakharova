package ru.otus.jdbc.mapper;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Field;
import java.util.List;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData{

    public EntityClassMetaData entityClassMetaData;

    public EntitySQLMetaDataImpl(T objectData) {
        this.entityClassMetaData = new EntityClassMetaDataImpl<T>(T objectData));
    }

    @Override
    public String getSelectAllSql() {
        StringBuilder str = new StringBuilder();
        str.append("select * from ");
        str.append(entityClassMetaData.getName());
        return str.toString();
    }

    @Override
    public String getSelectByIdSql() {
        StringBuilder str = new StringBuilder();
        str.append("select ");
        List<Field> list = entityClassMetaData.getFieldsWithoutId();
        for (Field field : list) {
            str.append(field.getName() + ", ");
        }
        str.substring(1, str.length() - 2);
        str.append(" from ");
        str.append(entityClassMetaData.getName());
        str.append(" where" );
        str.append(entityClassMetaData.getIdField().getName());
        str.append(" = ?");
        //"select id, name, age from user where id  = ?"
        System.out.println(str.toString());
        return str.toString();
    }

    @Override
    public String getInsertSql() {
        //insert into user(name, age) values (?, ?)
        StringBuilder str = new StringBuilder();
        StringBuilder strParam = new StringBuilder();
        str.append("insert into ");
        str.append(entityClassMetaData.getName());
        str.append("(");
        List<Field> list = entityClassMetaData.getFieldsWithoutId();
        for (Field field : list) {
            str.append(field.getName() + ", ");
            strParam.append("?, ");
        }
        str.substring(1, str.length() - 2);
        strParam.substring(1, strParam.length() - 2);
        str.append(") values (");
        str.append(strParam);
        str.append(")");
        System.out.println(str.toString());
        return str.toString();
    }

    @Override
    public String getUpdateSql() {
        StringBuilder str = new StringBuilder();
        str.append("update ");
        str.append(entityClassMetaData.getName());
        str.append(" set ");
        List<Field> list = entityClassMetaData.getFieldsWithoutId();
        for (Field field : list) {
            str.append(field.getName() + " = ?, ");
        }
        str.substring(1, str.length() - 1);
        str.append(" where ");
        str.append(entityClassMetaData.getIdField().getName());
        str.append(" = ?");
        System.out.println(str.toString());
        return str.toString();
    }
}
