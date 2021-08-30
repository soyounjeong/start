package com.koreait.day6.model.network.response;

import com.koreait.day6.model.enumclass.DtUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtUserApiResponse {

    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private DtUserStatus status;

    private List<OrderGroupApiResponse> orderGroupApiResponseList; // Getter& setter 가능

}
