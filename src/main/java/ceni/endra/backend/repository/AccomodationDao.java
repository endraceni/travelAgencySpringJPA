package ceni.endra.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceni.endra.backend.beans.Accomodation;
import ceni.endra.backend.beans.Destination;

@Repository("accomodationDao")
public interface AccomodationDao extends JpaRepository<Accomodation, Integer>{

}
