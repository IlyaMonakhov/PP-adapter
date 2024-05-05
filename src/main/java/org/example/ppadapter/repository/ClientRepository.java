package org.example.ppadapter.repository;
import org.example.ppadapter.modelClients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClientRepository extends JpaRepository<Client, String> {


}
