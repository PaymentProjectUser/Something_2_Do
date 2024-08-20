package com.sysaid.assignment.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String activity;
	@EqualsAndHashCode.Include
	private Float accessibility;
	private String type;
	@EqualsAndHashCode.Include
	private Integer participants;
	@EqualsAndHashCode.Include
	private Float price;
	@EqualsAndHashCode.Include
	private String link;
	@EqualsAndHashCode.Include
	private String key;
}

