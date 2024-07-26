package com.application.inventApp.Entity;

import jakarta.persistence.*;
import lombok.*;

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

}
