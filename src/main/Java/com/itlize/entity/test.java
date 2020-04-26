package com.itlize.entity;


import javax.persistence.*;

@Entity
@Table(name="test")
public class test {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="Name")
    private String Name;

    @Column(name="pass")
    private String pass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public test() {
    }

    public test(String name, String pass){
        this.Name=name;
        this.pass=pass;
    }
}
