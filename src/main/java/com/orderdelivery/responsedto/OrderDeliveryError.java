package com.orderdelivery.responsedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeliveryError extends OrderDeliveryResponse {
	private String error;

	public OrderDeliveryError() {
	}

	public OrderDeliveryError(String error) {
		this.error = error;
	}
}
