package vn.quankundeptrai.banana.data.models.other;

/**
 * Created by TQN on 1/30/18.
 */

public class User {
    private String id;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String token;
    private String password;

    public User(String id, String token, String nickname, String email, String password, String phone, String address) {
        this.id = id;

        this.token = token;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
