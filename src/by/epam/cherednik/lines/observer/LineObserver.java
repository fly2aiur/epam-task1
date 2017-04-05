package by.epam.cherednik.lines.observer;

import by.epam.cherednik.lines.entity.Line;
import by.epam.cherednik.lines.entity.LineProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class LineObserver implements Observer<Line> {
    private static final Logger LOG = LogManager.getLogger(LineObserver.class);

    private static LineObserver instance;
    private HashMap<Integer, LineProperties> dataHashMap;

    private LineObserver() {
        dataHashMap = new HashMap<>();
    }

    public static LineObserver getInstance() {
        if (instance == null) {
            instance = new LineObserver();
        }
        return instance;
    }

    @Override
    public void addObservable(Line observable) {
        dataHashMap.put(observable.getId(), new LineProperties());
        LOG.debug("Observer added for " + observable);
    }

    @Override
    public void removeObservable(Line observable) {
        dataHashMap.remove(observable.getId());
        LOG.debug("Observer removed for " + observable);
    }

    @Override
    public void update(Line observable) {
        dataHashMap.get(observable.getId()).update(observable);
        LOG.debug("Updating " + observable);
    }

    public LineProperties getProperties(Line observable) {
        return dataHashMap.get(observable.getId());
    }
}
