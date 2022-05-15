package com.xcale.WhatsApp.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.xcale.WhatsApp.controller.model.entity.Message;
import com.xcale.WhatsApp.controller.model.entity.UserConversationGroup;
import com.xcale.WhatsApp.dto.MessageDTO;
import com.xcale.WhatsApp.repository.UserConversationGroupRepository;

@Mapper(componentModel = "spring", uses = {
		UserMapper.class
})

public abstract class MessageMapper {

	@Autowired
	private UserConversationGroupRepository userConversationGroupRepository;
	
	@Mapping(target = "notifications", expression = "java(dto2notifications(entity))")
	@Mapping(target = "scheduledNumber", expression = "java(dto2scheduledNumber(entity))")
	public abstract MessageDTO entity2Dto(Message entity);
	
	public abstract Message dto2Entity(MessageDTO dto);
	
	public abstract List<MessageDTO> entity2Dto(List<Message> entity);
	
	public Long dto2notifications(Message entity) {
		Optional<UserConversationGroup> notifications = userConversationGroupRepository.findUserGroup(entity.getUserId(), entity.getGroupId());
		if(notifications.isPresent()) {
			return notifications.get().getNotifications();
		}
		return Long.valueOf(0);
	}
	
	/**
	 * I must check on my cell phone if I have programmed the number obtained as a participant in a group.
	 * The value is simulated with this method.
	 * */
	public Boolean dto2scheduledNumber(Message entity) {
		Random rd = new Random();
		return Boolean.valueOf(rd.nextBoolean());
	}
	
}
