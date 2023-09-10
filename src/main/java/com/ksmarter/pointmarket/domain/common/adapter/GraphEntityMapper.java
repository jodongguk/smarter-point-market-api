package com.ksmarter.pointmarket.domain.common.adapter;

interface GraphEntityMapper<G, E> {
    G toGraph(E e);

    E toEntity(G g);
}
