package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.DtUser;
import com.koreait.day2.model.entity.Item;
import com.koreait.day2.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = Partner.builder()
                .name("베스트샵")
                .status("사용중")
                .address("서울시 금천구")
                .callCenter("070-4444-4444")
                .businessNumber("444-44-444444")
                .ceoName("오지환")
                .regDate(LocalDateTime.now())
                .categoryId(5L)
                .build();
        Partner newPartner = partnerRepository.save(partner);
    }

    @Test
    public void read(){
        Optional<Partner> partner = partnerRepository.findByName("베스트샵");

        partner.ifPresent(selectPartner -> {
            System.out.println("***********************************");
            System.out.println("name : " + selectPartner.getName());
            System.out.println("status : " + selectPartner.getStatus());
            System.out.println("address :" + selectPartner. getAddress());
            System.out.println("callcenter : " + selectPartner.getCallCenter());
            System.out.println("ceoname : "  + selectPartner.getCeoName());
            System.out.println("reg_date : " + selectPartner.getRegDate());
            System.out.println("***********************************");
        });
    }

    @Test
    public void update(){
        Optional<Partner> partner = partnerRepository.findByName("현대 자동차");

        partner.ifPresent(updatePartner ->{
            updatePartner.setStatus("부품 부족");
            partnerRepository.save(updatePartner);
        });
    }

    @Test
    public void delete(){
        Optional<Partner> partner1 = partnerRepository.findById(Long.valueOf("4")); // 이걸로 찾아서

        // 삭제가 되었으므로
        partner1.ifPresent(selectPartner ->{
            partnerRepository.delete(selectPartner);
        });

        // 다시한번 검색을 해줌
        Optional<Partner> deletePartner = partnerRepository.findById(Long.valueOf("4"));
        // 혹시 데이터가 있니?있으면 true 없으면 false
        if(deletePartner.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
