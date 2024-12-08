package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.ManyProductDTO;
import com.example.componenteecommerce.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-api", url = "${product-api.url}")
public interface ProductClient {

    @GetMapping("/product/getAll")
    List<ProductDTO> getAll();

    @GetMapping("/product/{id}")
    ProductDTO getById(@PathVariable Long id);

    @PutMapping("/product/{id}")
    ProductDTO update(@PathVariable Long id, ProductDTO product);

    @PostMapping("/product/findMany")
    List<ProductDTO> findMany(@RequestBody ManyProductDTO manyProductDTO);

    @PutMapping("/product/updateMany")
    public List<ProductDTO> updateMany(@RequestBody List<ProductDTO> products);
}
