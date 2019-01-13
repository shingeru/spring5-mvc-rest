package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    public final VendorMapper vendorMapper;
    public final VendorRepository vendorRepository;


    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl("/api/v1/vendors/" + vendorDTO.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendor -> {
          VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
            vendorDTO.setVendorUrl("/api/v1/vendors/" + vendorDTO.getId());
            return vendorDTO;

        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnVendorDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO savedVendro = vendorMapper.vendorToVendorDTO(savedVendor);
        savedVendro.setVendorUrl("/api/v1/vendors/" + vendor.getId());
        return savedVendro;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return saveAndReturnVendorDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return saveAndReturnVendorDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
