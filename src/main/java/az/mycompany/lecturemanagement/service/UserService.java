package az.mycompany.lecturemanagement.service;

import az.mycompany.lecturemanagement.dto.request.UserDto;
import az.mycompany.lecturemanagement.dto.response.PageInfoWithContent;
import az.mycompany.lecturemanagement.entity.User;
import az.mycompany.lecturemanagement.enums.Role;
import az.mycompany.lecturemanagement.exception.DuplicateResourceException;
import az.mycompany.lecturemanagement.exception.ResourceNotFoundException;
import az.mycompany.lecturemanagement.repository.UserRepository;
import az.mycompany.lecturemanagement.validator.IdentityNumberValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto create(User user) {
        final String identityNumber = user.getIdentityNumber();
        if (user.getId() == null) {
            validateIdentityNumberAndCheckDuplicate(identityNumber);
            return entityToDto(userRepository.save(user));
        }
        throw new DuplicateResourceException("User already exist");
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

//    public Page<UserDto> findAll(Pageable pageable) {
//        Page<User> userPage = userRepository.findAll(pageable);
//        return userPage.map(this::entityToDto);
//    }

    public PageInfoWithContent<UserDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<UserDto> userDtoPage = userRepository.findAll(pageable).map(this::entityToDto);
        return new PageInfoWithContent<>(userDtoPage);
    }


    public UserDto findById(int id) {
        return entityToDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)));
    }

    @Transactional
    public void deleteById(int id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    private void validateIdentityNumberAndCheckDuplicate(String identityNumber) {
        validateIdentityNumber(identityNumber);
        throwIfIdentityNumberInUse(identityNumber);
    }

    private void validateIdentityNumber(String identityNumber) {
        IdentityNumberValidator.validateIdentityNumber(identityNumber);
    }

    private void throwIfIdentityNumberInUse(String identityNumber) {
        if (userRepository.existsByIdentityNumber(identityNumber)) {
            throw new DuplicateResourceException("This identity number is already in use: " + identityNumber);
        }
    }

    public List<UserDto> findUsersByRole(Role role) {
        return userRepository.findAllByRole(role).stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<UserDto> findPotentialUsers(List<Integer> ids) {
        if (ids.isEmpty()) {
            return findUsersByRole(Role.STUDENT);
        }
        return userRepository.findAllByRoleAndIdIsNotIn(Role.STUDENT, ids).stream().map(this::entityToDto).collect(Collectors.toList());
    }


    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setIdentityNumber(user.getIdentityNumber());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender().name());
        userDto.setRole(user.getRole().name());
        return userDto;
    }

}
