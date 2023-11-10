package com.resellerapp.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true,nullable = false)
    @Length(min = 3,max = 20)
    private String username;

    @Column(nullable = false)
    @Length(min = 3,max = 20)
    private String password;


    @Email
    private String email;

    @OneToMany
    @JoinColumn
    private Set<Offer> offers;

    @OneToMany
    @JoinColumn
    private Set<Offer> boughtOffers;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(Set<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }
}
