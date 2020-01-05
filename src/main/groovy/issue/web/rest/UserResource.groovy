package issue.web.rest

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import issue.repositories.UserRepository

import javax.inject.Inject

@Controller('/users')
class UserResource {

    @Inject
    UserRepository userRepository

    @Get('/fetchId/{username}')
    Integer fetchId(String username) {
        return userRepository.findByUsername(username).id
    }
}
