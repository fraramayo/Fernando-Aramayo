package com.xcale.WhatsApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xcale.WhatsApp.dto.MessageDTO;
import com.xcale.WhatsApp.exceptions.ResponseValidException;
import com.xcale.WhatsApp.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public ResponseEntity<List<MessageDTO>> getMessagesFromGroup(@RequestParam(name="groupId", required = true) Long groupId){
		log.info("Llamada a getMessagesFromGroup");
		return ResponseEntity.ok().body(messageService.getMessagesGroup(groupId));
	}
	
	@PostMapping("/createMessage")
	public ResponseEntity<List<MessageDTO>> createMessage(@Valid @RequestBody MessageDTO message, BindingResult result){
		log.info("Llamada a createMessage");
    	if(result.hasErrors()) {
    		throw new ResponseValidException(result);
    	}
    	messageService.createMessage(message);
		return ResponseEntity.ok().body(messageService.getMessagesGroup(message.getGroupId()));
	}
	
}
