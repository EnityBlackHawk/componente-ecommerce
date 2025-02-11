package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.*;
import com.example.componenteecommerce.dto.create.CreateOrderDTO;
import com.example.componenteecommerce.entity.Order;
import com.example.componenteecommerce.enums.OrderStatus;
import com.example.componenteecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService extends GenericService<Order, UUID, OrderRepository, OrderDTO, CreateOrderDTO> {

    private final ProductClient productClient;
    private final PaymentQueueService paymentQueueService;

    protected OrderService(OrderRepository repository, ProductClient productClient, PaymentQueueService paymentQueueService) {
        super(repository, Order.class, OrderDTO.class, CreateOrderDTO.class);
        this.productClient = productClient;
        this.paymentQueueService = paymentQueueService;
        paymentQueueService.setPaymentDTOConsumer(this::updateOrder);
    }

    @Override
    public OrderDTO create(CreateOrderDTO entity) {

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
            op.setValue(product.getValue());
        }

        productClient.updateMany(products);

        Order result = repository.save(modelMapper.map(entity, entityClass));

        double value = 0.0;
        for(var items : entity.getItems()) {
            value += items.getProduct().getValue() * items.getQuantity();
        }

        var pay = entity.getPayment();
        pay.setOrderId(result.getId());
        pay.setValue(value);

        paymentQueueService.sendPaymentToQueue(entity.getPayment());

        var dto = modelMapper.map(result, dtoClass);
        // TODO fill all the fields
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

    @Override
    public OrderDTO findById(UUID id) {
        var order = super.findById(id);
        List<Long> ids = order.getItems().stream().map(itemDTO -> itemDTO.getProduct().getCode()).toList();
        var products = productClient.findMany( new ManyProductDTO(ids));
        for(var item : order.getItems()) {
            var product = products.stream().filter(productDTO -> productDTO.getCode().equals(item.getProduct().getCode())).findFirst();
            product.ifPresent(item::setProduct);
        }
        return order;
    }

    public void updateOrder(PaymentResponseDTO dto) {
        var order = repository.findById(dto.getOrderId()).orElseThrow(() -> {
            throw new RuntimeException("Order not found: " + dto.getOrderId());
        });

        order.setPaymentId(dto.getId());
        order.setStatus(OrderStatus.APPROVED);
        repository.save(order);
    }
}
