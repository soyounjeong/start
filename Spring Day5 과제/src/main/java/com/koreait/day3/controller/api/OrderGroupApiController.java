package com.koreait.day3.controller.api;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.ItemApiRequest;
import com.koreait.day3.model.network.request.OrderGroupApiRequest;
import com.koreait.day3.model.network.response.ItemApiResponse;
import com.koreait.day3.model.network.response.OrderGroupApiResponse;
import com.koreait.day3.model.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordergroup")
@RequiredArgsConstructor
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<OrderGroupApiResponse> delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
