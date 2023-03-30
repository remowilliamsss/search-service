package ru.egorov.searchservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.egorov.searchservice.dto.ItemDto;
import ru.egorov.searchservice.mapper.ItemMapper;
import ru.egorov.searchservice.model.Item;
import ru.egorov.searchservice.model.StoreType;
import ru.egorov.searchservice.service.ItemService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class SearchController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/search")
    public Page<ItemDto> search(@RequestParam(name = "query") String query,
                        @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Item> itemPage = itemService.findAllByName(query, pageable);

        return itemPage.map(itemMapper::toDto);
    }

    @GetMapping("items/{sku}")
    public ItemDto show(@PathVariable("sku") String sku) {
        return itemMapper.toDto(itemService.findBySku(sku));
    }

    @GetMapping("stores/{store}")
    public Page<ItemDto> storePage(@PathVariable("store") StoreType store,
                       @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Item> itemPage = itemService.findAllByStore(store, pageable);

        return itemPage.map(itemMapper::toDto);
    }
}
