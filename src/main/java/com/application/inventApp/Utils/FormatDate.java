package com.application.inventApp.Utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FormatDate {
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
}
