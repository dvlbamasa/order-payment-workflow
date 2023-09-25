package com.marcura.payment;

import com.marcura.common.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:31 pm
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    Payment toEntity(OrderDto orderDto);
}
