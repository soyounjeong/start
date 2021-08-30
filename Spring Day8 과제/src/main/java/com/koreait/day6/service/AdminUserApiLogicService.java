package com.koreait.day6.service;


import com.koreait.day6.ifs.CrudInterface;
import com.koreait.day6.model.entity.AdminUser;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.Pagination;
import com.koreait.day6.model.network.request.AdminUserApiRequest;
import com.koreait.day6.model.network.response.AdminUserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 서비스레이어, 내부에서 자바로직을 처리함
@RequiredArgsConstructor
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {
    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest adminUserApiRequest = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .id(adminUserApiRequest.getId())
                .userid(adminUserApiRequest.getUserid())
                .userpw(adminUserApiRequest.getUserpw())
                .name(adminUserApiRequest.getName())
                .status(adminUserApiRequest.getStatus())
                .regDate(adminUserApiRequest.getRegDate())
                .build();
        AdminUser newAdminUser = baseRepository.save(adminUser);
        return Header.OK(response(newAdminUser));
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest adminUserApiRequest = request.getData();
        Optional<AdminUser> optional = baseRepository.findById(adminUserApiRequest.getId());
        return optional.map(adminUser -> {
            adminUser.setId(adminUser.getId());
            adminUser.setUserid(adminUser.getUserid());
            adminUser.setUserpw(adminUser.getUserpw());
            adminUser.setName(adminUser.getName());
            adminUser.setStatus(adminUser.getStatus());
            adminUser.setRegDate(adminUser.getRegDate());

            return adminUser;
        }).map(adminUser -> baseRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<AdminUser> optional = baseRepository.findById(id);

        return optional.map(adminUser -> {
            baseRepository.delete(adminUser);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private AdminUserApiResponse response(AdminUser adminUser){
        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .userid(adminUser.getUserid())
                .userpw(adminUser.getUserpw())
                .name(adminUser.getName())
                .status(adminUser.getStatus())
                .regDate(adminUser.getRegDate())
                .build();
        return adminUserApiResponse;
    }

    public Header<List<AdminUserApiResponse>> search(Pageable pageable){
        Page<AdminUser> adminUser = baseRepository.findAll(pageable);
        List<AdminUserApiResponse> adminUserApiResponseList = adminUser.stream()
                .map(adminUser1 -> response(adminUser1))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(adminUser.getTotalPages())
                .totalElements(adminUser.getTotalElements())
                .currentPage(adminUser.getNumber())
                .currentElements(adminUser.getNumberOfElements())
                .build();
        return Header.OK(adminUserApiResponseList, pagination);
    }
}
