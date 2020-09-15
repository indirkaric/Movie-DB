package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.domain.Order;
import com.indir.moviesdb.dto.OrderDto;
import com.indir.moviesdb.exception.NotFoundException;
import com.indir.moviesdb.mapper.OrderMapper;
import com.indir.moviesdb.repository.*;
import com.indir.moviesdb.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public ResponseEntity saveOrder(OrderDto orderDto, BindingResult result) {
        OrderDto order;
        if(result.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        if(!movieRepository.findById(orderDto.getMovie().getId()).isPresent())
            throw new NotFoundException(Constants.MOVIE_NOT_FOUND + orderDto.getMovie().getId());

        if(!userRepository.findById(orderDto.getUser().getId()).isPresent())
            throw new NotFoundException(Constants.USER_NOT_FOUND + orderDto.getUser().getId());

        try{
            order = orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto)));
        }
        catch (Exception e){
                log.error(e.getMessage());
                throw  e;
        }
        return  new ResponseEntity<OrderDto>(order, HttpStatus.CREATED);

    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orders;
        try{
            List<Order> tempOrders = new ArrayList<>();
            orderRepository.findAll().iterator().forEachRemaining(tempOrders::add);
            orders = tempOrders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return orders;
    }

    @Override
    public OrderDto findById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new NotFoundException(Constants.ORDER_NOT_FOUND + id);
        }
        return orderMapper.toDto(order.get());
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = orderMapper.toEntity(findById(id));
        orderRepository.delete(order);
    }
}
