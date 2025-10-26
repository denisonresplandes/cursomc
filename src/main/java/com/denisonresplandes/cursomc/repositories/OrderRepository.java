package com.denisonresplandes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.denisonresplandes.cursomc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
