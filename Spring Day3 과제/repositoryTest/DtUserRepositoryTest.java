package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.AdminUser;
import com.koreait.day2.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class DtUserRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private DtUserRepository dtUserRepository;

    @Test
    public void create(){
//        DtUser dtUser = new DtUser();
//        dtUser.setUserid("banana");
//        dtUser.setUserpw("1234");
//        dtUser.setHp("010-2222-2222");
//        dtUser.setEmail("banana@bananan.com");
//        dtUser.setRegDate(LocalDateTime.now());
//
//        DtUser newDtUser = dtUserRepository.save(dtUser);

        DtUser dtUser = DtUser.builder()
                .userid("melon")
                .userpw("1212")
                .hp("010-3333-3333")
                .email("orange@orange.com")
                .regDate(LocalDateTime.now())
                .build();
        DtUser newDtUser = dtUserRepository.save(dtUser);
    }


    @Test
    public void read(){
        // select * from users where userid=?
        Optional<DtUser> user = dtUserRepository.findByUserid("banana");
        user.ifPresent(selectUser -> {
            System.out.println("users : " + selectUser);
            System.out.println("userid : " + selectUser.getUserid());
            System.out.println("userpw : " + selectUser.getUserpw());
            System.out.println("hp : " + selectUser.getHp());
            System.out.println("email : " + selectUser.getEmail());
        });
    }

//    @Test
//    public void read(){
//        DtUser dtUser = dtUserRepository.findFirstByHpOrderByIdDesc("010-2222-2222");
//        if(dtUser != null){
//            System.out.println("데이터가 존재합니다!");
//        }else{
//            System.out.println("데이터가 존재하지 않습니다!");
//        }
//    }

    @Test
    public void update(){
        Optional<DtUser> dtUser = dtUserRepository.findByUserid("banana");
        // selectUser 이렇게 이름은 알아서 짓는거
        dtUser.ifPresent(selectUser ->{
            selectUser.setEmail("banana@naver.com");
            selectUser.setHp("010-0000-0000");
            selectUser.setUpdateDate(LocalDateTime.now());
            dtUserRepository.save(selectUser);
        });
    }


    @Test
    public void delete(){
        Optional<DtUser> dtUser = dtUserRepository.findByUserid("banana"); // 이걸로 찾아서

        // 삭제가 되었으므로
        dtUser.ifPresent(selectUser ->{
            dtUserRepository.delete(selectUser);
        });

        // 다시한번 검색을 해줌
        Optional<DtUser> deleteUser = dtUserRepository.findByUserid("banana");
        // 혹시 데이터가 있니?있으면 true 없으면 false
        if(deleteUser.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
