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
    private final AdressRepository adressRepository;

    public AddressDto findAddressById(Long id){
        return addressMapper.addressToAddressDto(adressRepository.findById(id).orElseThrow());
    }

    public List<AddressDto> findAddressByStreet(String street) {
        return adressRepository.findAddressByStreet(street)
                .stream()
                .map(addressMapper::addressToAddressDto)
                .collect(Collectors.toList());
    }

    public Page<AddressDto> getPageOfAddresses(Integer pageNum, Integer pageSize){
      return   adressRepository
              .findAll(PageRequest.of(pageNum, pageSize))
              .map(addressMapper::addressToAddressDto);
    }

    public AddressDto creatAddress (@NotNull AddressDto address) {
        if (address.getCity().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą miasta nie może być puste");
        }
        if (address.getStreet().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą ulicy nie może być puste");
        }
        if (address.getHouseNumber().trim().isEmpty()) {
            throw new BadRequestException("Pole z numerem domu nie może być puste");
        }
        Address savedAddress = adressRepository.save(addressMapper.addressDtoToAddress(address));
        return addressMapper.addressToAddressDto(savedAddress);
    }

}
