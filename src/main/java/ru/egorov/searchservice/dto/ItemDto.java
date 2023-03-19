package ru.egorov.searchservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egorov.searchservice.model.Offer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemDto {

    private String name;

    private String sku;

    private String category;

    private String brand;

    private String image;

    private String color;

    private String country;

    private String gender;

    private String composition;

    private String coloring;

    private List<Offer> offers;

    {
        offers = new ArrayList<>();
    }
}
