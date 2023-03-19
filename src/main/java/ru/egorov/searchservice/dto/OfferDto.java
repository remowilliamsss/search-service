package ru.egorov.searchservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egorov.searchservice.model.StoreType;

@Getter
@Setter
public class OfferDto {

    private Double price;

    private String priceCurrency;

    private String size;

    private StoreType storeName;

    private String url;
}
