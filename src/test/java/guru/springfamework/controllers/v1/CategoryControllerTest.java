package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Jim";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

    }

    @Test
    public void testListCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(1L);
        category2.setName("Max");

        List<CategoryDTO> categories = Arrays.asList(category1, category2);


        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

    }



    @Test
    public void testGetCategoryByName() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);
        categoryDTO.setId(ID);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }

}