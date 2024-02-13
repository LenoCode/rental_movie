package org.test.casumo.integration.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;
import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;
import org.test.casumo.modules.rent.domain.dto.response.ReturnDetailsDto;
import org.test.casumo.modules.rent.domain.repository.CustomerRepository;
import org.test.casumo.modules.rent.domain.repository.MovieRepository;
import org.test.casumo.modules.rent.domain.repository.MovieTypeRepository;
import org.test.casumo.modules.rent.service.domain.RentService;
import org.test.casumo.modules.rent.service.domain.impl.RentServiceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RentServiceTests {

    @Autowired
    private RentService rentService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MovieTypeRepository movieTypeRepository;



    @Test
    @DisplayName("Basic rent test")
    public void basic_rent_test() throws Exception {
        Optional<MovieTypeDao> movieTypeDao = movieTypeRepository.findByType("New releases");
        Assertions.assertFalse(movieTypeDao.isEmpty());

        MovieDao movieDao = new MovieDao();
        movieDao.setName("Service test");
        movieDao.setMovieTypeDao(movieTypeDao.get());
        movieRepository.save(movieDao);

        Optional<CustomerDao> customerDao = customerRepository.findByName("unit");

        Assertions.assertTrue(rentService.isMoveAvailable(movieDao.getId()));
        RentDetailsDto rentDetailsDto =  rentService.rentMovie(customerDao.get().getId(),movieDao.getId(),5);
        Assertions.assertFalse(rentService.isMoveAvailable(movieDao.getId()));
        Assertions.assertEquals(movieTypeDao.get().getPrice() * 5, rentDetailsDto.getPrice());
    }


}
