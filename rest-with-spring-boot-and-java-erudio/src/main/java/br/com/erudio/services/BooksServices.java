package br.com.erudio.services;

import br.com.erudio.controllers.BooksController;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.Mapper;
import br.com.erudio.mapper.custom.BooksMapper;
import br.com.erudio.model.Books;
import br.com.erudio.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {
    private Logger logger = Logger.getLogger(BooksServices.class.getName());

    @Autowired
    BooksRepository repository;
    @Autowired
    BooksMapper mapper;

    public List<BooksVO> findAll() {

        logger.info("Finding all Books!");

        var books = Mapper.parseListObjects(repository.findAll(), BooksVO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BooksController.class).findById(p.getId())).withSelfRel()));
        return books;
    }



    public BooksVO findById(Long id) {

        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BooksVO vo = Mapper.parseObject(entity, BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BooksVO create(BooksVO books){
        if (books == null){throw new RequiredObjectIsNullException();}
        logger.info("Adding one book!");

        var entity = Mapper.parseObject(books, Books.class);
        BooksVO vo = Mapper.parseObject(entity, BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getId())).withSelfRel());
        return vo;

    }

    public BooksVO update(BooksVO books) {

        if(books==null){throw new RequiredObjectIsNullException();}

        logger.info("Updating one book!");

        var entity = repository.findById(books.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setId(books.getId());
        entity.setAuthor(books.getAuthor());
        entity.setTitle(books.getTitle());
        entity.setDateTime(books.getDateTime());
        entity.setPrice(books.getPrice());


        var vo =  Mapper.parseObject(repository.save(entity), BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one books!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
