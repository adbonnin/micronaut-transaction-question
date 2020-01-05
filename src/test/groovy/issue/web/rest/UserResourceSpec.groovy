package issue.web.rest

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import issue.domain.User
import issue.repositories.UserRepository
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class UserResourceSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    @Inject
    @Client("/")
    RxHttpClient client

    @Inject
    UserRepository userRepository

    void cleanup() {
        userRepository.deleteAll()
    }

    User createUser(String username) {
        return userRepository.save(new User(username: username))
    }

    void "should fetch the id of the user"() {
        given:
        def user = createUser(username)
        def request = HttpRequest.GET("users/fetchId/$username")

        when:
        def result = client.toBlocking().exchange(request, Integer).body()

        then:
        result == user.id

        where:
        username = 'spock'
    }
}
