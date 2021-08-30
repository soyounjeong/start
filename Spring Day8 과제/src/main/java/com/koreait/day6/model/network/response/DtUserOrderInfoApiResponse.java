package com.koreait.day6.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtUserOrderInfoApiResponse {
    private DtUserApiResponse userApiResponse;
}
