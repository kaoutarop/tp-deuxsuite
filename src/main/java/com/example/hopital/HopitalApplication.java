package com.example.hopital;

import com.example.hopital.entities.*;
import com.example.hopital.repositories.ConsultationRepository;
import com.example.hopital.repositories.MedecinRepository;
import com.example.hopital.repositories.PatientRepository;
import com.example.hopital.repositories.RendezVousRepository;
import com.example.hopital.services.IHopitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HopitalApplication {

    public static void main(String[] args) {

        SpringApplication.run(HopitalApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IHopitalService HopitalService,
                            PatientRepository patientRepository,RendezVousRepository rendezVousRepository,
                            MedecinRepository medecinRepository,
                            ConsultationRepository consultationRepository){
        return args -> {
            Stream.of("Mohammed","Kaoutar","Sanaa")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        HopitalService.savePatient(patient);

                    });
            Stream.of("Khadija","Houda","Aymen")
                    .forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        HopitalService.saveMedecin(medecin);

                    });
            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1=patientRepository.findByNom("Mohammed");

            Medecin medecin=medecinRepository.finfByNom("Khadija");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            RendezVous saveDRDV = HopitalService.saveRDV(rendezVous);
            System.out.println(saveDRDV.getId());

            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation....");
            HopitalService.saveConsultation(consultation);


        };
    }

}
