package org.test.casumo.modules.rent.service.domain.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;
import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;
import org.test.casumo.modules.rent.domain.mapper.RentMapper;
import org.test.casumo.modules.rent.domain.mapper.RentMapperImpl;
import org.test.casumo.modules.rent.domain.repository.CustomerRepository;
import org.test.casumo.modules.rent.domain.repository.MovieRepository;
import org.test.casumo.modules.rent.domain.repository.RentsRepository;
import org.test.casumo.modules.rent.service.domain.RentService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RentsRepository rentsRepository;

    @Autowired
    private CustomerRepository customerRepository;



    /**
     *
     * @param customerId
     * @param movieId
     * @param days
     * @return
     * @throws Exception
     */
    @Override
    public RentDetailsDto rentMovie(long customerId, long movieId,int days) throws Exception {
        Optional<MovieDao> movie = movieRepository.findById(movieId);
        if(movie.isEmpty())
            throw new EntityNotFoundException("No movie with id"+movieId);

        Optional<CustomerDao> customer = customerRepository.findById(customerId);
        if(customer.isEmpty())
            throw new EntityNotFoundException("No customer with id"+customerId);

        if(rentsRepository.checkIfMovieIsRented(movieId).isEmpty()){
            RentsDao rentsDao = new RentsDao();
            rentsDao.setActive(true);
            rentsDao.setMovieDao(movie.get());
            rentsDao.setCustomerDao(customer.get());
            rentsDao.setReturnDate(RentService.getTimestampOfRentDate(days));
            rentsDao.setPrice(movie.get().getMovieTypeDao().getPrice() * days);

            rentsRepository.save(rentsDao);

            return RentMapperImpl.INSTANCE.generalMapping(rentsDao);
        }else{
            throw new Exception("Movie is already rented");
        }
    }

    @Override
    public boolean isMoveAvailable(long movieId) {
        return rentsRepository.checkIfMovieIsRented(movieId).isEmpty();
    }






}
