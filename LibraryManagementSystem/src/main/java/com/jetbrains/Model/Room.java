package com.jetbrains.Model;



import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {
	@Id
	@Column
	private UUID room_id = UUID.randomUUID();
	@Column
	private String roomCode;
	@OneToMany(mappedBy = "room")
	private List<Shelf> shelves;
	public Room(UUID room_id) {
		super();
		this.room_id = room_id;
	}
	public UUID getRoom_id() {
		return room_id;
	}
	public void setRoom_id(UUID room_id) {
		this.room_id = room_id;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public List<Shelf> getShelves() {
		return shelves;
	}
	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}
	public Room() {
		super();
	}


}
