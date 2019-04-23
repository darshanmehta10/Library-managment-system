package com.msu.elibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Calendar;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author VPatel
 */
@Entity
@Table(name = "book_card")
public class BookCard {
    @Id
    @Column(name = "Card_Id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Book_Id")
    @JsonIgnore
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Viewer_id")
    @JsonIgnore
    private User user;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Calendar issueDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Calendar getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Calendar issueDate) {
		this.issueDate = issueDate;
	}

    @Override
    public String toString() {
        return "BookCard{" +
                "id=" + id +
                '}';
    }
}