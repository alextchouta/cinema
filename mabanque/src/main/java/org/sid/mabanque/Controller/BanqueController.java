package org.sid.mabanque.Controller;

import lombok.AllArgsConstructor;
import org.sid.mabanque.entities.Compte;
import org.sid.mabanque.services.IBanqueService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BanqueController {
    private IBanqueService banqueService;

    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }

    @GetMapping("/consulterCompte/{codeCompte}")
    public Compte consulter(@PathVariable String codeCompte){
        return  banqueService.consulterCompte(codeCompte);
    }
}
