package com.example.memberservice.dto;
import lombok.Data;

import java.util.Date;

@Data
public class MemberDto {
    private String email;
    private String name;
    private String pw;
    private String userId;
    private Date createAt;

    private String encryptedPw;
}
