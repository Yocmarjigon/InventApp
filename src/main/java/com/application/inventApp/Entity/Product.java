package com.application.inventApp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "producto")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nombre")
  private String name;
  @Column(name = "descripcion")
  private String description;
  @Column(name = "precio")
  private BigDecimal price;
  private int stock;
  @Column(name = "fechaAdquisicion")
  @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private Date dateAdd;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "id_proveedor")
  private Supplier supplier;

  @ManyToMany(mappedBy = "products")
  @JsonIgnore
  private List<Sale> sales = new ArrayList<>();



}
