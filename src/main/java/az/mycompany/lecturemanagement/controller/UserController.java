package az.mycompany.lecturemanagement.controller;

import az.mycompany.lecturemanagement.dto.request.UserDto;
import az.mycompany.lecturemanagement.dto.response.ApiResponse;
import az.mycompany.lecturemanagement.dto.response.PageInfoWithContent;
import az.mycompany.lecturemanagement.entity.User;
import az.mycompany.lecturemanagement.enums.Role;
import az.mycompany.lecturemanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> findAll() {
        List<UserDto> userDtos = userService.findAll();
        ApiResponse<List<UserDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "User retrieved successfully",
                userDtos
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/pageable")
    public ResponseEntity<ApiResponse<PageInfoWithContent<UserDto>>> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {

        PageInfoWithContent<UserDto> pageInfoWithContent = userService.findAll(page, size);
        ApiResponse<PageInfoWithContent<UserDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Page info with content retrieved successfully",
                pageInfoWithContent
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable int userId) {
        UserDto userDto = userService.findById(userId);
        ApiResponse<UserDto> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "User retrieved successfully",
                userDto
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@RequestBody User user) {
        UserDto createdUser = userService.create(user);
        ApiResponse<UserDto> response = new ApiResponse<>(
                "success",
                HttpStatus.CREATED.value(),
                "User created successfully",
                createdUser
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/by-role")
    public ResponseEntity<ApiResponse<List<UserDto>>> getByRole(@RequestParam Role role) {

        List<UserDto> userDtos = userService.findUsersByRole(role);

        ApiResponse<List<UserDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Users retrieved successfully",
                userDtos
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/potential-students")
    public ResponseEntity<ApiResponse<List<UserDto>>> getPotentialStudents(@RequestBody List<Integer> studentIds) {
        List<UserDto> userDtos = userService.findPotentialUsers(studentIds);
        ApiResponse<List<UserDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "User retrieved successfully",
                userDtos
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
