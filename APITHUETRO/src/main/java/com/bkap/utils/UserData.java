package com.bkap.utils;

import com.bkap.entities.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String code;
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
