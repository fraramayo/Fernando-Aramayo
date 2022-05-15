package com.xcale.WhatsApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xcale.WhatsApp.controller.model.entity.UserConversationGroup;

@Repository
public interface UserConversationGroupRepository extends JpaRepository<UserConversationGroup, Long>{

	@Query(value = "SELECT userGroup FROM UserConversationGroup userGroup WHERE userGroup.user.phoneNumber=:userId AND userGroup.group.conversationGroupId=:groupId ")
	Optional<UserConversationGroup> findUserGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);
	
	@Query(value = "SELECT userGroup FROM UserConversationGroup userGroup WHERE userGroup.group.conversationGroupId=:groupId ")
	List<UserConversationGroup> findNotificationsGroup(@Param("groupId") Long groupId);
	
}
