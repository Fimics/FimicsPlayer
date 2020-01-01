//
// Created by lipengju on 2019-12-04.
//




#ifndef FIMICSPLAYER_STRUCTURE_HPP
#define FIMICSPLAYER_STRUCTURE_HPP

template <class E>
class Node{

    Node<E> *next;
    E value;

public:

    Node(E value,Node<E> *next)  {
        this->value=value;
        this->next=next;
    }
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

    void forEach();

};


template <class E>
void LinkedList::push(E e) {

//    Node<E> *newNode =  new Node(e,NULL);
//    if(!head){
//        head=newNode;
//    }

}

template <class E>
void LinkedList::forEach() {

}

#endif //FIMICSPLAYER_STRUCTURE_H