package com.api.controller;


import com.api.Dto.RegistrationDto;
import com.api.entity.Registration;
import com.api.services.RegistrationService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(){
        List<RegistrationDto> registration = registrationService.getAllRegistration();
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto,
                                                BindingResult result){ if(result.hasErrors())
                                                {return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
    }
        RegistrationDto regDto = registrationService.createReg(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam Long id){
       registrationService.deleteRegById(id);
       return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable long id, @RequestBody  Registration registration
    ){
        Registration updateRegistration = registrationService.updateRegistration(id, registration);
        return new ResponseEntity<>(updateRegistration, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
        RegistrationDto dto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
