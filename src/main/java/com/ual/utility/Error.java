package com.ual.utility;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
	private String errMsg;
	private int errCode;
	private LocalDate time;
}
