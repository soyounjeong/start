package com.koreait.day6.service;

import com.koreait.day6.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {
    // adminMenu라는 파일에 리스트를 getAdminMenu를 리턴
    public List<AdminMenu> getAdminMenu(){
        // asList : 리스트 타입으로 리턴
        return Arrays.asList(
                AdminMenu.builder().title("HOME").url("/pages").img("home").code("").build(),
                AdminMenu.builder().title("고객관리").url("/pages/user").img("account_circle").code("users").build(),
                AdminMenu.builder().title("관리자 관리").url("/pages/adminuser").img("person").code("adminuser").build(),
                AdminMenu.builder().title("카테고리 관리").url("/pages/category").img("category").code("category").build(),
                AdminMenu.builder().title("아이템 관리").url("/pages/item").img("view_module").code("item").build(),
                AdminMenu.builder().title("구매 정보 관리").url("/pages/orderGroup").img("shopping_cart").code("orderGroup").build(),
                AdminMenu.builder().title("업체 관리").url("/pages/partner").img("supervisor_account").code("partner").build()
        );
    }
}
