package eu.xetoo.koza_spring.role

import javax.persistence.*


@Entity
@Table(name = "roles")
class Role(
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    val name: ERole,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
)