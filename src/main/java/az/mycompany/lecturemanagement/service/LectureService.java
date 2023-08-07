package az.mycompany.lecturemanagement.service;

import az.mycompany.lecturemanagement.entity.Lecture;
import az.mycompany.lecturemanagement.entity.User;
import az.mycompany.lecturemanagement.exception.DuplicateResourceException;
import az.mycompany.lecturemanagement.exception.ResourceNotFoundException;
import az.mycompany.lecturemanagement.repository.LectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    private final Logger logger = LoggerFactory.getLogger(LectureService.class);

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public Lecture create(Lecture lecture) {
        if (lecture.getId() == null) {
            logger.info("Saving lecture");
            return lectureRepository.save(lecture);
        }
        throw new DuplicateResourceException("Lecture already exist");
    }

    @Transactional
    public void deleteById(int id) {
        if (!existsById(id)) {
            String errorMessage = "Lecture not found with id: " + id;
            logger.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }
        logger.info("Lecture with id {} is successfully deleted.", id);
        lectureRepository.deleteById(id);
    }

    public List<Lecture> findAll() {
        List<Lecture> lectures = lectureRepository.findAll();
        int numberOfLectures = lectures.size();
        logger.info("Retrieved {} lectures from the database.", numberOfLectures);
        return lectures;
    }

    public Page<Lecture> findAll(Pageable pageable) {
        Page<Lecture> lecturePage = lectureRepository.findAll(pageable);
        long numberOfUsers = lecturePage.getTotalElements();
        logger.info("Retrieved {} lectures with paging from the database.", numberOfUsers);
        return lecturePage;
    }

    public Lecture findById(int id) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isPresent()) {
            Lecture lecture = optionalLecture.get();
            logger.info("Lecture with id {} has been retrieved.", id);
            return lecture;
        } else {
            String errorMessage = "Lecture not found with id: " + id;
            logger.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }
    }

    public boolean existsById(int id) {
        return lectureRepository.existsById(id);
    }

}
