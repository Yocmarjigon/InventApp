package com.application.inventApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "categoria")
public class Category extends BaseEntity{
  
  @Column(name = "nombre")
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
  @JsonIgnore
  private List<Product> products = new ArrayList<>();

}
