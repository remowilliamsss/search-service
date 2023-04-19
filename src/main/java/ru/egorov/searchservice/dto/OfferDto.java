package ru.egorov.searchservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.egorov.searchservice.model.StoreType;

@Getter
@Setter
@Schema(description = "Предложение о продаже")
public class OfferDto {

    @Schema(description = "цена")
    private Double price;

    @Schema(description = "валюта")
    private String priceCurrency;

    @Schema(description = "размеры")
    private String size;

    @Schema(description = "название магазина")
    private StoreType storeName;

    @Schema(description = "url товара на сайте магазина")
    private String url;
}
