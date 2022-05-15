package com.xcale.WhatsApp.mapper;

import org.mapstruct.Mapper;

import com.xcale.WhatsApp.controller.model.entity.Message;
import com.xcale.WhatsApp.controller.model.entity.User;
import com.xcale.WhatsApp.dto.UserDTO;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	public abstract UserDTO entity2Dto(User entity);
	
	public abstract Message dto2Entity(UserDTO dto);
	
}
