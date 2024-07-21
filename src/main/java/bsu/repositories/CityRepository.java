package bsu.repositories;

import bsu.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.city IN (:names)")
    List<City> findByNameIn(@Param("names") List<String> names);
}
