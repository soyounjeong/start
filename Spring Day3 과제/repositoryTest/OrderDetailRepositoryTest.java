package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
//        OrderDetail orderDetail = OrderDetail.builder()
//                .status("결제완료")
//                .quantity(1)
//                .totalPrice(BigDecimal.valueOf(1500000))
//                .regDate(LocalDateTime.now())
//                .itemId(1L)
//                .orderGroupId(3L) // 몇번그룹에 포함되는지
//                .build();
//        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        OrderDetail orderDetail = OrderDetail.builder()
                .status("결제완료")
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(3000000))
                .regDate(LocalDateTime.now())
                .itemId(2L)
                .orderGroupId(3L) // 몇번그룹에 포함되는지
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

    }
}
