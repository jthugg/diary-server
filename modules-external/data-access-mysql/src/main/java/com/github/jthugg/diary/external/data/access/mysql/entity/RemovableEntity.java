package com.github.jthugg.diary.external.data.access.mysql.entity;

import java.time.Instant;

public interface RemovableEntity {

    void remove();
    Instant getRemovedAt();

}
