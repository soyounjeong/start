package com.koreait.day3.controller.api;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.ItemApiRequest;
import com.koreait.day3.model.network.response.ItemApiResponse;
import com.koreait.day3.model.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService;

//    {
//        "transaction_time" : "2021-08-23",
//        "resultCode" : "OK",
//        "description" : "OK",
//        "data" : {
//            "userid" : "4",
//            "name" : "삼성 노트북9",
//            "title" : "삼성노트북 15인치",
//             "content" : "무겁고 짱 단단해요",
//              "price" : "1500000",
//                "regdate" : "2021-08-23"
//    }
//    }
    @Override
    @PostMapping // http://127.0.0.1:9090/api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

//    {
//        "transaction_time" : "2021-08-23",
//        "resultCode" : "OK",
//        "description" : "OK",
//        "data" : {
//        "id" : "6",
//        "name" : "삼성 노트북 S펜",
//        "title" : "삼성노트북 신상",
//        "content" : "가볍고 짱 단단해요",
//        "price" : "1800000",
//        "regdate" : "2021-08-23"
//    }
//    }

    @Override
    @PutMapping
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<ItemApiResponse> delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
