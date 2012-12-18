package com.balanced;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.balanced.core.Resource;
import com.balanced.core.ResourceQuery;
import com.balanced.errors.CannotCreate;
import com.balanced.errors.HTTPError;

public class BankAccount extends Resource {
	
	public Date created_at;
	public String name;
	public String account_number;
	public String routing_number;
	public String type;
	public String fingerprint;
	public String bank_name;
	public Map<String, String> meta;
	
	public BankAccount() {
		super();
	}
	
	public BankAccount(Map<String, Object> payload) {
		super(payload);
	}
	
	public static ResourceQuery<Marketplace> query() {
		return new ResourceQuery<Marketplace>(
				Marketplace.class,
				"/v" + Settings.VERSION + "/bank_accounts");
	}
	
	@Override
	public void save() throws HTTPError {
		if (id == null && uri == null) {
			uri = "/v" + Settings.VERSION + "bank_accounts";
		}
		super.save();
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("name", name);
		payload.put("account_number", account_number);
		payload.put("routing_number", routing_number);
		payload.put("type", type);
		return payload;
	}

	@Override
	public void deserialize(Map<String, Object> payload) {
		super.deserialize(payload);
		created_at = deserializeDate((String) payload.get("created_at"));
		name = (String) payload.get("name");
		account_number = (String) payload.get("account_number");
		routing_number = (String) payload.get("routing_number");
		type = (String) payload.get("type");
		fingerprint = (String) payload.get("fingerprint");
		bank_name = (String) payload.get("bank_name");
		meta = (Map<String, String>) payload.get("meta");
	}
}