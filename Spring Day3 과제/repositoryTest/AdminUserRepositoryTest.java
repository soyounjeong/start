package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.AdminUser;
import com.koreait.day2.model.entity.DtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = AdminUser.builder()
                .userid("admin")
                .userpw("1234")
                .name("관리자")
                .status("use")
                .regDate(LocalDateTime.now())
                .build();
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
    }

    @Test
    public void read(){
        // select * from users where userid=?
        Optional<AdminUser> adminUser = adminUserRepository.findByUserid("admin");
        adminUser.ifPresent(selectAdminUser -> {
            System.out.println("userid : " + selectAdminUser.getUserid());
            System.out.println("userpw : " + selectAdminUser.getUserpw());
            System.out.println("username :" + selectAdminUser.getName());
            System.out.println("userstatus : " + selectAdminUser.getStatus());
            System.out.println("regdate : " + selectAdminUser.getRegDate());
        });
    }

    @Test
    public void update(){
        Optional<AdminUser> adminUser = adminUserRepository.findByUserid("apple");
        // selectUser 이렇게 이름은 알아서 짓는거
        adminUser.ifPresent(selectAdminUser ->{
            selectAdminUser.setUserpw("1313");
            selectAdminUser.setName("김사과");
            selectAdminUser.setRegDate(LocalDateTime.now());
            adminUserRepository.save(selectAdminUser);
        });
    }

    @Test
    public void delete(){
        Optional<AdminUser> adminUser = adminUserRepository.findByUserid("apple"); // 이걸로 찾아서

        // 삭제가 되었으므로
        adminUser.ifPresent(selectUser ->{
            adminUserRepository.delete(selectUser);
        });

        // 다시한번 검색을 해줌
        Optional<AdminUser> deleteUser = adminUserRepository.findByUserid("apple");
        // 혹시 데이터가 있니?있으면 true 없으면 false
        if(deleteUser.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
