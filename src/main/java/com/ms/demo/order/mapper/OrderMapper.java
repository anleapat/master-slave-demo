package com.ms.demo.order.mapper;

import com.ms.demo.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int delete(Long orderId);

    int insert(Order record);

    Order get(Long orderId);

    int update(Order record);
}