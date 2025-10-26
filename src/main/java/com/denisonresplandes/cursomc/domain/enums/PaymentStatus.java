package com.denisonresplandes.cursomc.domain.enums;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PaymentStatus {

	PENDING(1, "Pendente") {
		@Override
		public boolean evaluateCancellationImpl(LocalDate paymentDate) {
			// TODO rules, if any
			return true;
		}
	},
	PAID_OFF(2, "Pago") {
		@Override
		public boolean evaluateCancellationImpl(LocalDate paymentDate) {
			long days = ChronoUnit.DAYS.between(paymentDate, LocalDate.now());
			return days <= CANCELLATION_PERIOD_IN_DAYS;
		}
	},
	CANCELED(3, "Cancelado") {
		@Override
		public boolean evaluateCancellationImpl(LocalDate paymentDate) {
			// TODO rules, if any
			return false;
		}
	};
	private static final int CANCELLATION_PERIOD_IN_DAYS = 7;
	private static final Map<Integer, PaymentStatus> CODE_MAP;
	
	static {
		CODE_MAP = Stream.of(values())
			.collect(Collectors
				.toMap(PaymentStatus::getCode, Function.identity()));
	}
	
	private final Integer code;
	private final String description;
				
	PaymentStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	/** 
     * Validação centralizada — nenhuma constante precisa verificar nulo.
     */
	public final boolean evaluateCancellation(LocalDate paymentDate) {
		Objects.requireNonNull(paymentDate, "datePayment must not be null");
		// TODO more rules, if any
		return evaluateCancellationImpl(paymentDate);
	} 
	
	/** 
     * Implementação específica por constante (data já validada)
     */
	protected abstract boolean evaluateCancellationImpl(LocalDate paymentDate);
	
	public static PaymentStatus fromCode(Integer code) {
		Objects.requireNonNull(code);
		return Optional.ofNullable(CODE_MAP.get(code))
				.orElseThrow(() -> new IllegalArgumentException("Invalid PaymentStatus code: " 
						+ code));
	}
}
