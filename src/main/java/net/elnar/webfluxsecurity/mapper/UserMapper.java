package net.elnar.webfluxsecurity.mapper;

import net.elnar.webfluxsecurity.dto.UserDto;
import net.elnar.webfluxsecurity.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto map(UserEntity userEntity);
	
	@InheritInverseConfiguration
	UserEntity map(UserDto userDto);
}
