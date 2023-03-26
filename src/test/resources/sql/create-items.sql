delete from offer;
delete from item;

insert into item(name, sku, category, brand, image, color, country, gender, composition, coloring) values
    ('Nike x Travis Scott Air Trainer 1', 'DR7515-200', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/iblock/060/060181d6eb75f77e373df9b511db7224.jpg', 'Коричневый',
     'Индонезия', 'Мужское', null, 'Coriander/Ashen Slate-Wheat-Light Sienna'),
    ('Nike Air Force 1 ''07', 'DR9867-100', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/797/7978e2678bed43750d367be1b8729a62.webp', 'Белый',
     'Вьетнам', 'Мужское', 'Верх: натуральная кожа подошва: резина', 'White/White-Khaki'),
    ('Converse Chuck 70 OX', 'A00538', 'Кеды', 'Converse',
     'https://sneakerhead.ru/upload/iblock/1cd/1cd1c60e1869265c4ebb4aea040d16b9.jpg', 'Бежевый', 'Вьетнам', 'Женское',
     'Верх: хлопок подошва: резина', 'Yellow/Orange'),
    ('adidas Originals x Jeremy Scott Wings 4.0', 'GY4419', 'Кроссовки', 'adidas Originals',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/6fe/6fe5f8d16f41a9ea327fef4853693ec9.webp', 'Чёрный',
     'Китай', 'Мужское', null, null),
    ('Nike Joyride ENV ISPA', 'BV4584-400', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/617/617772a87f646cc95a0e87c166717d0c.webp', 'Голубой',
     'Вьетнам', null, null, null),
    ('Nike Air Zoom-Type SE', 'CV2220-001', 'Обувь', 'Nike',
     'https://footboxshop.ru/upload/resize_cache/iblock/9ec/600_600_2/mfs5hqhhak0256ijytzvf1s2k3cucuyz.jpg',
     'Серый', null, 'Мужское', 'Верх: текстиль Подошва: резина, пластик', 'Grey Fog/Dk Smoke Grey-Campfire Orange'),
    ('Jordan Legacy 312 "Exploration Unit"', 'FB1875-141', 'Обувь', 'Jordan',
     'https://footboxshop.ru/upload/resize_cache/iblock/2a9/600_600_2/imxvwkcgltygi4msvf1hae2kbdbfd7xq.jpg',
     'Серый', 'КИТАЙ', 'Мужское', 'Верх: натуральная кожа, текстиль подошва: резина', 'Sail/Beta Blue-Coconut Milk');

insert into offer(price, price_currency, size, url, store_name, sku) values
    (35990, 'RUB', '8 US, 8.5 US',
     'https://sneakerhead.ru/shoes/sneakers/x-travis-scott-air-trainer-1-DR7515-200/', 'sneakerhead', 'DR7515-200'),
    (15990, 'RUB', '8 US, 8.5 US', 'https://sneakerhead.ru/shoes/sneakers/air-force-1-07-DR9867-100/', 'sneakerhead',
     'DR9867-100'),
    (8100, 'RUB', '5.5 US, 6 US, 6.5 US, 7 US, 7.5 US', 'https://sneakerhead.ru/shoes/keds/chuck-70-ox-A00538/',
     'sneakerhead', 'A00538'),
    (15990, 'RUB', '6.5 US, 7 US', 'https://sneakerhead.ru/shoes/sneakers/x-jeremy-scott-wings-4-0-GY4419/',
     'sneakerhead', 'GY4419'),
    (15990, 'RUB', '4.5 US, 5 US, 7 US', 'https://sneakerhead.ru/shoes/sneakers/joyride-env-ispa-BV4584-400/',
     'sneakerhead', 'BV4584-400'),
    (32999, 'RUB', '41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR, 45 EUR',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_travis_scott_x_air_trainer_1_dr7515_200/', 'footbox',
     'DR7515-200'),
    (16499, 'RUB', '41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_air_force_1_07_dr9867_100/', 'footbox', 'DR9867-100'),
    (13399, 'RUB', '36.5 EUR, 37 EUR, 37.5 EUR, 38 EUR, 39 EUR, 39.5 EUR, 40 EUR',
     'https://www.footboxshop.ru/catalog/obuv/kedy_converse_chuck_70_a00538/', 'footbox', 'A00538'),
    (20099, 'RUB', '42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR, 45 EUR, 45.5 EUR, 46 EUR',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_air_zoom_type_se_cv2220_001/', 'footbox', 'CV2220-001'),
    (22899, 'RUB', '40 EUR, 40.5 EUR, 41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_art_fb1875_141/', 'footbox', 'FB1875-141');



