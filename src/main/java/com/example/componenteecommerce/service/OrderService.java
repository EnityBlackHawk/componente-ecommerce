package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.ItemDTO;
import com.example.componenteecommerce.dto.ManyProductDTO;
import com.example.componenteecommerce.dto.OrderDTO;
import com.example.componenteecommerce.dto.ProductDTO;
import com.example.componenteecommerce.entity.Order;
import com.example.componenteecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService extends GenericService<Order, UUID, OrderRepository, OrderDTO, OrderDTO> {

    private final ProductClient productClient;

    protected OrderService(OrderRepository repository, ProductClient productClient) {
        super(repository, Order.class, OrderDTO.class, OrderDTO.class);
        this.productClient = productClient;
    }

    @Override
    public OrderDTO create(OrderDTO entity) {

        List<ProductDTO> orderProds = entity.getItems().stream()
                .map(ItemDTO::getProduct)
                .toList();

        List<ProductDTO> products = productClient.findMany(
                new ManyProductDTO(
                    orderProds.stream().map(ProductDTO::getCode).toList()
                )
        );

        for(var op : orderProds) {
            var product = products.stream().filter(p -> p.getCode().equals(op.getCode())).findFirst().orElseThrow(() -> {
                throw new RuntimeException("Product not found: " + op.getCode());
            });

            if(product.getQuantity() < op.getQuantity()) {
                throw new RuntimeException("Product out of stock: " + op.getCode());
            }

            product.setQuantity(product.getQuantity() - op.getQuantity());
        }

        productClient.updateMany(products);

        Order result = repository.save(modelMapper.map(entity, entityClass));
        var dto = modelMapper.map(result, dtoClass);
        return dto;
    }

    @Override
    public List<OrderDTO> findAll() {
        var orders = super.findAll();
        for(var order : orders) {
            List<Long> ids = order.getItems().stream().map(itemDTO -> itemDTO.getProduct().getCode()).toList();
            var products = productClient.findMany( new ManyProductDTO(ids));
            for(var item : order.getItems()) {
                var product = products.stream().filter(productDTO -> productDTO.getCode().equals(item.getProduct().getCode())).findFirst();
                product.ifPresent(item::setProduct);
            }
        }
        return orders;
    }
}
