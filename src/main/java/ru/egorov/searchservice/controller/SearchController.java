package ru.egorov.searchservice.controller;

import lombok.RequiredArgsConstructor;
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
public class SearchController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/search")
    public ResponseEntity<Page<ItemDto>> search(@RequestParam(name = "query") String query,
                                               @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ItemDto> itemPage = itemService.findAllByName(query, pageable)
                .map(itemMapper::toDto);

        return new ResponseEntity<>(itemPage, HttpStatus.OK);
    }

    @GetMapping("items/{sku}")
    public ResponseEntity<ItemDto> show(@PathVariable("sku") String sku) {
        ItemDto itemDto = itemMapper.toDto(itemService.findBySku(sku));
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @GetMapping("stores/{store}")
    public ResponseEntity<Page<ItemDto>> storePage(@PathVariable("store") StoreType store,
                       @PageableDefault(sort = {"offers.price"}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ItemDto> itemPage = itemService.findAllByStore(store, pageable)
                .map(itemMapper::toDto);

        return new ResponseEntity<>(itemPage, HttpStatus.OK);
    }
}
