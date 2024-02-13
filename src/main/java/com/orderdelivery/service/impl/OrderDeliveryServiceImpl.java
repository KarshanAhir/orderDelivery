package com.orderdelivery.service.impl;

import static com.orderdelivery.util.StatusCode.TAKEN;
import static com.orderdelivery.util.StatusCode.UNASSIGNED;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.orderdelivery.entity.PlaceOrders;
import com.orderdelivery.repository.OrderPlaceRepository;
import com.orderdelivery.requestdto.PlaceOrdersRequest;
import com.orderdelivery.responsedto.OrderDeliveryError;
import com.orderdelivery.responsedto.OrderDeliveryResponse;
import com.orderdelivery.responsedto.OrderDeliverySucess;
import com.orderdelivery.responsedto.PlaceOrderResponse;
import com.orderdelivery.service.OrderDeliveryService;
import com.orderdelivery.util.GoogleMapsUtil;

@Service
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

	@Autowired
	private OrderPlaceRepository orderPlaceRepository;

	@Override
	public OrderDeliveryResponse placeOrder(PlaceOrdersRequest ordersRequest) {
		try {
			PlaceOrders placeOrders = new PlaceOrders();
			placeOrders.setStatus(UNASSIGNED.getMessage());
			try {
				placeOrders
						.setDistance(GoogleMapsUtil.getDistanceMock(ordersRequest.getOrigin().toString(), ordersRequest.getDestination().toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			orderPlaceRepository.save(placeOrders);

			PlaceOrderResponse orderResponse = new PlaceOrderResponse();
			orderResponse.setDistance(placeOrders.getDistance());
			orderResponse.setId(placeOrders.getSkey());
			orderResponse.setStatus(placeOrders.getStatus());
			return orderResponse;
		} catch (Exception e) {
			OrderDeliveryError error = new OrderDeliveryError();
			error.setError("Fail to save order");
			return error;
		}
	}

	@Override
	public OrderDeliveryResponse takeOrder(String id, Map<String, Object> takeRequest) {
		PlaceOrders orders = orderPlaceRepository.findBySkey(Long.valueOf(id));
		if (null == orders) {
			return new OrderDeliveryError("Detail not found by id");
		} else if (!orders.getStatus().equals(TAKEN.getMessage())) {
			if (!isEmpty(takeRequest)) {
				takeRequest.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(PlaceOrders.class, key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, orders, value);
				});
				orderPlaceRepository.save(orders);
				OrderDeliverySucess success = new OrderDeliverySucess();
				success.setStatus("SUCCESS");
				return success;
			} else {
				orders.setStatus(TAKEN.getMessage());
				orderPlaceRepository.save(orders);
				OrderDeliverySucess success = new OrderDeliverySucess();
				success.setStatus("SUCCESS");
				return success;
			}
		} else {
			OrderDeliveryError error = new OrderDeliveryError();
			error.setError("Order already taken by other partner");
			return error;


		}
	}

	@Override
	public List<Object> getOrder(String page, String limit) {
		Pageable pagination = PageRequest.of(Integer.valueOf(page), Integer.valueOf(limit));
		Page<PlaceOrders> list = orderPlaceRepository.findAll(pagination);
		if (list.isEmpty()) {
			return new ArrayList<Object>();
		}
		return list.get().collect(Collectors.toList());
	}

}
