package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {


    public static final String NAME = "Exotic";
    public static final Long ID = 1L;
    VendorMapper vendorMapper = VendorMapper.INSTANCE;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testVendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(NAME, vendorDTO.getName());
        assertEquals(ID, vendorDTO.getId());

    }

    @Test
    public void testVendorDTOtoVendor() {

        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName(NAME);
        vendorDTO1.setId(ID);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO1);

        assertEquals(NAME, vendor.getName());
        assertEquals(ID, vendor.getId());

    }
}