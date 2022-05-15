package com.xcale.WhatsApp.controller.model.entity;

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
@Table(name = "USERSGROUPS")
public class UserConversationGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="PHONENUMBER", nullable=false)
	private User user;
	@ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="GROUP_ID", nullable=false)
	private ConversationGroup group;
	@Column(name = "NOTIFICATIONS")
	private Long notifications;
	
}
