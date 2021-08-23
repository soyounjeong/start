package com.koreait.day3.model.network;


import com.koreait.day3.model.network.request.OrderGroupApiRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T>{
    private LocalDateTime transactionTime;
    private String resultCode;
    private String description;
    private T data;

    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK") // 내가 만들어서 보내는 코드
                .description("OK")
                .data(data) // select한 결과 쫙 리턴
                .build();
    }

    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR") // 내가 만들어서 보내는 코드
                .description("ERROR")
                .build();
    }

}
