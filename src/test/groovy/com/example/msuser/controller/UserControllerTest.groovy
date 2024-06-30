package com.example.msuser.controller

import com.example.msuser.model.enums.UserType
import com.example.msuser.model.response.UserResponse
import com.example.msuser.service.abstraction.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.http.HttpStatus.OK

class UserControllerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    UserService userService
    MockMvc mockMvc

    def setup() {
        userService = Mock()
        def userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build()
    }

    def "TestGetUser"() {
        given:
        def id = 1l
        def url = "v1/users/$id"
        def responseView = new UserResponse("John", "Doe", "mail@gmail.com", "photo.jpg",
                UserType.BUYER, LocalDate.of(2024, 06, 30))
        def expectedResponse = '''{
            "name": "name_5616cb966603",
            "surname": "surname_b078c87ffa70",
            "mail": "mail_91dc1a99afe3",
            "photo": "photo_bb190dde5d78",
            "userType": "BUYER",
            "birthDate": "2024-06-30"
        }'''

        when:
        def result = mockMvc.perform(get(url))
        .andReturn()

        then:
        1 * userService.getUser(id) >> responseView
        def response = result.response
        response.status == OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }

}