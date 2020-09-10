package org.sid.fileactualite.web;

import lombok.AllArgsConstructor;
import org.sid.fileactualite.entities.Actualite;
import org.sid.fileactualite.repository.ActualiteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ActualiteRestController {

    private ActualiteRepository actualiteRepository;

    @GetMapping("/actualites")
    public Page<Actualite> pageActualite(@PageableDefault(page = 0, size = 5)
                                             @SortDefault.SortDefaults({
                                                     @SortDefault(sort = "titre", direction = Sort.Direction.DESC),
                                                     @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                             }) Pageable pageable)
    {
        return actualiteRepository.findAll(pageable);
    }

    @GetMapping("/actualites/{id}")
    public Actualite pageActualiteById(@PathVariable(name = "id") Long id )
    {
        return actualiteRepository.findById(id).get();
    }
}
