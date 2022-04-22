package eu.xetoo.koza_spring.image

import eu.xetoo.koza_spring.user.User
import org.hibernate.annotations.GenericGenerator
import java.sql.Blob
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "images")
class Image(
    @Lob
    @Column(name="data", columnDefinition="BLOB")
    val data: ByteArray,
    val contentType: String,
    @ManyToOne
    val owner: User,
    val created: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null
)