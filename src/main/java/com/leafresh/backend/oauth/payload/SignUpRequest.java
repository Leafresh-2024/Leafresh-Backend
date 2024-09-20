package com.leafresh.backend.oauth.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    @Size(max = 6, message = "이름은 최대 6자까지 입력 가능합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Size(max = 40, message = "이메일은 최대 40자까지 입력 가능합니다.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하로 입력해야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 사항입니다.")
    @Size(max = 15, message = "닉네임은 최대 15자까지 입력 가능합니다.")
    private String nickname;

    @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 10~11자리 숫자로 입력해야 합니다.")
    private String phoneNumber;

    private String imageUrl;

    private boolean isTermsAgreement;

    // Getter 메서드
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isTermsAgreement() {
        return isTermsAgreement;
    }

    // Setter 메서드

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTermsAgreement(boolean termsAgreement) {
        isTermsAgreement = termsAgreement;
    }
}
