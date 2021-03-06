package pl.sda.librarymanagementapp.adress;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {

    private final AddressMapper addressMapper;
    private final AddressesRepository addressesRepository;

    public AddressDto findAddressById(Long id){
        return addressMapper.addressToAddressDto(addressesRepository.findById(id).orElseThrow());
    }

    public List<AddressDto> findAddressByStreet(String street) {
        return addressesRepository.findAddressByStreet(street)
                .stream()
                .map(addressMapper::addressToAddressDto)
                .collect(Collectors.toList());
    }

    public Page<AddressDto> getPageOfAddresses(Integer pageNum, Integer pageSize){
      return   addressesRepository
              .findAll(PageRequest.of(pageNum, pageSize))
              .map(addressMapper::addressToAddressDto);
    }

    public AddressDto creatAddress (@NotNull AddressDto address) {
        if (address.getCity().trim().isEmpty()) {
            throw new BadRequestException("City name cannot be empty");
        }
        if (address.getStreet().trim().isEmpty()) {
            throw new BadRequestException("Street name cannot be empty");
        }
        if (address.getHouseNumber().trim().isEmpty()) {
            throw new BadRequestException("House number cannot be empty");
        }
        Address savedAddress = addressesRepository.save(addressMapper.addressDtoToAddress(address));
        return addressMapper.addressToAddressDto(savedAddress);
    }

}
