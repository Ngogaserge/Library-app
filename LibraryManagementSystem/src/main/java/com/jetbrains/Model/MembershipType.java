package com.jetbrains.Model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MembershipType {
    @Id
    @Column
    private UUID membership_type_id = UUID.randomUUID();
    @Column
    private int max_books;
    @Column
    private int price;
    @Column
    private String membership_name;
    @OneToMany(mappedBy = "membership_type")
    private List<Membership> memberships;



    public MembershipType() {
        super();
    }
    public MembershipType(UUID membership_type_id) {
        this.membership_type_id = membership_type_id;
    }
    public UUID getMembership_type_id() {
        return membership_type_id;
    }
    public void setMembership_type_id(UUID membership_type_id) {
        this.membership_type_id = membership_type_id;
    }
    public int getMax_books() {
        return max_books;
    }
    public void setMax_books(int max_books) {
        this.max_books = max_books;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getMembership_name() {
        return membership_name;
    }
    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }
    public List<Membership> getMemberships() {
        return memberships;
    }
    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }


}
