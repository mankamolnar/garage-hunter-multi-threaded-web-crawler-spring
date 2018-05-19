package com.standapp.parking.slot.analyser.persistence.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "garages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DbGarage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "externalId")
  private long externalId;

  @Column(name = "startDate")
  private Date startDate;

  @Column(name = "endDate")
  private Date endDate;

  @Column(name = "lastModified")
  private Date lastModified;

  @Column(name = "district")
  private String district;

  @Column(name = "street")
  private String street;

  @Column(name = "area")
  private String area;

  @Column(name = "price")
  private String price;

  @Column(name = "img")
  private String img;
}
