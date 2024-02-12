package org.test.casumo.unit.domain;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;
import org.test.casumo.modules.rent.domain.repository.CustomerRepository;
import org.test.casumo.modules.rent.domain.repository.MovieRepository;
import org.test.casumo.modules.rent.domain.repository.MovieTypeRepository;
import org.test.casumo.modules.rent.domain.repository.RentsRepository;
import org.test.casumo.modules.rent.service.domain.RentService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DaoRelationshipTests {



    @Autowired
    private MovieTypeRepository movieTypeRepository;


    @Autowired
    private MovieRepository movieRepository;


    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private RentsRepository rentsRepository;




    @Test
    @DisplayName("Test movie and movie type creation")
    @Order(1)
    public void test_movie_and_movie_type_creation_and_fetching(){
        Optional<MovieTypeDao> movieTypeDao = movieTypeRepository.findByType("New releases");
        Assertions.assertFalse(movieTypeDao.isEmpty());

        MovieDao movieDao = new MovieDao();
        movieDao.setName("Test movie");
        movieDao.setMovieTypeDao(movieTypeDao.get());

        movieRepository.save(movieDao);

        List<MovieDao> list =  movieRepository.findAllMoviesByType(movieTypeDao.get().getId());
        Assertions.assertEquals(1,list.size());
        Assertions.assertEquals("Test movie",movieDao.getName());
    }


    @Test
    @DisplayName("Test basic rent creation")
    @Order(2)
    public void test_basic_rent_creation() throws InterruptedException {
        Optional<MovieTypeDao> movieTypeDao = movieTypeRepository.findByType("New releases");
        Assertions.assertFalse(movieTypeDao.isEmpty());

        MovieDao movieDao = new MovieDao();
        movieDao.setName("Test movie 1");
        movieDao.setMovieTypeDao(movieTypeDao.get());
        movieRepository.save(movieDao);

        Optional<CustomerDao> customerDao = customerRepository.findByName("unit");
        Assertions.assertFalse(customerDao.isEmpty());

        RentsDao rentsDao = new RentsDao();

        rentsDao.setMovieDao(movieDao);
        rentsDao.setCustomerDao(customerDao.get());
        rentsDao.setReturnDate(new Timestamp(System.currentTimeMillis()));
        Thread.sleep(2000);

        rentsRepository.save(rentsDao);

        Assertions.assertEquals(rentsRepository.findAllExpiredRents().size(),1);
    }

    @Test
    @DisplayName("Test query for checking if movie is rented")
    @Order(3)
    public void test_query_for_checking_if_movie_is_rented(){
        Optional<MovieTypeDao> movieTypeDao = movieTypeRepository.findByType("New releases");
        Assertions.assertFalse(movieTypeDao.isEmpty());

        MovieDao movieDao = new MovieDao();
        movieDao.setName("Test movie 2");
        movieDao.setMovieTypeDao(movieTypeDao.get());
        movieRepository.save(movieDao);

        Optional<CustomerDao> customerDao = customerRepository.findByName("unit");
        RentsDao rentsDao = new RentsDao();

        rentsDao.setMovieDao(movieDao);
        rentsDao.setCustomerDao(customerDao.get());
        rentsDao.setActive(true);
        rentsDao.setReturnDate(new Timestamp(System.currentTimeMillis()));

        rentsRepository.save(rentsDao);

        Optional<MovieDao> movie = rentsRepository.checkIfMovieIsRented(movieDao.getId());

        Assertions.assertFalse(movie.isEmpty());
        Assertions.assertEquals("Test movie 2",movie.get().getName());


        rentsDao.setActive(false);
        rentsRepository.save(rentsDao);

        movie = rentsRepository.checkIfMovieIsRented(movieDao.getId());
        Assertions.assertTrue(movie.isEmpty());
    }
}
