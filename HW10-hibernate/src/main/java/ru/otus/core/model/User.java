package ru.otus.core.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = AddressDataSet.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id")
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones;

    public User() {
    }

    public User(String name, AddressDataSet address, List<PhoneDataSet> phones) {
        this.name = name;
        this.address = address;
        this.phones = phones;
        for (PhoneDataSet phone : phones) {
            phone.setUser(this);
        }
    }

    public User(String name, AddressDataSet address) {
        this.name = name;
        this.address = address;
        this.phones = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
        for (PhoneDataSet phone : phones) {
            phone.setUser(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(PhoneDataSet phone : this.phones) {
            stringBuilder.append(phone.getNumber() + " ");
        }

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street' " + this.address.getStreet() +
                ", phones' " + stringBuilder.toString() +
                '}';
    }





}
