package backend.model;

public interface Reachable {

    boolean isReachable(Point selection);
    boolean isContained(Rectangle selection);

}
