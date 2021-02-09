package pl.sda.librarymanagementapp.model.addres;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.librarymanagementapp.adress.AddressesRepository;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AddressGetIntegrationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AddressesRepository addressesRepository;

    ObjectMapper objectMapper = new ObjectMapper();



}
