package vn.iotstar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.Rating;
import vn.iotstar.repository.RatingRepository;
import vn.iotstar.service.IRatingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements IRatingService {
    private final RatingRepository ratingRepository;

    @Override
    public List<Rating> findByBookId(int bookId) {
        return ratingRepository.findByBook_BookId(bookId);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
