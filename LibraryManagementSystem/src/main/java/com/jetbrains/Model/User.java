package com.jetbrains.Model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "users")
public class User extends Person{

	@Column
	private String username;
	@Column
	private	String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@ManyToOne
//	@JoinColumn(name = "village_id")
	private Location village_id;
	@OneToMany(mappedBy = "reader")
	private List<Borrower> borrowers;
	@OneToMany(mappedBy = "reader",fetch = FetchType.EAGER)
	private List<Membership> memberships;

	public User(String person_id) {
		super(person_id);
		// TODO Auto-generated constructor stub
	}
	public User(){
		// TODO Auto-generated constructor stub
	}



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Location getVillage_id() {
		return village_id;
	}
	public void setVillage_id(Location village_id) {
		this.village_id = village_id;
	}
	public List<Borrower> getBorrowers() {
		return borrowers;
	}
	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}
	public List<Membership> getMemberships() {
		return memberships;
	}
	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

}
