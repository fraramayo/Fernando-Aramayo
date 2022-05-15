package com.xcale.WhatsApp.controller.model.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
@Entity
@Table(name = "MESSAGES")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_ID")
	private Long messageId;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "MESSAGE_TIME")
	private LocalDateTime messageTime;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "GROUP_ID")
	private Long groupId;

	@ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", updatable = false, insertable = false)
	private User user;
	
}
