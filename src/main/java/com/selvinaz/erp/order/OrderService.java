package com.selvinaz.erp.order;

import com.selvinaz.erp.product.Product;
import com.selvinaz.erp.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order) {

        Long productId = order.getProduct().getId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < order.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        product.setStockQuantity(product.getStockQuantity() - order.getQuantity());
        productRepository.save(product);

        order = new Order(null, product, order.getQuantity());

        return orderRepository.save(order);
    }
}