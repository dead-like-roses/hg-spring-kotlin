package eu.xetoo.koza_spring.product

import eu.xetoo.koza_spring.image.Image
import eu.xetoo.koza_spring.product.response.ProductResponse
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
class Product(
    var name: String,
    var description: String,
    @OneToOne
    var image: Image,
    var price: Double,
    var packagingType: String,
    var available:Boolean,
    var visible: Boolean,
    var deleted: Boolean = false,
    @OneToMany(mappedBy = "product")
    val options: List<ProductOption> = listOf(),
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID? = null
) {
    fun toResponse(): ProductResponse =
        ProductResponse(
            id = this.id!!,
            image = this.image.id!!,
            name = this.name,
            description = this.description,
            packagingType = this.packagingType,
            price = this.price,
            available = this.available,
            visible = this.visible,
            options = this.options.associate { it.id!! to it.optionName }
        )
}