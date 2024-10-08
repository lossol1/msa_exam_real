package com.sparta.msa_exam.client.auth;

import com.sparta.msa_exam.client.auth.core.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
     *
     * @param userId 사용자 ID
     * @return JWT 액세스 토큰을 포함한 AuthResponse 객체를 반환합니다.
     */
    @PostMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest signInRequest){
        String token = authService.signIn(signInRequest.getUserId(), signInRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
     *
     * @param user_id 사용자 ID
     * @return JWT 액세스 토큰을 포함한 AuthResponse 객체를 반환합니다.
     */
    @GetMapping("/auth/signIn")
    public ResponseEntity<?> getCreateAuthenticationToken(@RequestParam String user_id){
        return ResponseEntity.ok(new AuthResponse(authService.getCreateAccessToken(user_id)));
    }

    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User createdUser = authService.signUp(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * JWT 액세스 토큰을 포함하는 응답 객체입니다.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SignInRequest {
        private String userId;
        private String password;
    }
}