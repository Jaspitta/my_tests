package com.example.demo.tests;

import com.example.demo.models.BusinessDetails;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder
@Log4j2
public class CompareBD {

    BusinessDetails old;
    BusinessDetails updated;
    List updates;


    public void compareBD(Optional<BusinessDetails> old, Optional<BusinessDetails> updated, List details) {
        if (details == null)
            details = new ArrayList<>(); //lost with the stack

        if (!old.equals(updated)) {
            if (!Objects.equals(old.orElse(new BusinessDetails()).getName(), updated.orElse(new BusinessDetails()).getName()))
                details.add("I got updated");
        }

        log.info("list after check is {}", details);

    }

}
