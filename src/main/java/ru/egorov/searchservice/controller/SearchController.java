package ru.egorov.searchservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egorov.searchservice.dto.ItemDto;
import ru.egorov.searchservice.mapper.ItemMapper;
import ru.egorov.searchservice.model.StoreType;
import ru.egorov.searchservice.service.ItemService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Поиск", description = "Методы для поиска товаров")
public class SearchController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/search")
    @Operation(summary = "Поиск по наименованию товара")
    public ResponseEntity<Page<ItemDto>> search(
            @RequestParam(name = "query") @Parameter(description = "Поисковой запрос") String query,
            @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC)
            @ParameterObject Pageable pageable) {

        Page<ItemDto> itemPage = itemService.findAllByName(query, pageable)
                .map(itemMapper::toDto);

        return new ResponseEntity<>(itemPage, HttpStatus.OK);
    }

    @GetMapping("items/{sku}")
    @Operation(summary = "Возвращает товар по артиклю")
    public ResponseEntity<ItemDto> show(@PathVariable("sku") @Parameter(description = "Артикул") String sku) {
        ItemDto itemDto = itemMapper.toDto(itemService.findBySku(sku));
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @GetMapping("stores/{store}")
    @Operation(summary = "Возвращает товары по магазину")
    public ResponseEntity<Page<ItemDto>> storePage(
            @PathVariable("store") @Parameter(description = "Название магазина") StoreType store,
            @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC)
            @ParameterObject Pageable pageable) {

        Page<ItemDto> itemPage = itemService.findAllByStore(store, pageable)
                .map(itemMapper::toDto);

        return new ResponseEntity<>(itemPage, HttpStatus.OK);
    }
}
