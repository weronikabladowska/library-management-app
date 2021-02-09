package pl.sda.librarymanagementapp.adress;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.exceptions.BadBoundaryException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public Page<AddressDto> getPageOfAddreses(@RequestParam(name = "pageNum") final Integer pageNum, @RequestParam(name = "pageSize") final Integer pageSize) {
        if (pageNum <= 0 && pageSize < 0) {
            return addressService.getPageOfAddresses(pageNum, pageSize);
        } else throw new BadBoundaryException("Page number and page size have to be positive");
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        return addressService.findAddressById(id);
    }

    @GetMapping("/street/{street}")
    public List<AddressDto> findByStreet(@PathVariable String street) {
        return addressService.findAddressByStreet(street);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto createAddress(@RequestBody AddressDto address) {
        return addressService.creatAddress(address);
    }

}
