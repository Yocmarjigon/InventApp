package com.application.inventApp.Services.Impl;

import com.application.inventApp.Controller.DTO.SaleDTOs.ProductsSoldDTO;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Exception.StockException;
import com.application.inventApp.Repository.ProductRepository;
import com.application.inventApp.Repository.SaleRepository;
import com.application.inventApp.Services.ISaleService;
import com.application.inventApp.Utils.Format;
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
    if (optionalSale.isPresent()) {
      saleRepository.delete(optionalSale.get());
      return optionalSale;
    }

    return optionalSale;
  }

  @Override
  public void save(List<ProductsSoldDTO> productsSoldDTOS) throws StockException {

      Sale sale = new Sale();
      List<UUID> produtsId = productsSoldDTOS.stream().map(productsSoldDTO -> productsSoldDTO.getProduct().getId()).toList();
      List<Product> products = (List<Product>) this.productRepository.findAllById(produtsId);

      if (products.stream().anyMatch(product -> product != null)) {
        for (int i = 0; i < products.size() && i < productsSoldDTOS.size(); i++) {
          if (products.get(i).getStock() < productsSoldDTOS.get(i).getNumberSale()) {
            throw new StockException("El stock del producto: " + products.get(i).getId() +  " es bajo");
          }
          products.get(i).setStock(products.get(i).getStock() - productsSoldDTOS.get(i).getNumberSale());
        }
      }

      List<BigDecimal> prices = products.stream().map(product -> product.getPrice()).toList();


      BigDecimal addTotal = new BigDecimal(0);

      for (int i = 0; i < prices.size(); i++) {
        addTotal = addTotal.add(prices.get(i));
      }


      sale.setDate(format.getDateFormat());
      sale.setProducts(products);
      sale.setPriceTotal(addTotal);
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

