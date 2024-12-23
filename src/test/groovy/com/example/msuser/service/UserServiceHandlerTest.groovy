package com.example.msuser.service

import com.example.msuser.dao.entity.UserEntity
import com.example.msuser.dao.repository.UserRepository
import com.example.msuser.exception.NotFoundException
import com.example.msuser.exception.WrongCredentialsException
import com.example.msuser.model.request.SignInRequest
import com.example.msuser.model.request.CreateUserRequest
import com.example.msuser.model.request.UpdateUserRequest
import com.example.msuser.service.abstraction.UserService
import com.example.msuser.service.concrete.UserServiceHandler
import com.example.msuser.util.SecurityUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.msuser.mapper.UserMapper.USER_MAPPER

class UserServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    UserService userService
    UserRepository userRepository
    SecurityUtil securityUtil

    def setup() {
        userRepository = Mock()
        securityUtil = Mock()
        userService = new UserServiceHandler(userRepository, securityUtil)
    }

    def "TestSignUp"() {
        given:
        def request = random.nextObject(CreateUserRequest)
        def entity = USER_MAPPER.buildUserEntity(request)

        when:
        userService.signUp(request)

        then:
        1 * userRepository.save(entity)
    }

    def "TestSignIn error"() {
        given:
        def request = random.nextObject(SignInRequest)
        def entity = random.nextObject(UserEntity)

        when:
        userService.signIn(request)

        then:
        1 * securityUtil.verifyPassword(request.mail) >> Optional.of(entity)
        1 * userRepository.findByMail(request) >> Optional.of(request)
        WrongCredentialsException ex = thrown()
        ex.message == "User not match with given credentials"
    }

    def "TestSignIn success"() {
        def signInRequest = new SignInRequest(mail: "user@example.com", password: "password")
        def userEntity = new UserEntity(password: "hashedPassword")
        userRepository.findByMail(signInRequest.mail) >> Optional.of(userEntity)
        securityUtil.verifyPassword(signInRequest.password, userEntity.password) >> true

        when:
        userService.signIn(signInRequest)

        then:
        noExceptionThrown()
    }

    def "TestGetUser success"() {
        given:
        def id = random.nextLong()
        def entity = random.nextObject(UserEntity)
        def expected = USER_MAPPER.buildUserResponse(entity)

        when:
        def response = userService.getUser(id)

        then:
        1 * userRepository.findById(id) >> Optional.of(entity)
        expected == response
    }

    def "TestGetUser error user not found"() {
        given:
        def id = random.nextLong()

        when:
        userService.getUser(id)

        then:
        1 * userRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.message == "User not found!"
    }

    def "TestUpdateUser error user can't update"() {
        given:
        def id = random.nextLong()
        def request = random.nextObject(UpdateUserRequest)
        def entity = random.nextObject(UserEntity)
        def expected = USER_MAPPER.buildUserRequest(id, request)

        when:
        def response = userService.updateUser(id, request)

        then:
        1 * userRepository.findById(id) >> Optional.of(entity)
        expected == response
        NotFoundException ex = thrown()
        ex.message == "User not found!"
    }

    def "TestUpdateUser success"() {
        given:
        def id = random.nextLong()
        def request = random.nextObject(UpdateUserRequest)
        def entity = random.nextObject(UserEntity)
        def expected = USER_MAPPER.buildUserRequest(request, id)

        when:
        userService.updateUser(id, request)

        then:
        1 * userRepository.findById(id) >> Optional.of(entity)
        1 * securityUtil.hashPassword(request.getPassword()) >> request.password
        1 * userRepository.save(expected)
        notThrown(NotFoundException)
    }
}