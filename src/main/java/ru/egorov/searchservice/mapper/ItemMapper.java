package ru.egorov.searchservice.mapper;

import org.mapstruct.Mapper;
import ru.egorov.searchservice.dto.ItemDto;
import ru.egorov.searchservice.model.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
}
