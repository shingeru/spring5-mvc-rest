package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import lombok.Lombok;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public static final String NAME = "Bill";
    public static final long ID = 1L;

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void categoryTocategoryDTOTest() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}