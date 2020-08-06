package org.sid.mabanque.services;

import lombok.AllArgsConstructor;
import org.sid.mabanque.entities.*;
import org.sid.mabanque.repositories.CompteRepository;
import org.sid.mabanque.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
@AllArgsConstructor
public class BanqueServiceImpl implements IBanqueService {

    private CompteRepository compteRepository;

    private OperationRepository operationRepository;

    @Override
    public Compte consulterCompte(String codeCpte) {
        Compte cp = compteRepository.findById(codeCpte).get();
        if (cp == null) throw new RuntimeException("Compte introuvable");
        return cp;
    }

    @Override
    public void verser(String codeCpte, double montant) {
        Compte cp = consulterCompte(codeCpte);
        cp.setSolde(cp.getSolde()+montant);

        // j enregistre l operation
        Versement versement = new Versement(new Date(), montant,cp);
        operationRepository.save(versement);
        // je mets mon compte a jour
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp = consulterCompte(codeCpte);

        double faciliteCaisse=0;

        if (cp instanceof CompteCourant){
            faciliteCaisse = ((CompteCourant) cp).getDecouvert();
        }
        if(cp.getSolde()+faciliteCaisse<montant) throw new RuntimeException("Solde insuffisant");

        cp.setSolde(cp.getSolde()-montant);
        // j enregistre l operation
        Retrait retrait = new Retrait(new Date(), montant,cp);
        operationRepository.save(retrait);
        // je mets mon compte a jour
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpteEnvoyeur, String codeCpteDestinataire, double montant) {

        retirer(codeCpteEnvoyeur,montant);
        verser(codeCpteDestinataire,montant);

    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {
        return operationRepository.listOperations(codeCpte, new QPageRequest(page,size));
    }
}
