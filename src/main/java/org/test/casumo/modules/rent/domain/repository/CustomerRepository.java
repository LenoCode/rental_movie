package org.test.casumo.modules.rent.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;

import java.util.Optional;


@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerDao,Long>, JpaRepository<CustomerDao,Long> {


    Optional<CustomerDao> findByName(String name);
}
