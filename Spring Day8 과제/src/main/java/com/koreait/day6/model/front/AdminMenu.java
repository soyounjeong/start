package com.koreait.day6.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 프론트 메뉴에서만 쓸 패키지
public class AdminMenu {
    private String title;
    private String url;
    private String code;
    private String img;
}
