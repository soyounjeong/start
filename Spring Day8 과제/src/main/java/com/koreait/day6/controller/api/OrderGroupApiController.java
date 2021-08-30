package com.koreait.day6.controller.api;

import com.koreait.day6.controller.CrudController;
import com.koreait.day6.ifs.CrudInterface;
import com.koreait.day6.model.entity.OrderGroup;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.OrderGroupApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.OrderGroupApiResponse;
import com.koreait.day6.service.BaseService;
import com.koreait.day6.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.koreait.day6.model.network.Header.OK;


@RestController
@RequestMapping("/api/orderGroup")
@RequiredArgsConstructor
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup>{
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<OrderGroupApiResponse> delete(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.delete(id);
    }

//    @GetMapping("")
//    public Header<List<OrderGroupApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        return orderGroupApiLogicService.search(pageable);
//    }
}
