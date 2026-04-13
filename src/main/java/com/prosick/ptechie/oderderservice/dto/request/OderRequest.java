package com.prosick.ptechie.oderderservice.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class OderRequest {

	
	private List<OderLineItemRequests> items;
}
