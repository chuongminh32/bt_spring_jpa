package vn.iotstar.service;

import vn.iotstar.entity.Rating;
import java.util.List;

public interface IRatingService {
    List<Rating> findByBookId(int bookId);
    Rating save(Rating rating);
}
