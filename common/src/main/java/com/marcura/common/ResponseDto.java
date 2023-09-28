package com.marcura.common;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 26/09/2023
 * Time: 2:19 pm
 */
@Data
public class ResponseDto{
    private String shipmentId;
    private String paymentTransactionId;
    private String orderId;
    private String errorMessage;
}
