package com.application.inventApp.Entity;

import com.application.inventApp.Entity.Enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "pedido")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "fecha")
  private Date date;
  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private State state;

}
