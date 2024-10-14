package com.application.inventApp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "producto")
public class Product extends BaseEntity{

  @Column(name = "nombre")
  private String name;
  @Column(name = "precio", precision = 14, scale = 2)
  private BigDecimal price;
  private int stock;
  @Column(name = "fechaAdquisicion")
  @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private Date dateAdd;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private Category category;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "id_proveedor")
  private Supplier supplier;

  @ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
  @JsonIgnore
  private List<Sale> sales = new ArrayList<>();

}
