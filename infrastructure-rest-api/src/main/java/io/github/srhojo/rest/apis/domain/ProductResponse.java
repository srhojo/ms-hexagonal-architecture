package io.github.srhojo.rest.apis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ProductResponse implements Serializable {
    private String id;
    private String name;
    private Double price;
}
