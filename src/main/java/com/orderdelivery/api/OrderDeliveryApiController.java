package com.orderdelivery.api;

import com.orderdelivery.requestdto.PlaceOrdersRequest;
import com.orderdelivery.requestdto.TakeOrdersRequest;
import com.orderdelivery.responsedto.OrderDeliveryResponse;

public interface OrderDeliveryApiController {

	public OrderDeliveryResponse placeOrder(PlaceOrdersRequest ordersRequest);

	OrderDeliveryResponse takeOrder(String id, TakeOrdersRequest takeRequest);

	public Object getOrder(String page, String limit);
}
