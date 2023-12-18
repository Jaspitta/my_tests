package com.example.demo;

import com.example.demo.utils.ListUtils;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Foo {

  @Valid
  List<FooInternal> listOfFoes;

  @NotNull(message = "some in Foo must not be null")
  String some;

  public void setListOfFoes(List<FooInternal> listOfFoes) {
    this.listOfFoes = ListUtils.removeNullElements(listOfFoes);
  }

}
