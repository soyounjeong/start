package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Category;
import com.koreait.day2.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = Item.builder()
                .name("엘지 비스포크")
                .status("판매중")
                .title("양문형 냉장고")
                .content("아주 시원해요")
                .price(BigDecimal.valueOf(2000000))
                .regDate(LocalDateTime.now())
                .partnerId(4L)
                .build();
        Item newItem = itemRepository.save(item);
    }

    @Test
    public void read(){
        Optional<Item> item = itemRepository.findByName("아반떼");

        item.ifPresent(selectItem ->{
            System.out.println("***********************************");
            System.out.println("name : " + selectItem.getName());
            System.out.println("status : " + selectItem.getStatus());
            System.out.println("title : " + selectItem.getTitle());
            System.out.println("price : " + selectItem.getPrice());
            System.out.println("regdate : " + selectItem.getRegDate());
            System.out.println("***********************************");
        });
    }

    @Test
    public void update(){
        Optional<Item> item = itemRepository.findByName("아반떼");

        item.ifPresent(updateItem ->{
            updateItem.setName("벤츠 S클래스");
            updateItem.setTitle("벤츠 S클래스 후기");
            updateItem.setContent("비싸고 아주 좋아요");
            itemRepository.save(updateItem);
        } );
    }

    @Test
    public void delete(){
        Optional<Item> item = itemRepository.findByName("벤츠 S클래스");

        item.ifPresent(deleteItem ->{
            itemRepository.delete(deleteItem);
        });

        Optional<Item> deleteItem = itemRepository.findByName("벤츠 S클래스");
        if(deleteItem.isPresent()){
            System.out.println("삭제실패!");
            System.out.println("다시 시도하세요.");
        }else {
            System.out.println("삭제 성공!");
        }
    }
}
