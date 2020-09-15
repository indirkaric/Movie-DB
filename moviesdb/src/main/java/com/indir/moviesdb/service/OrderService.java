package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OrderService {
    ResponseEntity saveOrder(OrderDto orderDto, BindingResult result);
    List<OrderDto> getOrders();
    OrderDto findById(Integer id);
    void deleteOrder(Integer id);
}
