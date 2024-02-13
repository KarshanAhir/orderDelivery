package com.orderdelivery.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeOrdersRequest {
	private String status;
	private String id;
}
