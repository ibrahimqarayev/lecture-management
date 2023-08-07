package az.mycompany.lecturemanagement.dto.request;

import java.util.List;

public class LectureDto {

    private Integer id;

    private String name;

    private UserDto teacher;

    private List<UserDto> students;

    public LectureDto() {
    }

    public LectureDto(Integer id, String name, UserDto teacher, List<UserDto> students) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getTeacher() {
        return teacher;
    }

    public void setTeacher(UserDto teacher) {
        this.teacher = teacher;
    }

    public List<UserDto> getStudents() {
        return students;
    }

    public void setStudents(List<UserDto> students) {
        this.students = students;
    }
}
