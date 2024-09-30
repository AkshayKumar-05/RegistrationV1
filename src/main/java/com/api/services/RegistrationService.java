package com.api.services;

import com.api.Dto.RegistrationDto;
import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    public List<RegistrationDto> getAllRegistration() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createReg(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;

    }

    public void  deleteRegById(Long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration saved = registrationRepository.save(r);
        return saved;
    }

    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration reg = new Registration();
//        reg.setName(registrationDto.getName());
//        reg.setEmail(registrationDto.getEmail());
//        reg.setMobile(registrationDto.getMobile());
        return registration;
    }

    RegistrationDto mapToDto(Registration registration){
        RegistrationDto regdto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto regdto = new RegistrationDto();
//        regdto.setName(registration.getName());
//        regdto.setEmail(registration.getEmail());
//        regdto.setMobile(registration.getMobile());
        return regdto;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Record Not Found")
                );
        return mapToDto(registration);
    }
}
