package com.koreait.day6.controller.api;

import com.koreait.day6.controller.CrudController;
import com.koreait.day6.model.entity.DtUser;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.DtUserOrderInfoApiResponse;
import com.koreait.day6.service.DtUserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class DtUserApiController extends CrudController<DtUserApiRequest, DtUserApiResponse, DtUser> {

    private final DtUserApiLogicService dtUserApiLogicService;

    /*
        {
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "userid":"cherry",
                "userpw":"1234",
                "email":"cherry@cherry.com",
                "hp":"010-1234-1234"
            }
        }
     */
    @Override
    @PostMapping("")    // http://127.0.0.1:9090/api/user (post)
    public Header<DtUserApiResponse> create(@RequestBody Header<DtUserApiRequest> request) {
        System.out.println(request);
        return dtUserApiLogicService.create(request);
    }


    @Override
    @GetMapping("{id}") // /api/user/{id} (get) // http://127.0.0.1:9090/api/user/42
    public Header<DtUserApiResponse> read(@PathVariable(name="id") Long id) {
        return dtUserApiLogicService.read(id);
    }

    /*
         {
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "id":42,
                "userid":"cherry",
                "userpw":"1010",
                "email":"cherry@naver.com",
                "hp":"010-0000-0000"
            }
        }
     */
    @Override
    @PutMapping("") // /api/user (put)
    public Header<DtUserApiResponse> update(@RequestBody Header<DtUserApiRequest> request) {
        return dtUserApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id} // http://127.0.0.1:9090/api/user/42
    public Header<DtUserApiResponse> delete(@PathVariable(name="id") Long id) {
        return dtUserApiLogicService.delete(id);
    }

    // 사용자가 무엇을 구입했는지 알 수 있는 api로 구현
    // 사용자에 대한 쇼핑정보
//    @GetMapping("/{id}/orderInfo") // /api/user/1(아이디번호)/orderInfo
//    public Header<DtUserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
//        return dtUserApiLogicService.orderInfo(id);
//    }

    // 호출할때 어떻게 호출하냐면 /api/user 로 호출하면 됨
    @GetMapping("")
    public Header<List<DtUserApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return dtUserApiLogicService.search(pageable);
    }

}
