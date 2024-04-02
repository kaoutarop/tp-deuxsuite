package com.example.hopital.repositories;

import com.example.hopital.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin finfByNom(String nom);
}
