package com.denisonresplandes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.denisonresplandes.cursomc.domain.OrderItem;
import com.denisonresplandes.cursomc.domain.OrderItemPK;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
