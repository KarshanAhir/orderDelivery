package com.orderdelivery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "PLACE_ORDERS_REQUEST")
@Getter
@Setter
public class PlaceOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SKEY")
	private Long skey;

	@Column(name = "DISTANCE")
	private long distance;

	@Column(name = "STATUS")
	private String status;

	@Version
	@Column(name = "VERSION")
	private Long version;
}
