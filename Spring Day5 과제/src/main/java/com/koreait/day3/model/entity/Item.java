package com.koreait.day3.model.entity;

import com.koreait.day3.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;





@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)

@SequenceGenerator(
        name = "seq_item", // 이건 자바에서 쓰는 시퀀스 이름 알아서 지어도 됨
        sequenceName = "seq_item", // 이 이름으로 id에 붙여주면 됨 - 이건 오라클의 시퀀스 이름이여야함!!!(둘이 동일해야함)
        initialValue = 1, // 초기값
        allocationSize = 1 // 사이즈 1씩 증가
)


public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_item" )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    private String title;
    private String content;
    private BigDecimal price;
    @CreatedDate
    private LocalDateTime regDate;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @LastModifiedBy
    private String updateBy;

    //    private Long partnerId;
    @ManyToOne
    private Partner partner;

}
