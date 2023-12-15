package br.com.erudio.mapper;



import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Books;
import br.com.erudio.model.Person;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Person.class, PersonVO.class).addMapping(Person :: getId, PersonVO :: setKey);
        mapper.createTypeMap(PersonVO.class, Person.class).addMapping(PersonVO :: getKey, Person :: setId);
        mapper.createTypeMap(BooksVO.class, Books.class).addMapping(BooksVO :: getId, Books :: setId);
        mapper.createTypeMap(Books.class, BooksVO.class).addMapping(Books :: getId, BooksVO :: setId);

    }

    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObject = new ArrayList<D>();
        for(O o: origin){
            destinationObject.add(mapper.map(o,destination));
        }
        return destinationObject;
    }
}
