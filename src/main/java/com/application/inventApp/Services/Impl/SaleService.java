package com.application.inventApp.Services.Impl;

import com.application.inventApp.Controller.DTO.SaleDTOs.ProductsSoldDTO;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Entity.User;
import com.application.inventApp.Exception.NotFoundException;
import com.application.inventApp.Exception.StockException;
import com.application.inventApp.Repository.ProductRepository;
import com.application.inventApp.Repository.SaleRepository;
import com.application.inventApp.Services.ISaleService;
import com.application.inventApp.Utils.Format;
import com.application.inventApp.Utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* dddd */
@Service
public class SaleService implements ISaleService {
  @Autowired
  private SaleRepository saleRepository;
  @Autowired
  private Format format;
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  Utils utils;

  @Override
  public List<Sale> findAll() {
    List<Sale> sales = (List<Sale>) saleRepository.findAll();
    return sales;

  }

  @Override
  public Optional<Sale> findById(UUID id) {
    return saleRepository.findById(id);
  }

  @Override
  public Optional<Sale> delete(UUID id) {
    Optional<Sale> optionalSale = saleRepository.findById(id);
    if (optionalSale.isPresent()) {
      saleRepository.delete(optionalSale.get());
      return optionalSale;
    }

    return optionalSale;
  }

  @Override
  public void save(List<ProductsSoldDTO> productsSoldDTOS) throws StockException, NotFoundException {

    Sale sale = new Sale();
    List<UUID> produtsId = productsSoldDTOS.stream().map(productsSoldDTO -> productsSoldDTO.getProduct().getId()).toList();
    List<Product> products = (List<Product>) this.productRepository.findAllById(produtsId);
    User user = this.utils.findUserLogging();
    BigDecimal multiplyPrice = new BigDecimal(0);
    BigDecimal addTotal = new BigDecimal(0);
    

    if (products.stream().anyMatch(product -> product != null)) {
      for (int i = 0; i < products.size() && i < productsSoldDTOS.size(); i++) {

        if (products.get(i).getStock() < productsSoldDTOS.get(i).getNumberSale()) {
          throw new StockException("El stock del producto: " + products.get(i).getName() + " es bajo");
        }
        
        BigDecimal price = products.get(i).getPrice();
        int stock = products.get(i).getStock();
        int numberSale = productsSoldDTOS.get(i).getNumberSale();

        products.get(i).setStock(stock - numberSale);
        multiplyPrice =  multiplyPrice.add(price.multiply(new BigDecimal(numberSale)));
        addTotal = addTotal.add(multiplyPrice);
        System.out.println(price + " MultiPrice" + multiplyPrice + "----AddTotal" + addTotal );
        multiplyPrice = new BigDecimal(0);
        System.out.println(price.multiply(new BigDecimal(numberSale)) );
      }
    }

    sale.setProducts(products);
    sale.setPriceTotal(addTotal);
    sale.setUser(user);
    sale.setDate(this.format.getDateFormat());
    saleRepository.save(sale);


  }

  @Override
  public Optional<Sale> update(UUID id, Sale sale) {
    Optional<Sale> saleOptional = saleRepository.findById(id);
    if (saleOptional.isPresent()) {

      Sale saleUp = saleOptional.get();
      saleUp.setPriceTotal(sale.getPriceTotal());

      saleRepository.save(saleUp);
    }
    return saleOptional;
  }

}

