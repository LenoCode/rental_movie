package org.test.casumo.unit.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;
import org.test.casumo.modules.rent.domain.dto.response.MovieDto;
import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;
import org.test.casumo.modules.rent.domain.mapper.MovieMapper;
import org.test.casumo.modules.rent.domain.mapper.MovieMapperImpl;
import org.test.casumo.modules.rent.domain.mapper.RentMapperImpl;

import java.sql.Timestamp;

public class MapperTests {


    private final MovieMapperImpl movieMapper = new MovieMapperImpl();



    @Test
    @DisplayName("Test mapping from movie dao to dto")
    public void test_movie_general_mapping(){
        MovieDao movieDao = new MovieDao();
        MovieTypeDao movieTypeDao = new MovieTypeDao();
        movieTypeDao.setType("New release");
        movieDao.setMovieTypeDao(movieTypeDao);
        movieDao.setName("Test movie");


        MovieDto movieDto = movieMapper.generalMapping(movieDao);

        Assertions.assertEquals("New release",movieDto.getType());
    }

    @Test
    @DisplayName("Test mapping from rent dao to dto")
    public void test_rent_mapping_dto(){
        MovieDao movieDao = new MovieDao();
        MovieTypeDao movieTypeDao = new MovieTypeDao();
        movieTypeDao.setType("New release");
        movieDao.setMovieTypeDao(movieTypeDao);
        movieDao.setName("Test movie");

        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(1L);
        customerDao.setName("unit");
        customerDao.setLastname("test");
        customerDao.setAddress("springBoot");

        RentsDao rentsDao = new RentsDao();
        rentsDao.setMovieDao(movieDao);
        rentsDao.setCustomerDao(customerDao);
        rentsDao.setReturnDate(new Timestamp(System.currentTimeMillis()));

        RentDetailsDto rentDetailsDto = RentMapperImpl.INSTANCE.generalMapping(rentsDao);

        Assertions.assertEquals("unit",rentDetailsDto.getCustomer().getName());
        Assertions.assertEquals("Test movie",rentDetailsDto.getMovie().getName());
    }

}
