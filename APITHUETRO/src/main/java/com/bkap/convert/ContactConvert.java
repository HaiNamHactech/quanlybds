package com.bkap.convert;

import com.bkap.dto.ContactDto;
import com.bkap.entities.Contact;
import com.bkap.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public ContactDto convertToDto(Contact contactEntity) {
        ContactDto contactDto = modelMapper.map(contactEntity,ContactDto.class);
        contactDto.setUserId(contactEntity.getUser().getId());
        return contactDto;
    }

    public Contact convertToEntity(ContactDto contactDto){
        Contact contact = modelMapper.map(contactDto,Contact.class);
        contact.setUser(userRepository.findById(contactDto.getUserId()).get());
        return contact ;
    }

    public Contact convertToEntity(ContactDto contactDto, Contact contactOld ) {
        Contact contactNew = modelMapper.map(contactDto,Contact.class);
        contactOld.setContent(contactNew.getContent());
        contactOld.setFullName(contactNew.getFullName());
        contactOld.setPhone(contactNew.getPhone());
        contactOld.setUser(userRepository.findById(contactDto.getUserId()).get());
        return contactOld;
    }
}
