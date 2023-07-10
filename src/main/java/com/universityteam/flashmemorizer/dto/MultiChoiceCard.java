package com.universityteam.flashmemorizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiChoiceCard extends CardReview {
    private List<String> options;
    private int indexChoose;
    private int indexAnswer;
}