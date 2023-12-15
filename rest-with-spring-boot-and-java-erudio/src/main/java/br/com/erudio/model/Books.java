package br.com.erudio.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 80)
    private String author;

    @Column(name = "launch_date", nullable = false, length = 80)
    private Date dateTime;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "title", nullable = false)
    private String title;

    public Books(){}

    public Books(Long id, String author, Date dateTime, double price, String title) {
        this.id = id;
        this.author = author;
        this.dateTime = dateTime;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books books)) return false;
        return Double.compare(books.getPrice(), getPrice()) == 0 && Objects.equals(getId(), books.getId()) && Objects.equals(getAuthor(), books.getAuthor()) && Objects.equals(getDateTime(), books.getDateTime()) && Objects.equals(getTitle(), books.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getDateTime(), getPrice(), getTitle());
    }
}
