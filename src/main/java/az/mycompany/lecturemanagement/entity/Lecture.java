package az.mycompany.lecturemanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany
    @JoinTable(
            name = "users_lectures",
            joinColumns = {@JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private List<User> students;

}
