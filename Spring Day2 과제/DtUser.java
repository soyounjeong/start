package com.koreait.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@SequenceGenerator(
        name="seq_dt_user",
        sequenceName = "seq_dt_user",
        initialValue = 1,
        allocationSize = 1
)
@Builder

public class DtUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dt_user")
    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate; // 회원정보 시간 
}
