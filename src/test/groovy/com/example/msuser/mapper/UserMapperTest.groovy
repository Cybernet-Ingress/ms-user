package com.example.msuser.mapper

import com.example.msuser.dao.entity.UserEntity
import com.example.msuser.model.request.CreateUserRequest
import com.example.msuser.model.request.UpdateUserRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.msuser.mapper.UserMapper.USER_MAPPER

class UserMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildUserEntity"() {
        given:
        def request = random.nextObject(CreateUserRequest)

        when:
        def entity = USER_MAPPER.buildUserEntity(request)

        then:
        entity.name == request.name
        entity.surname == request.surname
        entity.mail == request.mail
        entity.password == request.password
        entity.type == request.type
        entity.photo == request.photo
    }

    def "TestBuildUserResponse"() {
        given:
        def entity = random.nextObject(UserEntity)

        when:
        def response = USER_MAPPER.buildUserResponse(entity)

        then:
        response.surname == entity.surname
        response.name == entity.name
        response.mail == entity.mail
        response.surname == entity.surname
        response.userType == entity.type
        response.birthDate == entity.birthDate
    }

    def "TestBuildUserRequest"() {
        given:
        def request = random.nextObject(UpdateUserRequest)
        def id = random.nextLong()

        when:
        def entity = USER_MAPPER.buildUserRequest(request, id)

        then:
        entity.id == id
        entity.name == request.name
        entity.surname == request.surname
        entity.mail == request.mail
        entity.photo == request.photo
        entity.password == request.password
    }
}