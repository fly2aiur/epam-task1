package by.epam.cherednik.lines.observer;

public interface Observer<T extends Observable> {
    void addObservable(T observable);
    void removeObservable(T observable);
    void update(T observable);
}
