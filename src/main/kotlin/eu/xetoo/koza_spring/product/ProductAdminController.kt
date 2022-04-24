package eu.xetoo.koza_spring.product

import eu.xetoo.koza_spring.product.request.ProductCreationRequest
import eu.xetoo.koza_spring.product.response.ProductResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/product")
@RestController
@Secured("ROLE_ADMIN")
@CrossOrigin(originPatterns = ["*"])
class ProductAdminController(
    private val productService: ProductService
) {

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addProduct(@RequestBody request: ProductCreationRequest): ProductResponse =
        productService.insertProduct(
            name = request.name,
            description = request.description,
            imageID = request.image,
            price = request.price,
            packageType = request.packagingType,
            available = request.available,
            visible = request.visible,
            options = request.options
        ).toResponse()

    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProduct(@PathVariable id: String) {
        productService.delete(UUID.fromString(id))
    }


    @PostMapping("/{id}/edit")
    @ResponseStatus(HttpStatus.OK)
    fun editProduct(
        @PathVariable id: String,
        @RequestBody body: ProductCreationRequest
    ): ProductResponse =
        productService.edit(
            id = UUID.fromString(id),
            name = body.name,
            description = body.description,
            imageID = body.image,
            price = body.price,
            packageType = body.packagingType,
            available = body.available,
            visible = body.visible,
            options = body.options
        ).toResponse()


}