package az.mycompany.lecturemanagement.service;

import az.mycompany.lecturemanagement.dto.request.LectureDto;
import az.mycompany.lecturemanagement.dto.request.UserDto;
import az.mycompany.lecturemanagement.dto.response.PageInfoWithContent;
import az.mycompany.lecturemanagement.entity.Lecture;
import az.mycompany.lecturemanagement.exception.ResourceNotFoundException;
import az.mycompany.lecturemanagement.repository.LectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final UserService userService;

    public LectureService(LectureRepository lectureRepository, UserService userService) {
        this.lectureRepository = lectureRepository;
        this.userService = userService;
    }

    @Transactional
    public LectureDto create(Lecture lecture) {
        return entityToDto(lectureRepository.save(lecture));
    }

    @Transactional
    public void deleteById(int id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Lecture not found with id: " + id);
        }
        lectureRepository.deleteById(id);
    }

    public List<LectureDto> findAll() {
        return lectureRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public PageInfoWithContent<LectureDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<LectureDto> lectureDtoPage = lectureRepository.findAll(pageable).map(this::entityToDto);
        return new PageInfoWithContent<>(lectureDtoPage);
    }

    public LectureDto findById(int id) {
        return entityToDto(lectureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lecture not found with id: " + id)));
    }

    public boolean existsById(int id) {
        return lectureRepository.existsById(id);
    }

    public LectureDto entityToDto(Lecture lecture) {
        LectureDto lectureDto = new LectureDto();
        lectureDto.setId(lecture.getId());
        lectureDto.setName(lecture.getName());
        lectureDto.setStudents(lecture.getStudents().stream().map(userService::entityToDto).collect(Collectors.toList()));
        lectureDto.setTeacher(userService.entityToDto(lecture.getTeacher()));
        return lectureDto;
    }

}
