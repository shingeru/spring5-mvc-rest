package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Data
public class VendorDTO {

    private Long id;
    private String name;

    @JsonAlias("vendor_url")
    private String vendorUrl;


}
