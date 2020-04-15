package com.assignment.jsoncomparator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Pravin
 *
 */
@Entity
@Table(name = "json")
public class Json {

	@Id
	@GeneratedValue
	@Column(name = "json_id")
	private Long jsonId;

	@Column(name = "json_obj")
	@Lob
	private byte[] jsonNode;

	public byte[] getJsonNode() {
		return jsonNode;
	}

	public void setJsonNode(byte[] jsonNode) {
		this.jsonNode = jsonNode;
	}

	public Long getJsonId() {
		return jsonId;
	}

	public void setJsonId(Long jsonId) {
		this.jsonId = jsonId;
	}
}
