package interview.vk;

import java.util.List;

public interface DocumentRepository<Document> {

    void addDocument(Document document);
    List<Document> getDocuments();
}
