package com.indir.moviesdb.mapper;

import com.indir.moviesdb.domain.Order;
import com.indir.moviesdb.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
}
