package com.universityteam.flashmemorizer.form;

import com.universityteam.flashmemorizer.dto.FillBlankCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillBlankForm extends CardReviewForm {
    private List<FillBlankCard> cardReviews = new ArrayList<>();
}