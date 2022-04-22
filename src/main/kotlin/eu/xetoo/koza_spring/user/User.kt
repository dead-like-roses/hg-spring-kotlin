package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.role.Role
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    val name: String,
    val login: String,
    val password: String,
    val changedPassword: LocalDateTime,
    @ManyToOne
    val role: Role,
    val verified: Boolean = false,
    val created: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID? = null
)