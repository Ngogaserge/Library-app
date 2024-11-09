package com.jetbrains.Model;


import java.util.List;
import java.util.UUID;
import com.jetbrains.Model.Room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Shelf {

	@Id
	@Column
	private UUID shelf_id = UUID.randomUUID();
	@Column
	private int available_stock;
	private int initial_stock;
	@Column
	private int borrowed_number;
	@Column
	private String book_category;
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	@OneToMany(mappedBy = "shelf")
	private List<Book> books;


	public Shelf(UUID shelf_id) {
		super();
		this.shelf_id = shelf_id;
	}

	public Shelf() {
		super();
	}

	public UUID getShelf_id() {
		return shelf_id;
	}
	public void setShelf_id(UUID shelf_id) {
		this.shelf_id = shelf_id;
	}
	public int getAvailable_stock() {
		return available_stock;
	}
	public void setAvailable_stock(int available_stock) {
		this.available_stock = available_stock;
	}
	public int getInitial_stock() {
		return initial_stock;
	}
	public void setInitial_stock(int initial_stock) {
		this.initial_stock = initial_stock;
	}
	public int getBorrowed_number() {
		return borrowed_number;
	}
	public void setBorrowed_number(int borrowed_number) {
		this.borrowed_number = borrowed_number;
	}
	public String getBook_category() {
		return book_category;
	}
	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}



}
