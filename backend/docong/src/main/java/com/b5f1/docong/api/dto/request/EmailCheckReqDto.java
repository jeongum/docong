package com.b5f1.docong.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailCheckReqDto {
    @NotNull
    String email;
}