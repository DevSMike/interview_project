package interview.vk.impl;

import interview.vk.DocumentRepository;
import interview.vk.RecommenderService;
import interview.vk.Scorer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class RecommenderServiceImpl<Document, User> implements RecommenderService<Document, User> {
    private final DocumentRepository<Document> repository;
    private final Scorer<Document, User> scorer;

    public RecommenderServiceImpl(Scorer<Document, User> scorer, DocumentRepository<Document> repository) {
        this.scorer = scorer;
        this.repository = repository;
    }

    @Override
    public List<Document> getTop(User user, int limit) {
        PriorityQueue<ScorerDocument> queue = new PriorityQueue<>();
        List<Document> result = new ArrayList<>();

        for (Document document : repository.getDocuments()) {
            double score = scorer.getScore(document, user);
            queue.add(new ScorerDocument(document, score));
            if (queue.size() > limit) {
                queue.poll();
            }
        }

        while (!queue.isEmpty()) {
            result.add(queue.poll().getDocument());
        }

        Collections.reverse(result);
        return result;
    }

    @Override
    public void addDocument(Document document) {
        repository.addDocument(document);
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    private class ScorerDocument implements Comparable<ScorerDocument> {
        private final Document document;
        private final double score;

        @Override
        public int compareTo(ScorerDocument o) {
            return Double.compare(this.score, o.getScore());
        }
    }
}
