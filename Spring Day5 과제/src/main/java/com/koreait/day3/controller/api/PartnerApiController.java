package com.koreait.day3.controller.api;


import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.Partner;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.PartnerApiRequest;
import com.koreait.day3.model.network.response.PartnerApiResponse;
import com.koreait.day3.model.repository.PartnerRepository;
import com.koreait.day3.model.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Partner")
@RequiredArgsConstructor
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
    private final PartnerApiLogicService partnerApiLogicService;

    //    {
//        "transaction_time" : "2021-08-23",
//        "resultCode" : "OK",
//        "description" : "OK",
//        "data" : {
//        "id" : "5",
//        "name" : "엘지전자",
//        "address" : "경기도 평택시",
//        "callCenter" : "031-3333-3333",
//        "businessNumber" : "555-55-55555",
//        "ceoName" : "김아무개,
//        "status" : "판매중지"
//    }
//    }

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable(name="id") Long id) {
        return partnerApiLogicService.read(id);
    }

    //    {
//        "transaction_time" : "2021-08-23",
//        "resultCode" : "OK",
//        "description" : "OK",
//        "data" : {
//        "id" : "5",
//        "name" : "엘지디스플레이",
//        "address" : "경기도 평택시",
//        "callCenter" : "031-3383-9999",
//        "businessNumber" : "555-55-55555",
//        "ceoName" : "김아무개,
//        "status" : "판매시작"
//    }
//    }

    @Override
    @PutMapping // http://127.0.0.1:9090/api/Partner
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/Partner/6
    public Header<PartnerApiResponse> delete(@PathVariable(name = "id") Long id) {
        return partnerApiLogicService.delete(id);
    }
}
