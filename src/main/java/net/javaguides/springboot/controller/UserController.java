package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Tag(
        name = "Crud Rest APIs for User Response",
        description = "Create User, Update User, Get User, Get All Users and Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    // build create User REST API

    @Operation(
            summary = "Create User Rest API",
            description = "Create User it save user to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    @Operation(
            summary = "Get User Rest API by ID",
            description = "Get User by ID from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    // build get all users by REST API
    @Operation(
            summary = "Get All Users Rest API",
            description = "Get ALL User by the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userList = userService.getUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
    // Build updated user by REST API
    @Operation(
            summary = "Update User by ID Rest API",
            description = "Update User with ID it save user to database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 "
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @Valid @RequestBody UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);

        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // Build delete user by REST API
    @Operation(
            summary = "Delete User by ID Rest API",
            description = "Delete User from database by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
//            ResourceNotFoundException resourceNotFoundException,
//            WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                resourceNotFoundException.getMessage(),
//                webRequest.getDescription(false),
//                "User Not Found"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }
}
