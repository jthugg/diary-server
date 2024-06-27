package com.github.jthugg.diary.presentation.web;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
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
