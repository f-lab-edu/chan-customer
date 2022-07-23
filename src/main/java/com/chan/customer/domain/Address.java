package com.chan.customer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Address {

    //도로명 주소
    @NotNull
    private String doroAddress;

    //한국 행정 구역 코드
    @NotNull
    private int sigunguCode;

}
