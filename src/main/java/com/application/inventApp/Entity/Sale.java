package com.application.inventApp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "venta")
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "fecha")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date date;

  @Column(name = "precioTotal")
  private BigDecimal priceTotal;

  @ManyToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private User user;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<DetailsSales> detailsSales = new ArrayList<>();
}
