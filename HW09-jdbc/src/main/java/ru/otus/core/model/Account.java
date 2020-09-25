package ru.otus.core.model;

import ru.otus.Id;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class Account {
    @Id
    private final long no;
    private final String type;
    private final int rest;

    public Account(long no, String type, int rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public int getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return "User{" +
                "No=" + no +
                ", Type='" + type + '\'' +
                " Rest=" + rest +
                '}';
    }
}
