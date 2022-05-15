package com.xcale.WhatsApp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcale.WhatsApp.controller.model.entity.Message;
import com.xcale.WhatsApp.controller.model.entity.UserConversationGroup;
import com.xcale.WhatsApp.dto.MessageDTO;
import com.xcale.WhatsApp.exceptions.UserException;
import com.xcale.WhatsApp.mapper.MessageMapper;
import com.xcale.WhatsApp.repository.MessageRepository;
import com.xcale.WhatsApp.repository.UserConversationGroupRepository;
import com.xcale.WhatsApp.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private UserConversationGroupRepository userConversationGroupRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessageMapper messageMapper;
	
	/**
	 * @param groupId: To search for all messages in a specific group.
	 * @return: Returns a list of type MessageDTO, which is a list of all the messages of a certain group. 
	 * */
	@Transactional(readOnly = true)
	public List<MessageDTO> getMessagesGroup(Long groupId) {
		log.info("List all messages in a group: {}", groupId);
		List<Message> list = messageRepository.findByGroupIdOrderByMessageTimeAsc(groupId);
		return messageMapper.entity2Dto(list);
	}
	
	/**
	 * @param message: Receives an object of type MessageDTO, which contains attributes to determine if the user belongs to a group.
	 * @throws UserException: Returns an exception of type UserException. When you do not find a record in the Database.
	 * @return: Create a new message and return the updated list of messages to be displayed in the group view.
	 * */
	@Transactional
	public MessageDTO createMessage(MessageDTO message) {
		log.info("Create a new Message for user: {} and group: {}",message.getUserId(),message.getGroupId());
		Optional<UserConversationGroup> userGroup = userConversationGroupRepository.findUserGroup(message.getUserId(), message.getGroupId());
		if (userGroup.isPresent()) {
			message.setMessageTime(LocalDateTime.now());
			// When a new message is created, all group participants are notified that they have N+1 messages available.
			List<UserConversationGroup> messagesFromGroup = userConversationGroupRepository.findNotificationsGroup(message.getGroupId());
			messagesFromGroup.stream().forEach(mesage -> mesage.setNotifications(mesage.getNotifications() + 1));

			messageRepository.save(messageMapper.dto2Entity(message));
			userConversationGroupRepository.saveAll(messagesFromGroup);

		} else {
			log.info("The message could not be created for user: {} and group: {}",message.getUserId(),message.getGroupId());
			throw new UserException("The user or group entered is incorrect");
		}
		return message;
	}
}
