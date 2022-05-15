package com.xcale.WhatsApp.service;

import java.util.List;

import com.xcale.WhatsApp.controller.model.entity.Message;
import com.xcale.WhatsApp.dto.MessageDTO;

public interface MessageService {

	List<MessageDTO> getMessagesGroup(Long groupId);
	
	MessageDTO createMessage(MessageDTO message);
	
}
