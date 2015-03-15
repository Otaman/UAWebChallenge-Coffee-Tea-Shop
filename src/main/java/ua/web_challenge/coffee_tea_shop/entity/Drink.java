package ua.web_challenge.coffee_tea_shop.entity;

import javax.persistence.*;

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
    private String longDescription;

    public Drink() {
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
    @Column(name = "type_fk")
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
    @Column(name = "country_fk")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToOne
    @Column(name = "producer_fk")
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Basic
    @Lob
    @Column(name = "descr", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "long_desr", length = 2000)
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
