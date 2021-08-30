package com.koreait.day6.model.entity;

import com.koreait.day6.model.enumclass.DtUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
@EntityListeners(AuditingEntityListener.class)
public class DtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dt_user")
    private Long id;

    private String userid;
    private String userpw;
    private String hp;
    private String email;
    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private DtUserStatus status;  // REGISTERED, UNREGISTERED

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<OrderGroup> orderGroupList;
}

