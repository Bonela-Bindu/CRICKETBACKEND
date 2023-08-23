package com.dsafetech.cricket.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "images") // Use "collection" instead of "collation"
public class Image {
    @Id
    private String id;
    private String name;
    private byte[] data;
    private long brandContactNumber;
    private String brandName;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public byte[] getData() {
		return data;
	}
	public long getBrandContactNumber() {
		return brandContactNumber;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public void setBrandContactNumber(long brandContactNumber) {
		this.brandContactNumber = brandContactNumber;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Image(String id, String name, byte[] data, long brandContactNumber, String brandName) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.brandContactNumber = brandContactNumber;
		this.brandName = brandName;
	}
	public Image() {
		super();
	}

    // Getters and setters, constructors
    // ...
    
}
