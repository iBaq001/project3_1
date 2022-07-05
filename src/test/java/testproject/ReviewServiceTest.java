package testproject;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.mapper.ReviewMapper;
import com.amr.project.model.entity.Review;
import com.amr.project.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    private final ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);
    @Mock
    private ReviewDao reviewDao;
    @InjectMocks
    private ReviewServiceImpl reviewService;

    private static Review review1;
    private static Review review2;

    @BeforeAll
    static void beforeAll() {
        review1 = Review.builder()
                .id(1L)
                .dignity("im ok")
                .flaw("its ok")
                .text("hello")
                .date(Date.from(Instant.now()))
                .rating(5)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("no problem")
                .build();
        review2 = Review.builder()
                .id(2L)
                .dignity("im ok")
                .flaw("all ok")
                .text("goodbye")
                .date(Date.from(Instant.now()))
                .rating(4)
                .isModerated(true)
                .isModerateAccept(true)
                .moderatedRejectReason("no problem")
                .build();
    }

    @Test
    void findReviewByIdTest() {
        when(reviewService.findById(1L)).thenReturn(review1);
        Review review = reviewService.findById(1L);
        assertEquals("im ok", review.getDignity());
        assertEquals("its ok", review.getFlaw());
        assertEquals("hello", review.getText());
        assertEquals(5, review.getRating());
        assertTrue(review.isModerated());
        assertTrue(review.isModerateAccept());
        assertEquals("no problem", review.getModeratedRejectReason());
    }

    @Test
    void findAllReviewsTest() {
        when(reviewService.findAll()).thenReturn(Stream
                .of(review1, review2).collect(Collectors.toList()));
        assertEquals(2, reviewService.findAll().size());
    }

    @Test
    void persistReviewTest() {
        Review persist = reviewService.persist(review1);

        assertEquals(review1, persist);
        assertEquals(1, persist.getId());
        assertEquals("hello", persist.getText());
        verify(reviewDao).persist(review1);
    }
}