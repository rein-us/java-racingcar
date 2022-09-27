package racingcar.domain;

import racingcar.domain.exception.AlreadyRaceFinishedException;
import racingcar.view.Watcher;

public class Racing {
    private final Cars cars;
    private final int tryCount;
    private int currentTryCount;

    private Racing(Cars cars, int tryCount) {
        this.cars = cars;
        this.tryCount = tryCount;
    }

    public void race(Watcher watcher) {
        if (isFinish()) {
            throw new AlreadyRaceFinishedException();
        }
        currentTryCount++;
        watcher.notify(cars.move());
    }
    
    public boolean isFinish() {
        return currentTryCount >= tryCount;
    }
    
    public static Racing of(int carCount, int tryCount) {
        if (carCount <=0 || tryCount < 1) {
            throw new IllegalArgumentException("Number of cars is greater than 0 and trial count must be greater than or equal to 1.");
        }
        return new Racing(Cars.create(carCount), tryCount);
    }
}
