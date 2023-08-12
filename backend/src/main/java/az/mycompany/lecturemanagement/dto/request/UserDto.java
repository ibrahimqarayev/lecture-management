package az.mycompany.lecturemanagement.dto.request;

public class UserDto {

    private Integer id;

    private String identityNumber;

    private String name;

    private String surname;

    private String email;

    private String gender;

    private String role;

    public UserDto() {
    }

    public UserDto(Integer id, String identityNumber, String name, String surname, String email, String gender, String role) {
        this.id = id;
        this.identityNumber = identityNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
