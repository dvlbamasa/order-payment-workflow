package com.marcura.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 8:48 pm
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByOrderId(Long id);
}
