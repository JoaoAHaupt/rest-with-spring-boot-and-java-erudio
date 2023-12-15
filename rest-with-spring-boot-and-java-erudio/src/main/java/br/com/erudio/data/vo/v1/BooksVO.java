package br.com.erudio.data.vo.v1;

import br.com.erudio.model.Books;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"BookIdentification", "BookAuthor","LaunchDate","BookPrice","BookTitle"})
public class BooksVO extends RepresentationModel<BooksVO> implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("BookIdentification")
    private Long id;

    @JsonProperty("BookAuthor")
    private String author;

    @JsonProperty("LaunchDate")
    private Date dateTime;

    @JsonProperty("BookPrice")
    private double price;

    @JsonProperty("BookTitle")
    private String title;

    public BooksVO(){}

    public BooksVO(Long id, String author, Date dateTime, double price, String title) {
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
        if (!(o instanceof BooksVO booksVO)) return false;
        return Double.compare(booksVO.getPrice(), getPrice()) == 0 && Objects.equals(getId(), booksVO.getId()) && Objects.equals(getAuthor(), booksVO.getAuthor()) && Objects.equals(getDateTime(), booksVO.getDateTime()) && Objects.equals(getTitle(), booksVO.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getDateTime(), getPrice(), getTitle());
    }
}
