package interview.vk.impl;

import interview.vk.DocumentRepository;

import java.util.Collections;
import java.util.List;

public class DocumentRepositoryInMemoryImpl<Document> implements DocumentRepository<Document> {

    private final List<Document> documents;

    public DocumentRepositoryInMemoryImpl(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public void addDocument(Document document) {
        documents.add(document);
    }

    @Override
    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }
}
