package model;

import com.google.gson.annotations.SerializedName;

public class AuthToken {
    // todo: serialized поля, согласно swagger, а также геттеры и сеттеры
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("token")
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    //getPassword

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
