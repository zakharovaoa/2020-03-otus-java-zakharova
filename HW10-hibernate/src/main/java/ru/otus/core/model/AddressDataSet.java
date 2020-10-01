package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id", nullable = false)
    private long id;

    @Column(name = "street")
    private String street;

    public AddressDataSet() {}

    public AddressDataSet(String street) {
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                ", street='" + street + '\'' +
                '}';
    }
}