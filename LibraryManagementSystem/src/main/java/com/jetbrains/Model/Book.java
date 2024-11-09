package com.jetbrains.Model;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
    @Id
    @Column
    private UUID book_id = UUID.randomUUID();
    @Column
    private int edition;
    @Column(name = "isbn_code")
    private String ISBNCode;
    @Column
    private Date publication_year;
    @Column
    private String publisher_name;
    @Column
    private String title;
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;
    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private BookStatus status;
    @OneToMany(mappedBy = "book")
    private List<Borrower> borrowers;

    public Book() {
    }

    public UUID getBook_id() {
        return book_id;
    }
    public Book(UUID bookId) {
        this.book_id = bookId;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
    }
    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }
    public String getISBNCode() {
        return ISBNCode;
    }
    public void setISBNCode(String iSBNCode) {
        ISBNCode = iSBNCode;
    }
    public Date getPublication_year() {
        return publication_year;
    }
    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }
    public String getPublisher_name() {
        return publisher_name;
    }
    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Shelf getShelf() {
        return shelf;
    }
    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
    public BookStatus getStatus() {
        return status;
    }
    public void setStatus(BookStatus status) {
        this.status = status;
    }
    public List<Borrower> getBorrowers() {
        return borrowers;
    }
    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

}
