package com.sysaid.assignment.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	private String activity;
	private Float accessibility;
	private String type;
	private Integer participants;
	private Float price;
	private String link;
	private String key;
}

