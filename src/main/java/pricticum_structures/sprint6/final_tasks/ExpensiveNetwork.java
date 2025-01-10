package pricticum_structures.sprint6.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
yandex contest: https://contest.yandex.ru/contest/25070/run-report/131217724/

Требовалось реализовать алгоритм поиска максимального остовного дерева

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Создается множество еще непосещенных вершин (это все вершины графа изначально), множество посещенных вершин (изначально пусто).
Также кучу, которая содержит ребра графа, которые мы будем рассматривать. Изнчаально мы выполянем функцию addVertex(): удаляем ее
из множества непосещенных вершин; добавляем во множество посещенных; добавляем в кучу все ее ребра, конечная вершина которых
еще находится во множестве непосещенных вершин. Пока множество непосещеннных вершин не пустое и куча с ребрами не пустая,
мы получаем ребро с максимальным весом из кучи, удаляем его. Если конец этого ребра еще не посещен, то мы добавляем вершину в
наше MST, а также выполянем функцию addVertex() для конца этого ребра.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению является корректным, потому что во время выполнения функции findMst() будут рассмотрены всевозможные
ребра максимального веса из одной вершины в другую => будет собрано MST => будет найден вес этого MST.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Пусть n - кол-во вершин; m - кол-во ребер
Функция findMst() включает в себя цикл while , который работает за O(n), на каждой итерации выполняется
addVertex() за O(log(m)) и heap.popMax() за O(log(m))  = O(n * (O(log(m) + O(log(m))
==> O(n * log(m));

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 Пусть n - кол-во вершин; m - кол-во ребер
 Граф хранится в виде списка смежности : O( n + m)
 Доп множества посещенных && непосещенных вершин : O(n) + O(n)
 Куча с ребрами: O(m)

 ==> пространственная сложность O(n + m);
 */
class Heap {
    List<ExpensiveNetwork.Edge> heap;

    public Heap() {
        heap = new ArrayList<>();
        heap.add(new ExpensiveNetwork.Edge(0, 0, -1));
    }

    public boolean isEmpty() {
        return heap.size() == 1;
    }

    public void addHeap(ExpensiveNetwork.Edge edge) {
        heap.add(edge);
        shiftUp(heap.size() - 1);
    }

    public ExpensiveNetwork.Edge popMax() {
        ExpensiveNetwork.Edge result = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        shiftDown(1);
        return result;
    }

    private void shiftUp(int index) {
        if (index <= 1) {
            return;
        }

        int parentIndex = index / 2;
        if (heap.get(parentIndex).weight < heap.get(index).weight) {
            Collections.swap(heap, parentIndex, index);
            shiftUp(parentIndex);
        }
    }

    private void shiftDown(int index) {
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex >= heap.size()) {
            return;
        }

        int largestIndex = leftIndex;
        if (rightIndex < heap.size() && heap.get(rightIndex).weight > heap.get(leftIndex).weight) {
            largestIndex = rightIndex;
        }

        if (heap.get(largestIndex).weight > heap.get(index).weight) {
            Collections.swap(heap, largestIndex, index);
            shiftDown(largestIndex);
        }
    }
}

public class ExpensiveNetwork {
    static final String WRONG_ANSWER = "Oops! I did it again";

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        int n;
        int m;
        int startVertex = 1;

        Map<Integer, List<Edge>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            if (m == 0) {
                fillGraphBase(graph, n);
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(edge.nextToken());
                int end = Integer.parseInt(edge.nextToken());
                int weight = Integer.parseInt(edge.nextToken());

                List<Edge> startEdges = graph.getOrDefault(start, new ArrayList<>());
                startEdges.add(new Edge(start, end, weight));
                List<Edge> endEdges = graph.getOrDefault(end, new ArrayList<>());
                endEdges.add(new Edge(end, start, weight));

                graph.put(start, startEdges);
                graph.put(end, endEdges);

                if (startVertex == 1) {
                    startVertex = start;
                }
            }
        }

        List<Edge> mst = new ArrayList<>();
        int result = findMst(graph, mst, startVertex);

        if (result == -1) {
            System.out.print(WRONG_ANSWER);
        } else {
            System.out.print(result);
        }

    }

    private static void fillGraphBase(Map<Integer, List<Edge>> graph, int n) {
        while (n > 0) {
            graph.put(n--, new ArrayList<>());
        }
    }

    private static int findMst(Map<Integer, List<Edge>> graph, List<Edge> mst, int startVertex) {
        Set<Integer> notAdded = new HashSet<>(graph.keySet());
        Set<Integer> added = new HashSet<>();
        Heap edges = new Heap();

        addVertex(startVertex, edges, added, notAdded, graph.get(startVertex));

        while (!notAdded.isEmpty() && !edges.isEmpty()) {
            Edge maxEdge = edges.popMax();
            if (notAdded.contains(maxEdge.end)) {
                mst.add(maxEdge);
                addVertex(maxEdge.end, edges, added, notAdded, graph.get(maxEdge.end));
            }
        }

        if (!notAdded.isEmpty()) {
            return -1;
        }

        return findMstWeight(mst);
    }

    private static int findMstWeight(List<Edge> mst) {
        int maxWeight = 0;
        for (Edge edge : mst) {
            maxWeight += edge.weight;
        }

        return maxWeight;
    }

    private static void addVertex(int v, Heap edges, Set<Integer> added, Set<Integer> notAdded,
                                  List<Edge> vertexEdges) {
        added.add(v);
        notAdded.remove(v);

        for (Edge edge : vertexEdges) {
            if (notAdded.contains(edge.end)) {
                edges.addHeap(edge);
            }
        }
    }

}
