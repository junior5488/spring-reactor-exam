package com.mitocode.service.impl;

import com.mitocode.pagination.PageSupport;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID>{

    protected abstract IGenericRepo<T, ID> getRepo(); //para q un metodo sea abstracto la clase tambien debe ser abstracta
    @Override
    public Mono<T> save(T t) {
        return getRepo().save(t);
    }

    @Override
    public Mono<T> update(ID id, T t) {
        return getRepo().findById(id).flatMap(e -> getRepo().save(t));
    }

    @Override
    public Flux<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public Mono<T> findById(ID id) {
        return getRepo().findById(id);
    }

    @Override
    public Mono<Boolean> delete(ID id) {
        return getRepo().findById(id)
                .hasElement()//retorna true si encuentra elementos, false si no
                .flatMap(result -> {
                    if(result){
                        //return repo.deleteById(id).then(Mono.just(true));
                        return getRepo().deleteById(id).thenReturn(true);
                    }else {
                        return Mono.just(false);
                    }
                });
    }

    @Override
    public Mono<PageSupport<T>> getPage(Pageable pageable) {
        return getRepo().findAll()  //Flux<T>
                .collectList()
                .map(list -> new PageSupport<>(
                        //pageNumber = 1;
                        //pageSize = 2;
                        //1,2,3,4,5,6,7,8,9,10
                        list.stream()
                                .skip(pageable.getPageNumber() * pageable.getPageSize())
                                .limit(pageable.getPageSize()).toList()
                        ,
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        list.size()
                ));
    }
}
