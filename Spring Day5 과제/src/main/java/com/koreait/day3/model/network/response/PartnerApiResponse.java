package com.koreait.day3.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {
    private Long id;
    private String name;
    private String status; // 상태값
    private String address; /// 주소 저장
    private String callCenter; // 콜센터
    private String businessNumber;
    private String ceoName;
    private LocalDateTime regDate; // 등록 날짜
}
