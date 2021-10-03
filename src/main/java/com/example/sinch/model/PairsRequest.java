package com.example.sinch.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PairsRequest {
  List<Integer> expressions;
}
