package org.example.ppadapter.repository;
import org.example.ppadapter.modelClients.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Clients, String> {



}
