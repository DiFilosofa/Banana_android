package vn.quankundeptrai.banana.data.models.other;

import vn.quankundeptrai.banana.enums.Level;

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
    private String avatar;
    private Level level;
    private int pointSum;

    public User(String id, String token, String nickname, String email, String password, String phone, String address) {
        this.id = id;

        this.token = token;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(String nickname, String email, String phone, String address, String avatar, Level level, int pointSum) {
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.level = level;
        this.pointSum = pointSum;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Level getLevel() {
        return level;
    }

    public int getPointSum() {
        return pointSum;
    }

    public void update(String name, String email, String phone, String address, String avatar, Level level, int point){
        this.nickname = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.level = level;
        this.pointSum = point;
    }
}
