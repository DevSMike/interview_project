import old.interview.InterviewClass;
import old.interview.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewTest {
    private InterviewClass interviewClass;

    @Test
    public void testMethodReturnResultShouldBeTest() {
        assertEquals("Test", interviewClass.method());
    }


    //  @Test
//    public void testGetBucket() {
//
//        Map<Integer, Integer> users = Map.of(1, 20, 2, 45);
//        interviewClass = new old.interview.InterviewClass(users);
//
//        List<old.interview.Item> items = new ArrayList<>(List.of(new old.interview.Item(1, 100, 100),
//                new old.interview.Item(2, 850, 850)));
//
//        List<old.interview.Item> finalItem = interviewClass.getBucket(1, items);
//        assertEquals(80, finalItem.get(0).getFinalPrice());
//    }

    @Test
    public void testGetBucketDouble() {

        Map<Integer, Integer> users = Map.of(1, 20, 2, 45);
        interviewClass = new InterviewClass(users);

        List<Item> items = new ArrayList<>(List.of(new Item(1, 100, BigDecimal.valueOf(100)),
                new Item(2, 850, BigDecimal.valueOf(850))));

        List<Item> finalItem = interviewClass.getBucket(2, items);
        BigDecimal test = new BigDecimal("467.50");
        BigDecimal value = finalItem.get(1).getFinalPrice();
        assertEquals(test, value);
    }

//    @Test
//    public void testGetBucketShouldBeStartBucket() {
//
//        Map<Integer, Integer> users = Map.of(1, 20, 2, 45);
//        interviewClass = new old.interview.InterviewClass(users);
//
//        List<old.interview.Item> items = new ArrayList<>(List.of(new old.interview.Item(1, 100, 100),
//                new old.interview.Item(2, 850, 850)));
//
//        List<old.interview.Item> finalItem = interviewClass.getBucket(3, items);
//        assertEquals(items.get(0).getFinalPrice(), finalItem.get(0).getFinalPrice());
//    }
}
