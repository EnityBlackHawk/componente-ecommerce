package com.example.componenteecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

}
