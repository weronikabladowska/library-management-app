package pl.sda.librarymanagementapp.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper;
    private final RentRepository rentRepository;
//    private final RentEntityRepository rentEntityRepository;

    public RentDto findRentById(Long id) {
        return rentMapper.rentToRentDTO(rentRepository.findById(id).orElseThrow());
    }

    //ToDo : Niech Repository zwraca Optional z Rent - bo wywali NullPointera.
    public RentDto findByBookTitle(String title) {
//        return rentMapper.rentToRentDTO(rentEntityRepository.find(title));
        return null;
    }


}
