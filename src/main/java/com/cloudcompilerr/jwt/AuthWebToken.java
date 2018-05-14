package com.cloudcompilerr.jwt;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthWebToken {

	private List<String> roles;
	private String token;
	private Timestamp timeToLive;

}
