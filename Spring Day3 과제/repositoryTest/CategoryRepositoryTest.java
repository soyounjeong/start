package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // 자동차, 컴퓨터, 가전
    @Test
    public void create(){
        Category category = Category.builder()
                .type("가전")
                .title("삼성 TV")
                .regDate(LocalDateTime.now())
                .build();
        Category newCateory = categoryRepository.save(category);
    }

    @Test
    public void read(){
        Optional<Category> category = categoryRepository.findByTitle("현대자동차");

        category.ifPresent(selectCategory ->{
            System.out.println("type : " + selectCategory.getType());
            System.out.println("title : " + selectCategory.getTitle());
            System.out.println("regdate : " + selectCategory.getRegDate());
        });
    }

    @Test
    public void update(){
        Optional<Category> category = categoryRepository.findByTitle("애플컴퓨터");

        category.ifPresent(updateCategory ->{
            updateCategory.setTitle("아이맥");
            categoryRepository.save(updateCategory);
        } );
    }

    @Test
    public void delete(){
        Optional<Category> category = categoryRepository.findByTitle("삼성가전");

        category.ifPresent(deleteCategory ->{
            categoryRepository.delete(deleteCategory);
        });

        Optional<Category> deleteCategory = categoryRepository.findByTitle("삼성가전");
        if(deleteCategory.isPresent()){
            System.out.println("삭제실패!");
            System.out.println("다시 시도하세요.");
        }else {
            System.out.println("삭제 성공!");
        }
    }
}
