package br.com.erudio.mapper.custom;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.Books;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BooksMapper {
    public BooksVO convertEntityToVO(Books books){
        BooksVO vo = new BooksVO();

        vo.setId(books.getId());
        vo.setAuthor(books.getAuthor());
        vo.setPrice(books.getPrice());
        vo.setTitle(books.getTitle());
        vo.setDateTime(books.getDateTime());

        return vo;
    }

    public Books convertVoToEntity(BooksVO vo){
        Books books = new Books();

        books.setId(vo.getId());
        books.setAuthor(vo.getAuthor());
        books.setPrice(vo.getPrice());
        books.setDateTime(vo.getDateTime());
        books.setTitle(vo.getTitle());

        return books;
    }
}
