package com.example.hopital.services;

import com.example.hopital.entities.Consultation;
import com.example.hopital.entities.Medecin;
import com.example.hopital.entities.Patient;
import com.example.hopital.entities.RendezVous;

public interface IHopitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

}
