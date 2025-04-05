package interview.vk;

public interface Scorer<Document, User> {
    double getScore(Document doc, User user);
}