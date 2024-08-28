package com.application.inventApp.Utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class Format {
  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  public Date getDateFormat() {
    try {
      Date date = dateFormat.parse(dateFormat.format(new Date()));
      return date;
    } catch (ParseException e) {

      System.out.println(e);
      return null;
    }

  }

  public Date formaterDate(Date date) {
    try {
      return dateFormat.parse(dateFormat.format(date));
    } catch (ParseException e) {
      System.out.println(e);
      return null;
    }

  }

  public String formaterMoney(BigDecimal decimal) {
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
    return numberFormat.format(decimal);
  }
}
