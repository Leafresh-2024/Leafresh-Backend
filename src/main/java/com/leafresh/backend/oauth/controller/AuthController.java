
package com.leafresh.backend.oauth.controller;

import com.leafresh.backend.common.exception.BadRequestException;
import com.leafresh.backend.oauth.payload.ApiResponse;
import com.leafresh.backend.oauth.payload.AuthResponse;
import com.leafresh.backend.oauth.payload.LoginRequest;
import com.leafresh.backend.oauth.payload.SignUpRequest;
import com.leafresh.backend.oauth.repository.UserRepository;
import com.leafresh.backend.oauth.security.UserPrincipal;
import com.leafresh.backend.oauth.service.CustomUserDetailsService;
import com.leafresh.backend.oauth.service.TokenProvider;
import com.leafresh.backend.oauth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;
    private CustomUserDetailsService customUserDetailsService;
    private UserService userService; // sv메서드 수정 이후에 제거필요 오류방지로 일단 둠**

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(((UserPrincipal) authentication.getPrincipal()).getUserId());

        return ResponseEntity.ok(new AuthResponse(accessToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (signUpRequest == null) {
            throw new BadRequestException("가입 정보가 없습니다.");
        }
        if (!signUpRequest.isTermsAgreement()) {
            throw new BadRequestException("약관에 동의해야 합니다.");
        }

        ResponseEntity<?> result = customUserDetailsService.registerUser(signUpRequest);

        if (result != null){ // 응답값이 있으면 (회원가입성공/이메일중복/닉넴중복 등)
            return result;
        } else {
            throw new BadRequestException("가입에 실패했습니다. 다시 시도해주세요");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new ApiResponse(true, "사용자가 성공적으로 로그아웃되었습니다."));
    }

    @GetMapping("/checkNickname") // 닉네임 중복확인
    public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname) {
        Boolean isNicknameDuplicate = userService.existsByUserNickname(nickname);

        // Response에 exists 필드로 중복 여부 반환
        return ResponseEntity.ok(Collections.singletonMap("exists", isNicknameDuplicate));
    }

    @PostMapping("/check-token")
    public ResponseEntity<?> validateOrRefreshToken(@RequestHeader("Authorization") String tokenHeader,
                                                    @RequestBody(required = false) Map<String, String> request) {
        String token = tokenHeader.replace("Bearer ", "");

        if (tokenProvider.validateToken(token)) {
            return ResponseEntity.ok().build();
        } else {
            if (request != null && request.containsKey("refreshToken")) {
                String refreshToken = request.get("refreshToken");

                if (tokenProvider.validateRefreshToken(refreshToken)) {
                    try {
                        String newAccessToken = tokenProvider.refreshAccessToken(refreshToken);
                        return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new ApiResponse(false, "유효하지 않은 리프레시 토큰입니다."));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new ApiResponse(false, "리프레시 토큰이 만료되었습니다. 다시 로그인해주세요."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse(false, "액세스 토큰이 만료되었고, 리프레시 토큰이 제공되지 않았습니다."));
            }
        }
    }
}
