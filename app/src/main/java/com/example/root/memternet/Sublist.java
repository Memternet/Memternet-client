package com.example.root.memternet;

import java.util.List;

/*
 * Представляет фрагмент списка, изменяемый синхронно с самим списком
 */
public class Sublist<E> {
    private final List<E> parent;
    final int from, to;
    Sublist(List<E> parent, int from, int to) {
        this.parent = parent;
        this.from = from;
        this.to = to;
    }
    E get(int i) {
        return parent.get(i);
    }
    int size() {
        return to - from;
    }
}
