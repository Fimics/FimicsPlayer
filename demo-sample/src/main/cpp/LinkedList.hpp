//
// Created by lipengju on 2019-12-04.
//




#ifndef FIMICSPLAYER_STRUCTURE_HPP
#define FIMICSPLAYER_STRUCTURE_HPP


#include <string>

template <class E>
class Node<E>{

public:
    Node<E> *next;
    E value;
};


template <class E>
class LinkedList{
    Node<E> * head;

public:

    LinkedList() {}

    LinkedList(Node<E> *head) : head(head) {}

    virtual ~LinkedList() {}

    void push(E e);

    void pop();

};


template <class E>
void LinkedList::push(E e) {

    if(head->next=NULL){

    }

    Node<E> newNode = new Node(e);
}

#endif //FIMICSPLAYER_STRUCTURE_H