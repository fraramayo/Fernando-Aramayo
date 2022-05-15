package com.xcale.WhatsApp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor @Builder
public class MessageDTO {

	private Long messageId;
	@NotNull(message = "The 'message' field must must not be NULL")
	@NotEmpty(message = "The 'message' field must must not be empty")
	@Size(max = 250, message = "The 'message' field must have a maximum of 250 characters")
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime messageTime;
	@NotNull(message = "The 'userId' field must must not be NULL")
	private Long userId;
	@NotNull(message = "The 'groupId' field must must not be NULL")
	private Long groupId;
	private UserDTO user;
	private Long notifications;
	// To simulate if the group participant is scheduled on my cell phone
	private Boolean scheduledNumber;
	
}
