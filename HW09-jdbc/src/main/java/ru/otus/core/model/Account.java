package ru.otus.core.model;

import ru.otus.Id;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class Account {
    @Id
    private long no;
    private String type;
    private int rest;

    public Account() {
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "No=" + no +
                ", Type='" + type + '\'' +
                " Rest=" + rest +
                '}';
    }
}
