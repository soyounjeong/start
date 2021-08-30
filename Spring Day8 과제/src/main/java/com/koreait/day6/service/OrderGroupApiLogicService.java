package com.koreait.day6.service;


import com.koreait.day6.model.entity.OrderGroup;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.Pagination;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.OrderGroupApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.OrderGroupApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .id(orderGroupApiRequest.getId())
                .orderType(orderGroupApiRequest.getOrderType())
                .status(orderGroupApiRequest.getStatus())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .build();
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        Optional<OrderGroup> optional = baseRepository.findById(orderGroupApiRequest.getId());
        return optional.map(orderGroup -> {
            orderGroup.setId(orderGroup.getId());
            orderGroup.setOrderType(orderGroup.getOrderType());
            orderGroup.setStatus(orderGroup.getStatus());
            orderGroup.setRevAddress(orderGroup.getRevAddress());
            orderGroup.setRevName(orderGroup.getRevName());
            orderGroup.setPaymentType(orderGroup.getPaymentType());
            orderGroup.setTotalPrice(orderGroup.getTotalPrice());
            orderGroup.setTotalQuantity(orderGroup.getTotalQuantity());

            return orderGroup;
        }).map(orderGroup -> baseRepository.save(orderGroup))
                .map(orderGroup -> response(orderGroup))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = baseRepository.findById(id);

        return optional.map(orderGroup -> {
            baseRepository.delete(orderGroup);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private OrderGroupApiResponse response(OrderGroup orderGroup){
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .orderType(orderGroup.getOrderType())
                .status(orderGroup.getStatus())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .build();
        return orderGroupApiResponse;
    }

//    public Header<List<OrderGroupApiResponse>> search(Pageable pageable){
//        Page<OrderGroup> orderGroup = baseRepository.findAll(pageable);
//        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroup.stream()
//                .map(orderGroup1 -> response(orderGroup1))
//                .collect(Collectors.toList());
//        Pagination pagination = Pagination.builder()
//                .totalPages(orderGroup.getTotalPages())
//                .totalElements(orderGroup.getTotalElements())
//                .currentPage(orderGroup.getNumber())
//                .currentElements(orderGroup.getNumberOfElements())
//                .build();
//        return Header.OK(orderGroupApiResponseList, pagination);
//    }
}
