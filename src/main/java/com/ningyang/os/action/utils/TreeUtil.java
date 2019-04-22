package com.ningyang.os.action.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    /**
     * 解析树形数据
     *
     * @param topId
     * @param entityList
     * @return
     * @author jianda
     * @date 2017年5月29日
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();

        //获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     *
     * @param id
     * @param entityList
     * @return
     * @author jianda
     * @date 2017年5月29日
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(String id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        String parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }

        //递归退出条件
        if (childList.size() == 0) {
            return new ArrayList<>();
        }

        return childList;
    }

    /**
     * 解析树行数据，如果没有子数据则不添加children
     *
     * @param topId
     * @param entityList
     * @param <E>
     * @return
     */
    public static <E extends TreeEntity<E>> List<E> getTreeNodeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        String parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        for (E entity : resultList) {
            entity.setChildren(getSubNodeList(entity.getId(), entityList));
        }
        return resultList;
    }

    private static <E extends TreeEntity<E>> List<E> getSubNodeList(String id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        String parentId;
        //子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildren(getSubNodeList(entity.getId(), entityList));
        }
        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
