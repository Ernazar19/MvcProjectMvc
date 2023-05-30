package peaksoft.service;

import jakarta.persistence.UniqueConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.exceptions.MyException;
import peaksoft.repository.AgencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyService {
   private final AgencyRepository agencyRepository;

  /* *//* public Boolean agencyNameValidate(String name) {
        for (Agency agency : agencyRepository.findAll()) {
            if (agency.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }*//*
    void saveAgency(Agency agency) throws MyException {
        if (agencyNameValidate(agency.getName())) {
            throw new MyException("An agency with this name already exists");
        }
        agencyRepository.save(agency);
    }*/
    public void saveAgency(Agency agency){
        agencyRepository.save(agency);
    }

    public List<Agency> getAllAgency() {
        return agencyRepository.findAll();
    }


    public Agency getById(Long id) {
        try {
            return agencyRepository.findById(id).orElseThrow(()->new MyException("not found"));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateAgencyById(Long id, Agency agency) throws MyException {
        Agency agency1 = agencyRepository.findById(id).orElseThrow(() -> new MyException("not found"));
        agency1.setName(agency.getName());
        agency1.setEmail(agency.getEmail());
        agency1.setCountry(agency.getCountry());
        agency1.setPhoneNumber(agency.getPhoneNumber());
        agency1.setImageLink(agency.getImageLink());
        agencyRepository.save(agency1);
    }


    public void deleteById(Long id) {
        agencyRepository.deleteById(id);
    }

   
    public List<Agency> searchAgency(String word) {
        return agencyRepository.searchHouse(word);
    }
}



