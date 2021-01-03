package com.example.demo.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service("TokenService")
public class TokenService {


    /**
     * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
     * withAudience()存入需要保存在token的信息，这里把用户ID存入token中
     * @param UserID
     * @param password
     * @return
     */
    public String getToken(Integer UserID,String password) {
        String token="";
        token= JWT.create().withAudience(Integer.toString(UserID))// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(password));// 以 password 作为 token 的密钥
        return token;
    }

}
