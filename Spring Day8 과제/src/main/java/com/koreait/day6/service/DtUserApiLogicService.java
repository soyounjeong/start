package com.koreait.day6.service;


import com.koreait.day6.model.entity.DtUser;
import com.koreait.day6.model.enumclass.DtUserStatus;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.Pagination;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 서비스레이어, 내부에서 자바로직을 처리함
@RequiredArgsConstructor
public class DtUserApiLogicService extends BaseService<DtUserApiRequest, DtUserApiResponse, DtUser> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final ItemApiLogicService itemApiLogicService;

    @Override
    public Header<DtUserApiResponse> create(Header<DtUserApiRequest> request) {

        DtUserApiRequest userApiRequest = request.getData();

        DtUser user = DtUser.builder()
                .userid(userApiRequest.getUserid())
                .userpw(userApiRequest.getUserpw())
                .hp(userApiRequest.getHp())
                .email(userApiRequest.getEmail())
                .status(DtUserStatus.REGISTERED)
                .build();
        DtUser newUser = baseRepository.save(user);
        return Header.OK(response(newUser));
    }

    @Override
    public Header<DtUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> response(user))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<DtUserApiResponse> update(Header<DtUserApiRequest> request) {
        DtUserApiRequest userApiRequest = request.getData();
        Optional<DtUser> optional = baseRepository.findById(userApiRequest.getId());
        return optional.map(user -> {

            user.setUserid(userApiRequest.getUserid());
            user.setUserpw(userApiRequest.getUserpw());
            user.setHp(userApiRequest.getHp());
            user.setEmail(userApiRequest.getEmail());
            user.setStatus(userApiRequest.getStatus());

            return user;
        }).map(user -> baseRepository.save(user))
                .map(user -> response(user))
                // 찾았을때 ok 실행
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<DtUser> optional = baseRepository.findById(id);

        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private DtUserApiResponse response(DtUser user){
        DtUserApiResponse userApiResponse = DtUserApiResponse.builder()
                .id(user.getId())
                .userid(user.getUserid())
                .userpw(user.getUserpw())
                .email(user.getEmail())
                .hp(user.getHp())
                .regDate(user.getRegDate())
                .status(user.getStatus())
                .build();
        return userApiResponse;
    }

    // 사용자 구매 정보
//    public Header<DtUserOrderInfoApiResponse> orderInfo(Long id){
//        DtUser user = baseRepository.getById(id);
//        DtUserApiResponse userApiResponse = response(user);
//
//        List<OrderGroup> orderGroupList = user.getOrderGroupList();
//        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream() // stream 데이터로 읽어옴
//                .map(orderGroup -> {
//                    // 데이터를 가져
//                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
//                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
//                            // 데이터 가져온것을 detail에 담아서 item을 가지고 빙글뱅글
//                            .map(detail -> detail.getItem())
//                            .map(item -> itemApiLogicService.response(item).getData())
//                            .collect(Collectors.toList()); // collect는 stream의 데이터를 변형등의 처리를 하고 원하는 자료형으로 변환
//
//                        orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
//                        return orderGroupApiResponse;
//                })
//                .collect(Collectors.toList()); // 전체적으로 list를 만들어서 담아줌
//        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
//        DtUserOrderInfoApiResponse userOrderInfoApiResponse = DtUserOrderInfoApiResponse.builder()
//                .userApiResponse(userApiResponse)
//                .build();
//        return Header.OK(userOrderInfoApiResponse);
//    }

    // 8월 30일 수업
    public Header<List<DtUserApiResponse>> search(Pageable pageable){
        Page<DtUser> user = baseRepository.findAll(pageable);
        List<DtUserApiResponse> userApiResponseList = user.stream()
                .map(users -> response(users))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(user.getTotalPages())
                .totalElements(user.getTotalElements())
                .currentPage(user.getNumber())
                .currentElements(user.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList, pagination);
    }

}
