package ru.egorov.searchservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.egorov.searchservice.controller.handler.ControllerExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.yml")
@Sql(value = {"/sql/create-items.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/delete-items.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SearchTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public static final String SEARCH_URL = "/api/search";
    public static final String FIND_BY_SKU_URL = "/api/items";
    public static final String FIND_BY_STORE_URL = "/api/stores";
    public static final String SNEAKERHEAD = "sneakerhead";
    public static final String FOOTBOX = "footbox";
    public static final String QUERY_FOR_SNEAKERHEAD = "Nike Joyride ENV ISPA";
    public static final String QUERY_FOR_FOOTBOX = "Jordan Legacy 312 \"Exploration Unit\"";
    public static final String QUERY_FOR_ALL_STORES = "Air Trainer 1";
    public static final String QUERY_FOR_MANY_RESULTS = "nike";
    public static final String SKU_FOR_SNEAKERHEAD = "BV4584-400";
    public static final String SKU_FOR_FOOTBOX = "FB1875-141";
    public static final String SKU_FOR_ALL_STORES = "DR7515-200";
    public static final String FIRST_RESULT_SECOND_STORE = "content[0].offers[1]";
    public static final String FIRST_RESULT_FIRST_STORE_NAME = "content[0].offers[0].storeName";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void searchForProductAvailableInAllStores() throws Exception {
        this.mockMvc.perform(get(SEARCH_URL + "?query=" + QUERY_FOR_ALL_STORES))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(FIRST_RESULT_SECOND_STORE)
                        .exists());
    }

    @Test
    public void searchForProductOnlyAvailableOnSneakerhead() throws Exception {
        this.mockMvc.perform(get(SEARCH_URL)
                        .param("query", QUERY_FOR_SNEAKERHEAD))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(FIRST_RESULT_FIRST_STORE_NAME)
                        .value(SNEAKERHEAD))
                .andExpect(jsonPath(FIRST_RESULT_SECOND_STORE)
                        .doesNotExist());
    }

    @Test
    public void searchForProductOnlyAvailableOnFootbox() throws Exception {
        this.mockMvc.perform(get(SEARCH_URL)
                        .param("query", QUERY_FOR_FOOTBOX))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(FIRST_RESULT_FIRST_STORE_NAME)
                        .value(FOOTBOX))
                .andExpect(jsonPath(FIRST_RESULT_SECOND_STORE)
                        .doesNotExist());
    }

    @Test
    public void searchForNotAvailableProduct() throws Exception {
        this.mockMvc.perform(get(SEARCH_URL)
                        .param("query", "some string"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("content")
                        .isEmpty());
    }

    @Test
    public void searchWithManyResults() throws Exception {
        this.mockMvc.perform(get(SEARCH_URL)
                        .param("query", QUERY_FOR_MANY_RESULTS))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("content[3]")
                        .exists());
    }

    @Test
    public void findBySkuForProductAvailableInAllStores() throws Exception {
        this.mockMvc.perform(get(FIND_BY_SKU_URL + "/" + SKU_FOR_ALL_STORES))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("offers[0]")
                        .exists());
    }

    @Test
    public void findBySkuForProductOnlyAvailableOnSneakerhead() throws Exception {
        this.mockMvc.perform(get(FIND_BY_SKU_URL + "/" + SKU_FOR_SNEAKERHEAD))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("offers[0].storeName")
                        .value(SNEAKERHEAD))
                .andExpect(jsonPath("offers[1]")
                        .doesNotExist());
    }

    @Test
    public void findBySkuForProductOnlyAvailableOnFootbox() throws Exception {
        this.mockMvc.perform(get(FIND_BY_SKU_URL +"/" + SKU_FOR_FOOTBOX))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("offers[0].storeName")
                        .value(FOOTBOX))
                .andExpect(jsonPath("offers[1]")
                        .doesNotExist());
    }

    @Test
    public void findByStoreWithPaginationForSneakerhead() throws Exception {
        this.mockMvc.perform(get(FIND_BY_STORE_URL + "/" + SNEAKERHEAD)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("content[1]")
                        .exists())
                .andExpect(jsonPath("content[2]")
                        .doesNotExist());
    }

    @Test
    public void findByStoreWithPaginationForFootbox() throws Exception {
        this.mockMvc.perform(get(FIND_BY_STORE_URL + "/" + FOOTBOX)
                        .param("page", "1")
                        .param("size", "2"))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("content[1]")
                        .exists())
                .andExpect(jsonPath("content[2]")
                        .doesNotExist());
    }

    @Test
    public void findByStoreForNotSupportedStore() throws Exception {
        this.mockMvc.perform(get(FIND_BY_STORE_URL + "/some_string"))
                .andExpect(status()
                        .isNotFound())
                .andExpect(jsonPath("message")
                        .value(ControllerExceptionHandler.STORE_NOT_SUPPORTED));
    }

    @Test
    public void findByStoreWithPaginationForNotSupportedStore() throws Exception {
        this.mockMvc.perform(get(FIND_BY_STORE_URL + "/some_string")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status()
                        .isNotFound())
                .andExpect(jsonPath("message")
                        .value(ControllerExceptionHandler.STORE_NOT_SUPPORTED));
    }
}
