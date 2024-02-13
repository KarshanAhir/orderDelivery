package com.orderdelivery.responsedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderResponse extends OrderDeliveryResponse {
	private Long id;
	private Long distance;
	private String status;
}
