package com.application.inventApp.Entity;

import com.application.inventApp.Entity.Enums.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name= "usuario")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "nombre")
  private String name;
  @Column(name = "contraseña")
  private String password;
  @Enumerated(EnumType.STRING)
  private Rol rol;

}
