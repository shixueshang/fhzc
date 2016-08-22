package com.fhzc.app.system.commons.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:15
 */
public class Tree implements Serializable{
    private static final long serialVersionUID = 980682543891282923L;
    private Integer id;

    private String name;

    private Integer pId;

    private boolean open = false;

    private List<Tree> children;

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private boolean leaf = false;



}
