package main.core;

public interface Connectable extends DiagramElement {
    void addConnection(Connection connection);
    void removeConnection(Connection connection);
}