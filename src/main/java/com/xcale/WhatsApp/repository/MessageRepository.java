package com.xcale.WhatsApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xcale.WhatsApp.controller.model.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByGroupIdOrderByMessageTimeAsc(Long groupId);
	
}
