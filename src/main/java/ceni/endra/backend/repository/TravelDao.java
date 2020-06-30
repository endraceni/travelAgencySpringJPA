package ceni.endra.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Travel;

@Repository("travelDao")
public interface TravelDao extends JpaRepository<Travel, Integer>{

}
