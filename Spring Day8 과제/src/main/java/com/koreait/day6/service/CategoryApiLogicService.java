package com.koreait.day6.service;

import com.koreait.day6.model.entity.Category;
import com.koreait.day6.model.network.Header;
import com.koreait.day6.model.network.Pagination;
import com.koreait.day6.model.network.request.CategoryApiRequest;
import com.koreait.day6.model.network.response.CategoryApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service // 서비스레이어, 내부에서 자바로직을 처리함
@RequiredArgsConstructor
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {
    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest categoryApiRequest = request.getData();

        Category category = Category.builder()
                .id(categoryApiRequest.getId())
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .updateBy(categoryApiRequest.getUpdateBy())
                .build();
        Category newCategory = baseRepository.save(category);
        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Optional<Category> optional = baseRepository.findById(categoryApiRequest.getId());
        return optional.map(category -> {
            category.setId(categoryApiRequest.getId());
            category.setType(categoryApiRequest.getType());
            category.setTitle(categoryApiRequest.getTitle());
            category.setUpdateBy(categoryApiRequest.getUpdateBy());

            return category;
        }).map(category -> baseRepository.save(category))
                .map(category -> response(category))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = baseRepository.findById(id);

        return optional.map(category -> {
            baseRepository.delete(category);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private CategoryApiResponse response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .regDate(category.getRegDate())
                .updateBy(category.getUpdateBy())
                .build();
        return categoryApiResponse;
    }


    public Header<List<CategoryApiResponse>> search(Pageable pageable){
        Page<Category> category = baseRepository.findAll(pageable);
        List<CategoryApiResponse> categoryApiResponseList = category.stream()
                .map(category1 -> response(category1))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(category.getTotalPages())
                .totalElements(category.getTotalElements())
                .currentPage(category.getNumber())
                .currentElements(category.getNumberOfElements())
                .build();
        return Header.OK(categoryApiResponseList, pagination);
    }
}
