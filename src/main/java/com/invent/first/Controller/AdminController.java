package com.invent.first.Controller;

import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.User;
import com.invent.first.Service.AuthenticationService;
import com.invent.first.Service.UserService;
import com.invent.first.response.AuthenticationResponse;
import com.invent.first.request.RegisterRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/Details")
    public ResponseEntity<Optional<UserDTO>> getUserByUsername(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<UserDTO> user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/Users")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/changePassword/{userId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> changePassword(@PathVariable Integer userId) {
        String newPassword = "P@ssw0rd";
        userService.updatePassword(userId, newPassword);
        return ResponseEntity.ok("Password updated successfully.");
    }

    @DeleteMapping("/Delete/{userId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        User deletedUser = userService.deleteById(userId);
        if (deletedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
