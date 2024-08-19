package com.application.inventApp.Exception;

import com.application.inventApp.Enums.Severity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ExceptionDetails {
  private String userMessage;
  private Severity severity;

}
