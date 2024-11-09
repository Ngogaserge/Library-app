package com.jetbrains.Model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
@Entity
public class Borrower {
	@Id
	@Column
	private UUID id = UUID.randomUUID();
	@Column
	private Date due_date;
	@Column
	private Date return_date;
	@Column
	private Date pickup_date;
	@Column
	private int fine;
	@ManyToOne
	@JoinColumn(name = "reader_id")
	private User reader;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public Date getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(Date pickup_date) {
		this.pickup_date = pickup_date;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public User getReader() {
		return reader;
	}
	public void setReader(User reader) {
		this.reader = reader;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}



}