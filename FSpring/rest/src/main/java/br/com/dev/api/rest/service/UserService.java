package br.com.dev.api.rest.service;

import br.com.dev.api.rest.controller.CreateUserDto;
import br.com.dev.api.rest.controller.UpdateUserDto;
import br.com.dev.api.rest.entity.User;
import br.com.dev.api.rest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        //Converter DTO -> Entity
        User entity = new User();
        entity.setUserName(createUserDto.username());
        entity.setEmail(createUserDto.email());
        entity.setPassword(createUserDto.password());

        User userSaved = userRepository.save(entity);
        return userSaved.getUserID();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUserName(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);
        }

    }

    public void deleteUserById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if(userExists) {
            userRepository.deleteById(id);
        }
    }
    /*
    public UUID createUser(CreateUserDto createUserDto) {
        //Converter DTO -> Entity
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserID();
    }
     */

}
