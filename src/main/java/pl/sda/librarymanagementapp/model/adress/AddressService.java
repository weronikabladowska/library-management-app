package pl.sda.librarymanagementapp.model.adress;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.domain.user.Address;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.model.mapper.AdressMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {

    private final AdressMapper adressMapper;
    private final AdressRepository adressRepository;

    public AddressDto findAdressById (Long id){
        return adressMapper.adressToAdrresDTO(adressRepository.findById(id).orElseThrow());
    }

    public List<AddressDto> findAdressByStreet (String street) {
        return adressRepository.findAdressByStreet(street)
                .stream()
                .map(adressMapper::adressToAdrresDTO)
                .collect(Collectors.toList());
    }

    public Page<AddressDto> getPageOfAdresses (Integer pageNum, Integer pageSize){
      return   adressRepository
              .findAll(PageRequest.of(pageNum, pageSize))
              .map(adressMapper::adressToAdrresDTO);
    }

    public AddressDto creatAddress (@NotNull Address address) {
        if (address.getCity().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą miasta nie może być puste");
        }
        if (address.getStreet().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą ulicy nie może być puste");
        }
        if (address.getHouseNumber().trim().isEmpty()) {
            throw new BadRequestException("Pole z numerem domu nie może być puste");
        }
        return adressMapper.adressToAdrresDTO(address);
    }

}
