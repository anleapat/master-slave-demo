package com.ms.demo.order.service;

import com.ms.demo.common.datasource.TargetDs;
import com.ms.demo.order.entity.Order;
import com.ms.demo.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderMapper mapper;

    @TargetDs(name = "slave")
    public Order getOrder(long id) {
        return mapper.get(id);
    }

    public int saveOrder(Order order) {
        return mapper.insert(order);
    }

    /**
     * 从库只读写表会报错，如果此表报没有权限说明程序测试ok（或者一个库建一个只读账号用于slave数据源一样效果）
     */
    @TargetDs(name = "slave")
    public int saveOrderError(Order order) {
        return mapper.insert(order);
    }
}
