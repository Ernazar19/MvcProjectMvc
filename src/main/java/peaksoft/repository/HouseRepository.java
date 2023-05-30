package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.House;
import peaksoft.enums.HouseType;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
    @Query("select h from House h where h.agency.id = ?1")
    List<House> getAllByAgencyId1(Long id);

    @Query("select h from House h where h.houseType = ?1")
    List<House> sortHouseByHouseType(HouseType houseType);

    @Query("select h from House h where h.houseType = ?1 order by h.houseType desc")
    List<House> sortHouseByHouseTypeDec(HouseType houseType);

    @Query("select h from House h where h.address = ?1")
    List<House> searchHouse(String word);
}
