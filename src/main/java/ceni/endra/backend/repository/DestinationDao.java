package ceni.endra.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ceni.endra.backend.beans.Destination;

@Repository("destinationDao")
public interface DestinationDao extends JpaRepository<Destination, Integer>{

}
