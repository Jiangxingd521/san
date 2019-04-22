package com.ningyang.os.action.utils;

import java.util.List;

public interface TreeEntity<E> {
    String getId();

    String getParentId();

    void setChildren(List<E> children);
}
