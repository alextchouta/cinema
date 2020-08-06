package org.sid.contacts.controller;

import lombok.AllArgsConstructor;
import org.sid.contacts.entities.Contact;
import org.sid.contacts.repository.ContactRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ContactRestService{

    private ContactRepository contactRepository;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<Contact> getContacts(){

        return contactRepository.findAll();
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable Long id){

        return contactRepository.findById(id).get();
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact){

        return contactRepository.save(contact);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){

         contactRepository.deleteById(id);
         return true;
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
    public Contact update(@PathVariable  Long id, @RequestBody Contact contact){

        contact.setId(id);
        return contactRepository.save(contact);
    }

/*    @RequestMapping(value = "/chercherContacts", method = RequestMethod.GET)
    public Page<Contact> chercher(@RequestParam(name="mc", defaultValue = "") String mc, @RequestParam(name="page", defaultValue = "0")int page, @RequestParam(name="size", defaultValue = "5")int size ){
        Page<Contact> chercher = contactRepository.chercher("%"+mc+"%", new PageRequest(page,size,));
        return chercher;
    }*/

}
