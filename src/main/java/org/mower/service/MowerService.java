package org.mower.service;

import java.util.logging.Logger;
import org.mower.model.Mower;
import org.mower.model.Position;

public class MowerService {

    private static final Logger logger = Logger.getLogger(MowerService.class.getName());

    public void move(Mower mower, String instructions, int maxX, int maxY) {
        Position currentPosition = mower.getPosition();
        char orientation = mower.getOrientation();

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'D') {
                orientation = turnRight(orientation);
            } else if (instruction == 'G') {
                orientation = turnLeft(orientation);
            } else if (instruction == 'A') {
                Position newPosition = moveForward(currentPosition, orientation);
                if (isValidPosition(newPosition, maxX, maxY)) {
                    currentPosition = newPosition; // Update current position
                    mower.setPosition(currentPosition);// Update mower's position
                } else {
                    logger.warning("Mower has quit the base, the current position is ("
                            + currentPosition.getX() + " , " + currentPosition.getY() + ")");
                }
            }
        }

        mower.setOrientation(orientation); // Update mower's orientation
    }

    private char turnRight(char orientation) {
        switch (orientation) {
            case 'N':
                return 'E';
            case 'E':
                return 'S';
            case 'S':
                return 'W';
            case 'W':
                return 'N';
            default:
                return orientation;
        }
    }

    private char turnLeft(char orientation) {
        switch (orientation) {
            case 'N':
                return 'W';
            case 'E':
                return 'N';
            case 'S':
                return 'E';
            case 'W':
                return 'S';
            default:
                return orientation;
        }
    }

    public Position moveForward(Position currentPosition, char orientation) {
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        switch (orientation) {
            case 'N':
                y++;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y--;
                break;
            case 'W':
                x--;
                break;
            default:
                break;
        }

        return new Position(x, y);
    }

    public boolean isValidPosition(Position position, int maxX, int maxY) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }
}
