package com.orderdelivery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderdelivery.entity.PlaceOrders;

@Repository
public interface OrderPlaceRepository extends JpaRepository<PlaceOrders, Long> {

	PlaceOrders findBySkey(Long skey);

	@Override
	Page<PlaceOrders> findAll(Pageable pageable);
}