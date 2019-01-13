package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    public static final String NAME = "Exotic FRT.";
    public static final Long ID = 1L;
    public static final String VENDOR_URL = "/api/v1/vendors/1";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }


    @Test
    public void getVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOS = vendorService.getVendors();

        //then
        assertEquals(vendors.size(), vendorDTOS.size());
    }

    @Test
    public void getVendorById() {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        Optional<Vendor> optionalVendor = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(optionalVendor);

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(ID, vendorDTO.getId());
    }


    @Test
    public void testCreateNewVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VENDOR_URL);


        Vendor savedVendor = new Vendor();
        savedVendor.setId(vendorDTO.getId());
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedVendroDTO = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(ID, savedVendroDTO.getId());
        assertEquals(NAME, savedVendroDTO.getName());
        assertEquals(VENDOR_URL, savedVendroDTO.getVendorUrl());
    }

    @Test
    public void testUpdateVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VENDOR_URL);


        Vendor updatedVendor = new Vendor();
        updatedVendor.setId(vendorDTO.getId());
        updatedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(updatedVendor);

        //when
        VendorDTO updatedVendroDTO = vendorService.updateVendor(ID, vendorDTO);

        //then
        assertEquals(ID, updatedVendroDTO.getId());
        assertEquals(NAME, updatedVendroDTO.getName());
        assertEquals(VENDOR_URL, updatedVendroDTO.getVendorUrl());
    }

    @Test
    public void testPatchVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl(VENDOR_URL);


        Vendor patchedVendor = new Vendor();
        patchedVendor.setId(vendorDTO.getId());
        patchedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(patchedVendor);

        //when
        VendorDTO patchedVendroDTO = vendorService.patchVendor(ID, vendorDTO);

        //then
        assertEquals(ID, patchedVendroDTO.getId());
        assertEquals(NAME, patchedVendroDTO.getName());
        assertEquals(VENDOR_URL, patchedVendroDTO.getVendorUrl());

    }

}