package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.AdminUser;
import com.koreait.day2.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class DtUserRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private DtUserRepository dtUserRepository;

    @Test
    public void create(){
        DtUser dtUser = DtUser.builder()
                .userid("banana")
                .userpw("1234")
                .hp("010-2222-2222")
                .email("banana@banana.com")
                .regDate(LocalDateTime.now())
                .build();
        DtUser newDtUser = dtUserRepository.save(dtUser);
    }
}
