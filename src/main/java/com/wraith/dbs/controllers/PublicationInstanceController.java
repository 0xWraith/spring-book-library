package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.PublicationInstanceDTO;
import com.wraith.dbs.models.PublicationInstance;
import com.wraith.dbs.services.PublicationInstanceService;
import com.wraith.dbs.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instances")
public class PublicationInstanceController
{
    private final PublicationService publicationService;
    private final PublicationInstanceService publicationInstanceService;

    @Autowired
    public PublicationInstanceController(PublicationService publicationService, PublicationInstanceService publicationInstanceService)
    {
        this.publicationService = publicationService;
        this.publicationInstanceService = publicationInstanceService;
    }

    @PostMapping("")
    public ResponseEntity<?> createInstance(@RequestBody PublicationInstanceDTO publicationInstanceDTO)
    {

        if(publicationInstanceDTO.isDTOInvalid())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(publicationService.findFirstById(publicationInstanceDTO.getPublication_id()) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        PublicationInstance publicationInstance = publicationInstanceService.save(publicationInstanceService.convertToEntity(publicationInstanceDTO));
        return new ResponseEntity<>(publicationInstanceService.convertToDTO(publicationInstance), HttpStatus.CREATED);
    }

    @GetMapping("/{instanceId}")
    public ResponseEntity<?> getInstance(@PathVariable String instanceId)
    {
        PublicationInstance publicationInstance = publicationInstanceService.findFirstById(instanceId);

        if(publicationInstance == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(publicationInstanceService.convertToDTO(publicationInstance), HttpStatus.OK);
    }

    @PatchMapping("/{instanceId}")
    public ResponseEntity<?> updateInstance(@PathVariable String instanceId, @RequestBody PublicationInstanceDTO publicationInstanceDTO)
    {

        PublicationInstance publicationInstance = publicationInstanceService.findFirstById(instanceId);

        if(publicationInstance == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(publicationInstanceDTO.getYear() > 0)
            publicationInstance.setYear(publicationInstanceDTO.getYear());

        if (publicationInstanceDTO.getType() != null)
            publicationInstance.setType(publicationInstanceDTO.getType());

        if (publicationInstanceDTO.getStatus() != null)
            publicationInstance.setStatus(publicationInstanceDTO.getStatus());

        if (publicationInstanceDTO.getPublisher() != null)
            publicationInstance.setPublisher(publicationInstanceDTO.getPublisher());

        publicationInstance = publicationInstanceService.save(publicationInstance);

        return new ResponseEntity<>(publicationInstanceService.convertToDTO(publicationInstance), HttpStatus.OK);
    }

    @DeleteMapping("/{instanceId}")
    public ResponseEntity<?> deleteInstance(@PathVariable String instanceId)
    {
        PublicationInstance publicationInstance = publicationInstanceService.findFirstById(instanceId);

        if(publicationInstance == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        publicationInstanceService.deletePublicationInstance(publicationInstance.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
