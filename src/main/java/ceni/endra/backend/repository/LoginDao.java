package ceni.endra.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Login;

@Repository("loginDao")
public interface LoginDao extends JpaRepository<Login, Integer>{

}
