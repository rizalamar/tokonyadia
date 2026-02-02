package com.enigmacamp.todonyadia.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
@Setter
public class PageResponseWrapper <T> {
    private List<T> data;
    private Long totalElements;
    private int totalPages;
    private int page;
    private int size;
    private boolean hasNext;

    public PageResponseWrapper(Page<T>page ) {
        this.data = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
        this.hasNext = page.hasNext();
    }
}
