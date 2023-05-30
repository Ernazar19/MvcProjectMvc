package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.enums.HouseType;
import peaksoft.exceptions.MyException;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.HouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;


    public void saveHouse(Long agencyId, House house) {
        try {
            Agency agency = agencyRepository.findById(agencyId).orElseThrow(() ->
                    new MyException("Agency with id: " + agencyId + " is not found"));
            house.setAgency(agency);
            houseRepository.save(house);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<House> getAllHouse(Long agencyId, String word) throws MyException {
        return houseRepository.getAllByAgencyId1(agencyId);
    }


    public House getHouseById(Long id) throws MyException {
        try {
            return houseRepository.findById(id).orElseThrow(() -> new MyException("not found!"));
        } catch (MyException e) {
            throw new MyException("error");
        }
    }


    public House updateHouseById(Long id, House house) {
        try {
            House house1 = houseRepository.findById(id).orElseThrow(() -> new MyException("not found!"));
            house1.setHouseType(house.getHouseType());
            house1.setDescription(house.getDescription());
            house1.setCountry(house.getCountry());
            house1.setImage(house.getImage());
            house1.setPrice(house.getPrice());
            house1.setRoom(house.getRoom());
            house1.setAddress(house.getAddress());
            houseRepository.save(house1);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void deleteHouseById(Long id) {
        try {
            houseRepository.delete(houseRepository.findById(id).orElseThrow(() -> new MyException("not found!")));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        if (ascOrDesc.equals("asc"))
            return houseRepository.sortHouseByHouseType(HouseType.APARTMENT);
        else
            return houseRepository.sortHouseByHouseTypeDec(HouseType.APARTMENT);
    }
    public List<House> searchHouse(String word) {
        return houseRepository.searchHouse(word);
    }
}