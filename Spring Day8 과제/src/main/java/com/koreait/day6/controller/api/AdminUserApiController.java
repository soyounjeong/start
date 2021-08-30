package com.koreait.day6.controller.api;


import com.koreait.day6.controller.CrudController;
import com.koreait.day6.model.entity.AdminUser;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.request.AdminUserApiRequest;
import com.koreait.day6.model.network.response.AdminUserApiResponse;
import com.koreait.day6.service.AdminUserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminuser")
@RequiredArgsConstructor
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {
    private final AdminUserApiLogicService adminUserApiLogicService;

    @Override
    public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> request) {
        return super.create(request);
    }

    @Override
    public Header<AdminUserApiResponse> read(@PathVariable(name="id") Long id) {
        return super.read(id);
    }

    @Override
    public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> request) {
        return super.update(request);
    }

    @Override
    public Header<AdminUserApiResponse> delete(@PathVariable(name="id") Long id) {
        return super.delete(id);
    }

    @GetMapping("")
    public Header<List<AdminUserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        return adminUserApiLogicService.search(pageable);
    }
}
