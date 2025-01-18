package at.spengergasse.springtest.presentation.dto;

import at.spengergasse.springtest.domain.Address;
import at.spengergasse.springtest.domain.Email;
import at.spengergasse.springtest.domain.Nose;
import at.spengergasse.springtest.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UserDto (Email email, String name, Role role, Address address, Nose nose){
}
