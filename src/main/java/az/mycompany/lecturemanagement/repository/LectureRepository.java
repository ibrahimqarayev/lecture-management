package az.mycompany.lecturemanagement.repository;

import az.mycompany.lecturemanagement.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
}
