package com.application.inventApp.Entity;

import com.application.inventApp.Enums.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "usuario")
public class User extends BaseEntity{

  @Column(name = "nombre")
  private String name;
  @Column(name = "contrasenia")
  private String password;
  @Enumerated(EnumType.STRING)
  private Rol rol;

  @Column(name = "is_enabled")
  private boolean isEnabled = true;

  @Column(name = "account_No_Expired")
  private boolean accountNoExpired = true;

  @Column(name = "account_No_Locked")
  private boolean accountNoLocked = true;

  @Column(name = "credential_No_Expired")
  private boolean credentialNoExpired = true;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Sale> sales = new ArrayList<>();
}
