package com.impacta.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

 
public class SavingsAccount{
 

    private String branch ;
    private Long  accountNumber;
    private int  checkDigit;
    private Boolean status;
    private BigDecimal currentBalance ;
    private BigDecimal yieldCurrent ;
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(int checkDigit) {
		this.checkDigit = checkDigit;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public BigDecimal getYieldCurrent() {
		return yieldCurrent;
	}
	public void setYieldCurrent(BigDecimal yieldCurrent) {
		this.yieldCurrent = yieldCurrent;
	} 

   


}
