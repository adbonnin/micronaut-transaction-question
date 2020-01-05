package issue.domain

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
class User {

    @Id
    @GeneratedValue
    Long id

    String username
}
