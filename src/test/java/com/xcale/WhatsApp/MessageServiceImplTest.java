package com.xcale.WhatsApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.xcale.WhatsApp.controller.model.entity.ConversationGroup;
import com.xcale.WhatsApp.controller.model.entity.Message;
import com.xcale.WhatsApp.controller.model.entity.User;
import com.xcale.WhatsApp.controller.model.entity.UserConversationGroup;
import com.xcale.WhatsApp.repository.MessageRepository;
import com.xcale.WhatsApp.repository.UserConversationGroupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageServiceImplTest {
	
	@Autowired
	private UserConversationGroupRepository userConversationGroupRepositoryMock = Mockito.mock(UserConversationGroupRepository.class);
	@Autowired
	private MessageRepository messageRepositoryMock = Mockito.mock(MessageRepository.class);
	@Autowired
    Optional<UserConversationGroup> userConversationGroupOptional;
	private Message message;
	private List<UserConversationGroup> messagesFromGroupList = new ArrayList<UserConversationGroup>();
	
	@BeforeEach
	void setUp() {
		log.info("Execution before everything");
		message = Message.builder().message("Text for Test").messageTime(LocalDateTime.now()).userId(549388155738244L).groupId(1L).build();
		User user = User.builder().phoneNumber(549388155738244L).userName("gerardo").build();
		ConversationGroup group = ConversationGroup.builder().conversationGroupId(1L).conversationGroupName("Trabajo").build();
		UserConversationGroup userConversationGroup = UserConversationGroup.builder()
				.user(user)
				.group(group)
				.notifications(1L)
				.build();
		userConversationGroupOptional = Optional.of(userConversationGroup);
		messagesFromGroupList.add(userConversationGroup);
		
		Mockito.when(userConversationGroupRepositoryMock.findUserGroup(user.getPhoneNumber(), group.getConversationGroupId())).thenReturn(userConversationGroupOptional);
		Mockito.when(userConversationGroupRepositoryMock.findNotificationsGroup(group.getConversationGroupId())).thenReturn(messagesFromGroupList);
		Mockito.when(userConversationGroupRepositoryMock.saveAll(messagesFromGroupList)).thenReturn(messagesFromGroupList);
		Mockito.when(messageRepositoryMock.save(message)).thenReturn(message);
	}
	
	@Test
	void createMessageWithValidInfo() {
		log.info("TEST: createMessageWithValidInfo");
		Message savedMessage = null;
		List<UserConversationGroup> messagesFromGroup = null;
		Optional<UserConversationGroup> userGroup = userConversationGroupRepositoryMock.findUserGroup(549388155738244L, 1L);
		if (userGroup.isPresent()) {
			savedMessage = messageRepositoryMock.save(message);
			messagesFromGroup = userConversationGroupRepositoryMock.findNotificationsGroup(message.getGroupId());
			messagesFromGroup.stream().forEach(mesage -> mesage.setNotifications(mesage.getNotifications() + 1));
			userConversationGroupRepositoryMock.saveAll(messagesFromGroup);
		}
		assertEquals(549388155738244L, userGroup.get().getUser().getPhoneNumber());
		assertEquals(1L, userGroup.get().getGroup().getConversationGroupId());
		assertNotNull(savedMessage);
		assertEquals(549388155738244L, savedMessage.getUserId());
		assertEquals(messagesFromGroup.size(), messagesFromGroupList.size());

	}
	
	@Test
	void createMessageWithInValidInfo() {
		log.info("TEST: createMessageWithInValidInfo");
		Optional<UserConversationGroup> userGroup = userConversationGroupRepositoryMock.findUserGroup(549388155738245L, 2L);
		assertEquals(userGroup.isPresent(), false);
	}
	
	@AfterEach
	void tearDown() {
		log.info("Execution after all");
	}
	
}
