package com.eAppInformation.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "information")
@Component
public class Information {

	@Id
	private int uniqueid;
	private String role;
	private String name;
	private String bonafide;
	private String transfer;
	private String bonafidePendingAt;
	private String transferPendingAt;

	public int getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBonafide() {
		return bonafide;
	}

	public void setBonafide(String bonafide) {
		this.bonafide = bonafide;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getBonafidePendingAt() {
		return bonafidePendingAt;
	}

	public void setBonafidePendingAt(String bonafidePendingAt) {
		this.bonafidePendingAt = bonafidePendingAt;
	}

	public String getTransferPendingAt() {
		return transferPendingAt;
	}

	public void setTransferPendingAt(String transferPendingAt) {
		this.transferPendingAt = transferPendingAt;
	}

}
