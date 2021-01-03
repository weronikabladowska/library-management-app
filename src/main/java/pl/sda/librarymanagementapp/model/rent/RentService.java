package pl.sda.librarymanagementapp.model.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.model.mapper.RentMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper ;
    private final RentRepository rentRepository;

    public RentDTO findRentById (Long id){
        return rentMapper.rentToRentDTO(rentRepository.findById(id).orElseThrow());
    }


}
