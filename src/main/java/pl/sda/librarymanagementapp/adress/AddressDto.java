package pl.sda.librarymanagementapp.adress;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotNull
    private Long id;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String houseNumber;
    private String postCode;


}
