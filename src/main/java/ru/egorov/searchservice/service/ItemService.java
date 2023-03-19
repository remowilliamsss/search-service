package ru.egorov.searchservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.searchservice.model.Item;
import ru.egorov.searchservice.model.StoreType;
import ru.egorov.searchservice.repository.ItemRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<Item> findAllByName(String name, Pageable pageable) {
        return itemRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    public Page<Item> findAllByStore(StoreType store, Pageable pageable) {
        return itemRepository.findAllByOffersStoreName(store, pageable);
    }

    public Item findBySku(String sku) {
        return itemRepository.findBySku(sku);
    }
}
