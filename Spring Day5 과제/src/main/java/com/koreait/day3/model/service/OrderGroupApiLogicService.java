package com.koreait.day3.model.service;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.OrderGroup;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.OrderGroupApiRequest;
import com.koreait.day3.model.network.response.OrderGroupApiResponse;
import com.koreait.day3.model.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    private final OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .id(orderGroupApiRequest.getId())
                .status(orderGroupApiRequest.getStatus())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        Optional<OrderGroup> optional = orderGroupRepository.findById(orderGroupApiRequest.getId());
        return optional.map(orderGroup -> {
            orderGroup.setId(orderGroupApiRequest.getId());
            orderGroup.setStatus(orderGroupApiRequest.getStatus());
            orderGroup.setRevAddress(orderGroupApiRequest.getRevAddress());
            orderGroup.setRevName(orderGroupApiRequest.getRevName());
            orderGroup.setTotalPrice(orderGroupApiRequest.getTotalPrice());
            orderGroup.setTotalQuantity(orderGroupApiRequest.getTotalQuantity());
            return  orderGroup;
        }).map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional.map(orderGroup -> {
            orderGroupRepository.delete(orderGroup);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse userApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .build();
        return  Header.OK(userApiResponse);
    }
}
