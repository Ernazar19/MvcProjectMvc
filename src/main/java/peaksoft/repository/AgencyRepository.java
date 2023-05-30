package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.enums.HouseType;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    @Query("select u from Agency u where u.name ilike :word")
    List<Agency> searchHouse(String word);
}
