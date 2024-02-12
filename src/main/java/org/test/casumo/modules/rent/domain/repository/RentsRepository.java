package org.test.casumo.modules.rent.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentsRepository extends PagingAndSortingRepository<RentsDao,Long>, JpaRepository<RentsDao,Long> {



    @Query(value = "select r from RentsDao r where r.returnDate < current_timestamp()")
    List<RentsDao> findAllExpiredRents();


    @Query(value="select m from MovieDao m inner join RentsDao r on r.movieDao.id = m.id where r.active=true and m.id=?1")
    Optional<MovieDao> checkIfMovieIsRented(long movieId);
}
