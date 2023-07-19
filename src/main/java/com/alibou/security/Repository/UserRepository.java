package com.alibou.security.Repository;

import java.util.List;
import java.util.Optional;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT new com.alibou.security.DTO.UserDTO" +
          "(u.id, u.firstname, u.lastname" +
          ", u.username, u.role) " +
          "FROM User u")
  List<UserDTO> findAllUsersAsDTO();

  Optional<User> findByUsername(String username);



}
