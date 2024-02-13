package com.orderdelivery.service;

import java.util.List;
import java.util.Map;

import com.orderdelivery.requestdto.PlaceOrdersRequest;
import com.orderdelivery.responsedto.OrderDeliveryResponse;

public interface OrderDeliveryService {

	public OrderDeliveryResponse placeOrder(PlaceOrdersRequest ordersRequest);

	public List<Object> getOrder(String page, String limit);

	public OrderDeliveryResponse takeOrder(String id, Map<String, Object> takeRequest);
}
