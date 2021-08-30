package com.koreait.day6.controller.api;

import com.koreait.day6.controller.CrudController;
import com.koreait.day6.ifs.CrudInterface;
import com.koreait.day6.model.entity.Partner;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.PartnerApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.PartnerApiResponse;
import com.koreait.day6.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
    private final PartnerApiLogicService partnerApiLogicService;


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

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody  Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<PartnerApiResponse> delete(@PathVariable(name="id") Long id) {
        return partnerApiLogicService.delete(id);
    }
    @GetMapping
    public Header<List<PartnerApiResponse>> findAll(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return partnerApiLogicService.search(pageable);
    }
}
