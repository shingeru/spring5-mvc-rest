package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendor(Long id);

}
