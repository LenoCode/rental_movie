package org.test.casumo.modules.rent.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieTypeRepository extends PagingAndSortingRepository<MovieTypeDao,Long>, JpaRepository<MovieTypeDao,Long> {

    /**
     * Finding by name
     * Returns list because there can be datasets with same name. Because user is the one who defining
     * @TODO Check if this should be unique
     * @param name
     * @return
     */
    Optional<MovieTypeDao> findByType(String name);





}
