package pl.sda.librarymanagementapp.model.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.model.mapper.RentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper;
    private final RentRepository rentRepository;
    private final RentEntityRepository rentEntityRepository;

    public RentDTO findRentById(Long id) {
        return rentMapper.rentToRentDTO(rentRepository.findById(id).orElseThrow());
    }

    //ToDo : Niech Repository zwraca Optional z Rent - bo wywali NullPointera.
    public RentDTO findByBookTitle(String title) {
        return rentMapper.rentToRentDTO(rentEntityRepository.findByBookTitle(title));
    }


}
