package org.test.casumo.modules.rent.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.test.casumo.modules.rent.domain.dao.MovieDao;

import java.util.List;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieDao,Long>, JpaRepository<MovieDao,Long> {


    /**
     *
     * @param type
     * @return
     */
    @Query(value = "select m from MovieDao m inner join MovieTypeDao mt on m.movieTypeDao.id = mt.id where mt.id=?1")
    List<MovieDao> findAllMoviesByType(Long type);
}