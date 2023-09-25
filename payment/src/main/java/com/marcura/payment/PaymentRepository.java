package com.marcura.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:09 pm
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
