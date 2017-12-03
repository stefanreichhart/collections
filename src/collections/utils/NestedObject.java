package collections.utils;

import java.util.ArrayList;

public class NestedObject {

    private long id;
    private String name;
    private ArrayList<NestedObject> children = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<NestedObject> getChildren() {
        return new ArrayList<>(children);
    }

    public void setChildren(ArrayList<NestedObject> children) {
        this.children = children;
    }

    public void addChild(NestedObject object) {
        this.children.add(object);
    }

    public String toString() {
        return "<" + id + ":" + name + ":" + name.length() + ":" + children.size() + ">";
    }

}
