package vk;

import interview.vk.DocumentRepository;
import interview.vk.RecommenderService;
import interview.vk.Scorer;
import interview.vk.impl.DocumentRepositoryInMemoryImpl;
import interview.vk.impl.RecommenderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommenderServiceImplTest {

    @AllArgsConstructor
    @Getter
    static class MockDocument {
        private double score;
    }

    static class MockScore implements Scorer<MockDocument, Integer> {
        @Override
        public double getScore(MockDocument doc, Integer user) {
            return doc.getScore();
        }
    }

    @Test
    void getTop3Elements() {
        DocumentRepository<MockDocument> repository = new DocumentRepositoryInMemoryImpl<>(new CopyOnWriteArrayList<>());
        var score = new MockScore();

        RecommenderService<MockDocument, Integer> service = new RecommenderServiceImpl<>(score, repository);

        var doc1 = new MockDocument(4);
        var doc2 = new MockDocument(8);
        var doc3 = new MockDocument(8);
        var doc4 = new MockDocument(10);
        var doc5 = new MockDocument(19);

        service.addDocument(doc4);
        service.addDocument(doc1);
        service.addDocument(doc2);
        service.addDocument(doc5);
        service.addDocument(doc3);


        List<MockDocument> result = service.getTop(1, 3);
        assertEquals(3, result.size());
        assertEquals(List.of(doc5, doc4, doc3), result);
    }


}