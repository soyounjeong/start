package com.koreait.day6.service;

import com.koreait.day6.ifs.CrudInterface;
import com.koreait.day6.model.entity.Partner;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.Pagination;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.DtUserApiRequest;
import com.koreait.day6.model.network.request.PartnerApiRequest;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.DtUserApiResponse;
import com.koreait.day6.model.network.response.PartnerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        Partner partner = Partner.builder()
                .id(partnerApiRequest.getId())
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .regDate(partnerApiRequest.getRegDate())
                .build();
        Partner newPartner = baseRepository.save(partner);
        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Optional<Partner> optional = baseRepository.findById(partnerApiRequest.getId());
        return optional.map(partner -> {
            partner.setId(partnerApiRequest.getId());
            partner.setName(partner.getName());
            partner.setStatus(partner.getStatus());
            partner.setAddress(partner.getAddress());
            partner.setCallCenter(partner.getCallCenter());
            partner.setBusinessNumber(partner.getBusinessNumber());
            partner.setCeoName(partner.getCeoName());
            partner.setRegDate(partner.getRegDate());

            return partner;
        }).map(partner -> baseRepository.save(partner))
                .map(partner -> response(partner))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = baseRepository.findById(id);

        return optional.map(partner -> {
            baseRepository.delete(partner);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .regDate(partner.getRegDate())
                .build();
        return partnerApiResponse;
    }

    public Header<List<PartnerApiResponse>> search(Pageable pageable){
        Page<Partner> partner = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partner.stream()
                .map(partner1 -> response(partner1))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(partner.getTotalPages())
                .totalElements(partner.getTotalElements())
                .currentPage(partner.getNumber())
                .currentElements(partner.getNumberOfElements())
                .build();
        return Header.OK(partnerApiResponseList, pagination);
    }
}
