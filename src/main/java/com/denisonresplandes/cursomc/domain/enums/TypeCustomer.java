package com.denisonresplandes.cursomc.domain.enums;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeCustomer {

	NATURAL_PERSON(1, "Pessoa Física"),	
	LEGAL_ENTITY(2, "Pessoa Jurídica");

	private static final Map<Integer, TypeCustomer> CODE_MAP;
	
	static {
		CODE_MAP = Stream.of(values())
			.collect(Collectors
				.toMap(TypeCustomer::getCode, Function.identity()));
	}
	
	private final Integer code;
	private final String description;
	
	private TypeCustomer(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static TypeCustomer fromCode(Integer code) {
		Objects.requireNonNull(code, "code is null");
		return Optional.ofNullable(CODE_MAP.get(code))
			.orElseThrow(() -> new IllegalArgumentException("code is invalid: " + 
					code));
	}
}
