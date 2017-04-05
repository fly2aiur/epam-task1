package by.epam.cherednik.lines.observer;

public interface Observable<T extends Observer> {
    void notifyObserver();
    void addObserver(T observer);
    void removeObserver();
}