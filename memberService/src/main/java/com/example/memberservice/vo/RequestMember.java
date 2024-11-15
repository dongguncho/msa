package com.example.memberservice.vo;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestMember {
    @NotNull(message = "Email은 Null 일수 없습니다.")
    @Size(min = 2, message = "Email은 2글자 이상입니다.")
    @Email
    private String email;

    @NotNull(message = "name은 Null 일수 없습니다.")
    @Size(min = 2, message = "name은 2글자 이상입니다.")
    private String name;

    @NotNull(message = "pw은 Null 일수 없습니다.")
    @Size(min = 8, message = "name은 8글자 이상입니다.")
    private String pw;
}
