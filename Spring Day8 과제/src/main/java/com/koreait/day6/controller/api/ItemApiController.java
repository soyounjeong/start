package com.koreait.day6.controller.api;

import com.koreait.day6.controller.CrudController;
import com.koreait.day6.ifs.CrudInterface;
import com.koreait.day6.model.entity.Item;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.ItemApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.ItemApiResponse;
import com.koreait.day6.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {
    private final ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody  Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable(name="id") Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update( @RequestBody Header<ItemApiRequest> request) {

        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<ItemApiResponse> delete(@PathVariable(name="id") Long id) {

        return itemApiLogicService.delete(id);
    }

    @GetMapping("")
    public Header<List<ItemApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC)Pageable pageable){
        return itemApiLogicService.search(pageable);
    }
}
