package com.ms.demo;

import com.ms.demo.order.entity.Order;
import com.ms.demo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterSlaveSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    public void slaveDsTest() {
        orderService.getOrder(1);
    }

    @Test
    public void masterDsTest() {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setUserId(1L);
        orderService.saveOrder(order);
    }

    /**
     * 从库只读写表会报错，如果此测试运行后报没有权限
     * 说明程序测试ok（可以不用真实搭建主从，同一个库建一个只读账号用于slave数据源一样效果）
     */
    @Test
    public void slaveDsWriteErrorTest() {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setUserId(1L);
        orderService.saveOrderError(order);
    }
}
