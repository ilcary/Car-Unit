package com.ilCary.CarUnit.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.ilCary.CarUnit.models.Make} entity
 */
@Data
public class MakeDTO implements Serializable {
    private final String nome;
    private final List<List<String>> modelli;
}