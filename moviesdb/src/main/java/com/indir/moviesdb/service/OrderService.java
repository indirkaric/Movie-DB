package com.indir.moviesdb.service;

import com.indir.moviesdb.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface OrderService {
    ResponseEntity<OrderDto> saveOrder(OrderDto orderDto);
    List<OrderDto> getOrders();
    OrderDto findById(Integer id);
    void deleteOrder(Integer id);
}
