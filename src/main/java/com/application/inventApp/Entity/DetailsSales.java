package com.application.inventApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "detallesVenta")
public class DetailsSales {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

}
