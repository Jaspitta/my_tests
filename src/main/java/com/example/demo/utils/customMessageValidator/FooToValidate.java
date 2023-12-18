package com.example.demo;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class FooToValidate {

  @NotBlank()
  @NotNull()
  @Pattern(regexp = "^(https?:\\/\\/)?www\\.[^\\s]*")
  String link;

  FooProp prop;

  enum FooProp{

    BELLO,
    BRUTTO

  }

}
