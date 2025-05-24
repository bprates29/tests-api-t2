package org.example.dtos;

import com.google.gson.annotations.JsonAdapter;

public record AgePrediction(
        String name,
        int age,
        int count
) {}
