package com.github.jthugg.diary.external.data.access.mysql.entity;

import org.springframework.jdbc.support.KeyHolder;

public interface AutoIncrementedId {

    void refreshId(KeyHolder keyHolder);

}
