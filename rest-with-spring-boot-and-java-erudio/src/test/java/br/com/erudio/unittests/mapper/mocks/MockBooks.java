package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.model.Books;

public class MockBooks {


    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksVO mockVO() {
        return mockVO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> bookss = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            bookss.add(mockEntity(i));
        }
        return bookss;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> bookss = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            bookss.add(mockVO(i));
        }
        return bookss;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setId(number.longValue());
        books.setAuthor("Some Author" + number);
        books.setDateTime(new Date());
        books.setPrice(25D);
        books.setTitle("Some Title" + number);
        return books;
    }

    public BooksVO mockVO(Integer number) {
        BooksVO books = new BooksVO();
        books.setId(number.longValue());
        books.setAuthor("Some Author" + number);
        books.setDateTime(new Date());
        books.setPrice(25D);
        books.setTitle("Some Title" + number);
        return books;
    }

}
