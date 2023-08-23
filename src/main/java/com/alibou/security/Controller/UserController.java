package com.alibou.security.Controller;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Service.AuthenticationService;
import com.alibou.security.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService,AuthenticationService authenticationService){
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/Details")
    public ResponseEntity<Optional<UserDTO>> getUserByUsername(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/ChangePassword")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(@RequestParam String currentPassword, @RequestParam String newPassword) {
        userService.changeCurrentUserPassword(currentPassword, newPassword);

        return ResponseEntity.ok("Password changed successfully.");
    }



}
