package com.github.jthugg.diary.presentation.web;

import lombok.Builder;

import java.util.List;

@Builder
public record PagedContent<T>(
        List<T> contents,
        int page,
        int hasPrevious,
        int hanNext,
        int pageSize,
        int numberOfContent,
        int lastPage
) {}
