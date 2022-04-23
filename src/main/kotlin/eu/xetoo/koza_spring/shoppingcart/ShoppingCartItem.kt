package eu.xetoo.koza_spring.shoppingcart

import eu.xetoo.koza_spring.product.Product
import eu.xetoo.koza_spring.product.ProductOption
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "shopping_cart_items")
class ShoppingCartItem(
    val size: Int,
    @ManyToOne
    val option: ProductOption,
    @ManyToOne
    val product: Product,
    @ManyToOne
    val shoppingCart: ShoppingCart,
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