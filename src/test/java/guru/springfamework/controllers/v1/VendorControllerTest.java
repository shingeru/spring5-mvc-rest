package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {


    public static final String NAME = "Billy";
    public static final long ID = 1L;
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void getVendors() throws Exception {
        //given
        List<VendorDTO> vendorDTOS = Arrays.asList(new VendorDTO(), new VendorDTO());

        when(vendorService.getVendors()).thenReturn(vendorDTOS);

        mockMvc.perform(get("/api/v1/vendors/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setId(ID);

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get("/api/v1/vendors/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }


    @Test
    public void testCreateNewVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
//        vendorDTO.setId(ID);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setId(vendorDTO.getId());
        returnDTO.setVendorUrl("/api/v1/vendors/1");

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/vendors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO.getName())))
//                .andExpect(jsonPath("$.id", equalTo(vendorDTO.getId())))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));



    }
}