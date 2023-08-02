package com.alibou.security.Repository;

import java.util.List;
import java.util.Optional;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT new com.alibou.security.DTO.UserDTO" +
          "(u.id, u.firstname, u.lastname" +
          ", u.username, u.role) " +
          "FROM User u")
  List<UserDTO> findAllUsersAsDTO();
  @Query("SELECT new com.alibou.security.DTO.UserDTO" +
          "(u.id, u.firstname, u.lastname" +
          ", u.username, u.role) " +
          "FROM User u WHERE u.id = :userId")
  UserDTO getUserAsDTObyId(@Param("userId") Integer userId);
  @Query("SELECT u FROM User u INNER JOIN u.computers c WHERE u.id = :userId")
  List<UserDTO> getComputerOwner(@Param("userId") Integer id);
  @Modifying
  @Query("UPDATE Computer c SET c.user = :user WHERE c.id = :computerId")
  void setComputerOwner(@Param("user") User user, @Param("computerId") Integer computerId);
  Optional<User> findByUsername(String username);



}
