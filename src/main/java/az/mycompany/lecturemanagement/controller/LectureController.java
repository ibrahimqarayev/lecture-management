package az.mycompany.lecturemanagement.controller;

import az.mycompany.lecturemanagement.dto.request.LectureDto;
import az.mycompany.lecturemanagement.dto.request.UserDto;
import az.mycompany.lecturemanagement.dto.response.ApiResponse;
import az.mycompany.lecturemanagement.dto.response.PageInfoWithContent;
import az.mycompany.lecturemanagement.entity.Lecture;
import az.mycompany.lecturemanagement.entity.User;
import az.mycompany.lecturemanagement.service.LectureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lectures")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<LectureDto>>> getAll() {
        List<LectureDto> lectureDtos = lectureService.findAll();
        ApiResponse<List<LectureDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Lectures retrieved successfully",
                lectureDtos
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/pageable")
    public ResponseEntity<ApiResponse<PageInfoWithContent<LectureDto>>> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {

        PageInfoWithContent<LectureDto> pageInfoWithContent = lectureService.findAll(page, size);
        ApiResponse<PageInfoWithContent<LectureDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Page info with content retrieved successfully",
                pageInfoWithContent
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<ApiResponse<LectureDto>> getById(@PathVariable int lectureId) {
        LectureDto lectureDto = lectureService.findById(lectureId);
        ApiResponse<LectureDto> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Lecture retrieved successfully",
                lectureDto
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LectureDto>> create(@RequestBody Lecture lecture) {
        LectureDto createdLecture = lectureService.create(lecture);
        ApiResponse<LectureDto> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Lecture created successfully",
                createdLecture
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
