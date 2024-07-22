package com.example.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Telefono implements Serializable{

private static final long serialVersionUID =1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String numero;

@ManyToOne(fetch = FetchType.LAZY)
    private Estudiante estudiante;

   
}