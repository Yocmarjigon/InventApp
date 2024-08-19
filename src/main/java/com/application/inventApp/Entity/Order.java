package com.application.inventApp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "pedido")
public class Order extends BaseEntity{

  @Column(name = "fecha")
  @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private Date date;
  @Column(name = "fecha-llegada")
  @JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private  Date dateArrival;
  @ManyToOne
  @JoinColumn(name = "id_proveedor")
  private Supplier supplier;
  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private User user;

}
