package com.stock.stock.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthToken {

    private String access_token;
    private String  token_type;
    private String expires_in;
    private String  scope;
    private String user_id;
    private String refresh_token;

}
