package com.marcura.order;

import com.marcura.common.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 10:41 am
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
