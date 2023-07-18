package com.alibou.security.Controller;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import com.alibou.security.Service.AuthenticationService;
import com.alibou.security.Service.UserService;
import com.alibou.security.auth.AuthenticationResponse;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final AuthenticationService service;

    public AdminController(UserService userService, AuthenticationService service) {
        this.userService = userService;
        this.service = service;
    }

    @GetMapping("/U")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<Map<String, String>> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(userDetails.getUsername(), userDetails.getAuthorities().toString()));
    }

    @GetMapping("/Users")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
