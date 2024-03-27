package com.pinsoft.shopapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;

import lombok.Data;


@Entity(name = "product")
@Table(schema = "public")
@Data

public class Product {
	@Id
	@SequenceGenerator(name = "product_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column (name = "name")
	private String name;
	private double price;
	private String explanation;
	@Lob
	@Column(name="base64image",columnDefinition = "MEDIUMBLOB")
	private String base64image;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@JsonBackReference
	private Category category;

}
