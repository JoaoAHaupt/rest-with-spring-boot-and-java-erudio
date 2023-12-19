package br.com.erudio.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String acessToken;
    private String refreshToken;

    public TokenVO(){}
    public TokenVO(String username, Boolean authenticated, Date created, Date expiration, String acessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.acessToken = acessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVO tokenVO)) return false;
        return Objects.equals(username, tokenVO.username) && Objects.equals(authenticated, tokenVO.authenticated) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(acessToken, tokenVO.acessToken) && Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authenticated, created, expiration, acessToken, refreshToken);
    }
}
