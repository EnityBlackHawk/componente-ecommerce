package com.example.componenteecommerce.dto;

import com.example.componenteecommerce.entity.Item;
import com.example.componenteecommerce.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;
    @NotNull
    private User user;
    @NotNull
    private List<ItemDTO> items;

}
