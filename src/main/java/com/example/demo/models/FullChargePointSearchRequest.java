package com.example.demo.models;

import lombok.Data;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class FullChargePointSearchRequest {

  Double minPower;

  Integer pageSize;

  Integer pageNumber;

  List<String> fields;

  FullChargePointSearchRequest(Double minPower, Integer pageSize, Integer pageNumber, List<String> fields){

    this.fields = ( fields == null || fields.isEmpty() )?
        new ArrayList<>(List.of("idColonnina", "indirizzo", "stato", "potenza erogata", "tipo connettore", "Tipo ricarica", "CPO"))
      :
        fields;

    this.minPower = minPower;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;

  }

}
