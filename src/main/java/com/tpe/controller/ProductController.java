package com.tpe.controller;


import com.tpe.domain.Product;
import com.tpe.dto.ProductDTO;
import com.tpe.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping ("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //17-a)ürün oluşturma/ekleme->http://localhost:8080/products/add + JSON BODY + POST

    /*
    {
   "productName":"keyboardLGTCH99",
   "brand":"logitech",
   "price":500
    }
     */

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO productDTO){
        productService.save(productDTO);
        return ResponseEntity.ok("Saved is succcessfully");

    }

    //18-a)-Tüm productları getirme->http://localhost:8080/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=productService.getAllProducts();

        return ResponseEntity.ok(products);

    }

    //19-a)-Id ile product getirme->http://localhost:8080/products/5
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductDTOById(@PathVariable("id") Long id){
        ProductDTO productDTO = productService.findByIdWithDTO(id);
        return ResponseEntity.ok(productDTO);
    }

    // Tüm productları page page gösterme

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam("page") int page,
                                                          @RequestParam("size")int size,
                                                          @RequestParam("sort")String prop,
                                                          @RequestParam("direction")Sort.Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Product> allProducts= productService.getAllProductsByPage(pageable);

        return ResponseEntity.ok(allProducts);
    }

 //   @GetMapping("/page")
 //   public ResponseEntity<Page<ProductDTO>> getAllProductDTO(@RequestParam("page") int page,
 //                                                         @RequestParam("size")int size,
 //                                                         @RequestParam("sort")String prop,
 //                                                         @RequestParam("direction")Sort.Direction direction){
 //       Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
 //       Page<ProductDTO> allProducts= productService.getAllProductDTOByPage(pageable);
//
 //       return ResponseEntity.ok(allProducts);
 //   }




}
