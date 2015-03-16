package ua.web_challenge.coffee_tea_shop.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created on 15.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "producers")
public class Producer {
    private int id;
    private String name;

    public Producer() {
    }

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "name", length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
