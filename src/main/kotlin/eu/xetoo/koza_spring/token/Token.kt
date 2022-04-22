package eu.xetoo.koza_spring.token

import eu.xetoo.koza_spring.user.User
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="tokens")
class Token(
    @ManyToOne
    val user: User,
    val expires: LocalDateTime,
    val issued: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID? = null
)