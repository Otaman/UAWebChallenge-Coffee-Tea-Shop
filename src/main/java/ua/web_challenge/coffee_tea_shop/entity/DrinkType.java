package ua.web_challenge.coffee_tea_shop.entity;

import javax.persistence.*;

/**
 * Created on 15.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "drink_types")
public class DrinkType {
    private int id;
    private String name;

    public DrinkType() {
    }

    public DrinkType(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
