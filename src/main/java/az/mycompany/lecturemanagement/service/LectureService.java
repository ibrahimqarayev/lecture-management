package az.mycompany.lecturemanagement.service;

import az.mycompany.lecturemanagement.repository.LectureRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }


}
