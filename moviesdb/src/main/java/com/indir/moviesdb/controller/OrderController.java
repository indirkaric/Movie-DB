package com.indir.moviesdb.controller;

import com.indir.moviesdb.constants.Constants;
import com.indir.moviesdb.dto.OrderDto;
import com.indir.moviesdb.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(OrderController.ROOT_PATH)
public class OrderController {
    private final OrderService orderService;
    public static final String ROOT_PATH = "/api/v1/orders";

    @ApiOperation(value = Constants.SAVE_ORDER)
    @PostMapping("")
    public ResponseEntity saveOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result){
        return orderService.saveOrder(orderDto, result);
    }

    @ApiOperation(value = Constants.GET_ORDERS)
    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orders = orderService.getOrders();
        return new ResponseEntity<List<OrderDto>>(orders, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.FIND_ORDER)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Integer id){
        OrderDto orderDto = orderService.findById(id);
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);

    }

    @ApiOperation(value = Constants.DELETE_ORDER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
            orderService.deleteOrder(id);
            return new ResponseEntity<Void>(HttpStatus.OK);

    }

}
