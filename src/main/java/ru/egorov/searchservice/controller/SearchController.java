package ru.egorov.searchservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.egorov.searchservice.dto.ItemDto;
import ru.egorov.searchservice.dto.ItemResponse;
import ru.egorov.searchservice.mapper.ItemMapper;
import ru.egorov.searchservice.model.Item;
import ru.egorov.searchservice.model.StoreType;
import ru.egorov.searchservice.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class SearchController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/search")
    public ItemResponse search(@RequestParam(name = "query") String query,
                         @RequestParam(name = "page", required = false) Integer page,
                         @RequestParam(name = "size", required = false) Integer size) {

        Page<Item> itemPage = (size == null || page == null) ?
                itemService.findAllByName(query, PageRequest.of(0, 30))
                : itemService.findAllByName(query, PageRequest.of(page, size));

        List<ItemDto> itemDtos = itemPage.getContent()
                .stream()
                .map(itemMapper::toDto)
                .toList();

        return new ItemResponse(itemDtos, itemPage.getTotalPages());
    }

    @GetMapping("items/{sku}")
    public ItemDto show(@PathVariable("sku") String sku) {
        return itemMapper.toDto(itemService.findBySku(sku));
    }

    @GetMapping("stores/{store}")
    public ItemResponse storePage(@PathVariable("store") StoreType store,
                            @RequestParam(name = "page", required = false) Integer page,
                            @RequestParam(name = "size", required = false) Integer size) {

        Page<Item> itemPage = (size == null || page == null) ?
                itemService.findAllByStore(store, PageRequest.of(0, 30))
                : itemService.findAllByStore(store, PageRequest.of(page, size));

        List<ItemDto> itemDtos = itemPage.getContent()
                .stream()
                .map(itemMapper::toDto)
                .toList();

        return new ItemResponse(itemDtos, itemPage.getTotalPages());
    }
}
