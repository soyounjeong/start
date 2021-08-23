package com.koreait.day3.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)

@SequenceGenerator(
        name = "seq_partner", // 이건 자바에서 쓰는 시퀀스 이름 알아서 지어도 됨
        sequenceName = "seq_partner", // 이 이름으로 id에 붙여주면 됨 - 이건 오라클의 시퀀스 이름이여야함!!!(둘이 동일해야함)
        initialValue = 1, // 초기값
        allocationSize = 1 // 사이즈 1씩 증가
)


public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_partner")
    private Long id;
    private String name;
    private String status; // 상태값
    private String address; /// 주소 저장
    private String callCenter; // 콜센터
    private String businessNumber;
    private String ceoName;
    @CreatedDate
    private LocalDateTime regDate; // 등록 날짜
    private LocalDateTime updateDate; // 수정 날짜
//    private Long categoryId; // 카테고리 아이디


    // 아이템 리스트가 객체로 등록되어야함
    // LAZY로 많이 씀
    // 하나의 파트너에 여러개의 아이템 리스트 저장할 수 있음
    // 카테고리와 파트너, 아이템과 파트너
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Item> itemList;
}
