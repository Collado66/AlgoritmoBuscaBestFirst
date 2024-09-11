/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.algoritmobuscabestfirst;

/**
 *
 * @author marce
 */
import java.util.*;

class Grafo {
    private int numVertices; // Número de vértices
    private LinkedList<Aresta> adjList[]; // Lista de adjacência com custo

    // Construtor
    Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Adiciona uma aresta ao grafo com custo
    void adicionarAresta(int origem, int destino, int custo) {
        adjList[origem].add(new Aresta(destino, custo));
        adjList[destino].add(new Aresta(origem, custo)); // Para grafos não direcionados
    }

    // Realiza a busca Best-First a partir de um vértice fonte até o objetivo
    void bestFirst(int inicio, int objetivo, int[] heuristica) {
        // PriorityQueue para armazenar os nós com base na heurística
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristica));

        // Conjunto para armazenar os vértices visitados
        Set<Integer> visitados = new HashSet<>();

        // Inicializa a fila de prioridade com o vértice inicial
        pq.add(new Nodo(inicio, heuristica[inicio]));

        while (!pq.isEmpty()) {
            Nodo atual = pq.poll();
            int vertice = atual.vertice;

            // Se o vértice atual é o objetivo, imprime o resultado e retorna
            if (vertice == objetivo) {
                System.out.println("Caminho encontrado para o vértice " + objetivo);
                return;
            }

            // Marca o vértice atual como visitado
            visitados.add(vertice);

            // Processa todos os vizinhos do vértice atual
            for (Aresta aresta : adjList[vertice]) {
                if (!visitados.contains(aresta.destino)) {
                    pq.add(new Nodo(aresta.destino, heuristica[aresta.destino]));
                }
            }
        }

        System.out.println("Não há caminho do vértice " + inicio + " ao vértice " + objetivo);
    }

    // Classe interna para representar uma aresta com custo
    private static class Aresta {
        int destino;
        int custo;

        Aresta(int destino, int custo) {
            this.destino = destino;
            this.custo = custo;
        }
    }

    // Classe interna para representar um nó na fila de prioridade
    private static class Nodo {
        int vertice;
        int heuristica; // Estimativa heurística

        Nodo(int vertice, int heuristica) {
            this.vertice = vertice;
            this.heuristica = heuristica;
        }
    }
}

public class AlgoritmoBuscaBestFirst {
    public static void main(String[] args) {
        Grafo g = new Grafo(5);

        g.adicionarAresta(0, 1, 10);
        g.adicionarAresta(0, 4, 3);
        g.adicionarAresta(1, 2, 2);
        g.adicionarAresta(1, 4, 4);
        g.adicionarAresta(2, 3, 9);
        g.adicionarAresta(3, 4, 7);
        g.adicionarAresta(4, 2, 6);

        // Heurísticas (distâncias estimadas ao objetivo, devem ser ajustadas conforme o problema)
        int[] heuristicas = {7, 6, 2, 0, 1};

        System.out.println("Busca Best-First do vértice 0 ao vértice 3:");

        g.bestFirst(0, 3, heuristicas);
    }
}
