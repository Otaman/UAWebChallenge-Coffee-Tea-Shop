package ua.web_challenge.coffee_tea_shop.entity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Created on 15.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "drinks")
public class Drink {
    private int id;
    private String name;
    private DrinkType type;
    private int weightInGram;
    private int priceInCent;
    private Country country;
    private Producer producer;
    private String description;

    public Drink() {
    }

    public Drink(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "type_id")
    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "weight")
    public int getWeightInGram() {
        return weightInGram;
    }

    public void setWeightInGram(int weightInGram) {
        this.weightInGram = weightInGram;
    }

    @Basic
    @Column(name = "price")
    public int getPriceInCent() {
        return priceInCent;
    }

    public void setPriceInCent(int priceInCent) {
        this.priceInCent = priceInCent;
    }

    @OneToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToOne
    @JoinColumn(name = "producer_id")
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Basic(fetch = LAZY)
    @Lob
    @Column(name = "descr", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
