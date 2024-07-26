package com.application.inventApp.Entity;

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
@Entity(name = "proveedor")
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "nombre")
  private String name;
  @Column(name = "contacto")
  private String contact;
  @Column(name = "correo")
  private String email;
  @Column(name = "direccion")
  private String addres;

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Product> products = new ArrayList<>();

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Order> orders = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private User user;

}
