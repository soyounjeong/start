package com.koreait.day6.model.network.response;


import com.koreait.day6.model.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {
    private Long id;
    private String type;
    private String title;
    private LocalDateTime regDate;
    private Date updateDate;
    private String updateBy;

    private List<Partner> PartnerList;
}
