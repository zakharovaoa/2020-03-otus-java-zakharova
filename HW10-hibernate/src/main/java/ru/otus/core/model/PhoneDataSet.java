package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id", nullable = false)
    private long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(table = "phones", name="fk_phones_id")
    private User user;

    public PhoneDataSet() {}

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                ", number='" + number + '\'' +
                '}';
    }
}
