package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Repository.ProductRepository;
import com.application.inventApp.Repository.SaleRepository;
import com.application.inventApp.Services.ISaleService;
import com.application.inventApp.Utils.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService implements ISaleService {
  @Autowired
  private SaleRepository saleRepository;
  @Autowired
  private Format format;
  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Sale> findAll() {
    return (List<Sale>) saleRepository.findAll();
  }

  @Override
  public Optional<Sale> findById(UUID id) {
    return saleRepository.findById(id);
  }

  @Override
  public Optional<Sale> delete(UUID id) {
    Optional<Sale> optionalSale = saleRepository.findById(id);
    if(optionalSale.isPresent()){
      saleRepository.delete(optionalSale.get());
      return optionalSale;
    }

    return optionalSale;
  }

  @Override
  public void save(Sale sale, List<Product> products) {
    try{
      List<Product> productsI = (List<Product>) productRepository.findAllById(products.stream().map(product -> product.getId()).toList());

      List<BigDecimal> prices = productsI.stream().map(product -> product.getPrice()).toList();


      BigDecimal addTotal = new BigDecimal(0);
      
      for (int i = 0; i<prices.size(); i++){
        addTotal = addTotal.add(prices.get(i));
      }

      BigDecimal totalFormater = this.format.formaterMoney(addTotal);

      System.out.println(totalFormater + " __________________----------__________jdkls");

      sale.setDate(format.getDateFormat());
      sale.setProducts(productsI);
      sale.setPriceTotal(totalFormater);
      saleRepository.save(sale);

    }catch (Exception e){
      System.out.println(e);
    }

  }

  @Override
  public Optional<Sale> update(UUID id, Sale sale) {
    Optional<Sale> saleOptional = saleRepository.findById(id);
    if (saleOptional.isPresent()){

      Sale saleUp = saleOptional.get();
      saleUp.setPriceTotal(sale.getPriceTotal());

      saleRepository.save(saleUp);
    }
    return saleOptional;
  }

}

