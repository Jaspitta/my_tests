package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.annotations.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class BusinessDetails {

	/**
	 * Name of the operator.
	 */
	@NotNull
	private String name;

	/**
	 * Link to the operator's website.
	 */
	private String website;

	/**
	 * Image link to the operator's logo.
	 */
	private Image logo;

}
