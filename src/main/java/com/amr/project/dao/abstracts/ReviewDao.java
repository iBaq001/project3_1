package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.User;

import java.util.List;

public interface ReviewDao extends ReadWriteDao<Review, Long> {
   List<Review> findReviewsByItem (Item item);
   Review findReviewByUser(User user);
}
