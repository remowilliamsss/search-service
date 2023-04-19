package ru.egorov.searchservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Информация о товаре")
public class ItemDto {

    @Schema(description = "наименование")
    private String name;

    @Schema(description = "артикул")
    private String sku;

    @Schema(description = "категория")
    private String category;

    @Schema(description = "бренд")
    private String brand;

    @Schema(description = "url изображения товара")
    private String image;

    @Schema(description = "цвет")
    private String color;

    @Schema(description = "страна")
    private String country;

    @Schema(description = "пол")
    private String gender;

    @Schema(description = "состав")
    private String composition;

    @Schema(description = "расцветка")
    private String coloring;

    @Schema(description = "Предложения о продаже в различных магазинах")
    private List<OfferDto> offers;

    {
        offers = new ArrayList<>();
    }
}
