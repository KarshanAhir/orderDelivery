package com.orderdelivery.api.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orderdelivery.requestdto.PlaceOrdersRequest;
import com.orderdelivery.responsedto.OrderDeliveryError;
import com.orderdelivery.responsedto.OrderDeliveryResponse;
import com.orderdelivery.service.OrderDeliveryService;

@RestController
public class OrderDeliveryApiControllerImpl {

	@Autowired
	private OrderDeliveryService deliveryService;

	@PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OrderDeliveryResponse placeOrder(@RequestBody PlaceOrdersRequest ordersRequest) {
		return deliveryService.placeOrder(ordersRequest);
	}

	@PatchMapping(value = "/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody OrderDeliveryResponse takeOrder(@PathVariable String id, @RequestBody Map<String, Object> takeRequest) {
		return deliveryService.takeOrder(id, takeRequest);
	}

	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getOrder(@RequestParam String page, @RequestParam String limit) {
		if (StringUtils.isEmpty(page)) {
			return new OrderDeliveryError("Page number should not be blank");
		}
		if (StringUtils.isEmpty(limit)) {
			return new OrderDeliveryError("Limit should not be blank");
		}
		if (!org.apache.commons.lang3.StringUtils.isNumeric(page) || !org.apache.commons.lang3.StringUtils.isNumeric(limit)) {
			return new OrderDeliveryError("page number and limit should be numeric value");
		}
		if (page.indexOf('1') != 0) {
			return new OrderDeliveryError("Page number must starts with 1");
		}
		return deliveryService.getOrder(page, limit);
	}
}
