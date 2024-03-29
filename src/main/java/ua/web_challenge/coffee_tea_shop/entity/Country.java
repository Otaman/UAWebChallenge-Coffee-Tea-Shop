package ua.web_challenge.coffee_tea_shop.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created on 15.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "countries")
public class Country {
    private int id;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", length = 25, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
