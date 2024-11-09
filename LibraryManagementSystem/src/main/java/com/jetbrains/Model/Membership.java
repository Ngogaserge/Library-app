package com.jetbrains.Model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Membership {
    @Id
    @Column
    private UUID membership_id = UUID.randomUUID();
    @Column
    private String membership_code;
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User reader;
    @Column
    private Date expiring_date;
    @Column
    private Date registration_date;
    @Enumerated(EnumType.STRING)
    @Column
    private Status membership_status;
    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MembershipType membership_type;
    public UUID getMembership_id() {
        return membership_id;
    }
    public void setMembership_id(UUID membership_id) {
        this.membership_id = membership_id;
    }
    public String getMembership_code() {
        return membership_code;
    }
    public void setMembership_code(String membership_code) {
        this.membership_code = membership_code;
    }
    public User getReader() {
        return reader;
    }
    public void setReader(User reader) {
        this.reader = reader;
    }
    public Date getExpiring_date() {
        return expiring_date;
    }
    public void setExpiring_date(Date expiring_date) {
        this.expiring_date = expiring_date;
    }
    public Date getRegistration_date() {
        return registration_date;
    }
    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }
    public Status getMembership_status() {
        return membership_status;
    }
    public void setMembership_status(Status membership_status) {
        this.membership_status = membership_status;
    }
    public MembershipType getMembership_type() {
        return membership_type;
    }
    public void setMembership_type(MembershipType membership_type) {
        this.membership_type = membership_type;
    }


}
