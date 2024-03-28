package org.mower.util;

import org.mower.model.Mower;
import org.mower.model.Position;
import org.mower.service.MowerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MowerUtil {
    private final MowerService mowerService;

    public MowerUtil(MowerService mowerService) {
        this.mowerService = mowerService;
    }

    public void processInputFile(String fileName) {
        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            int maxX = scanner.nextInt();
            int maxY = scanner.nextInt();
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] initialPosition = scanner.nextLine().split(" ");
                int x = Integer.parseInt(initialPosition[0]);
                int y = Integer.parseInt(initialPosition[1]);
                char orientation = initialPosition[2].charAt(0);

                String instructions = scanner.nextLine();

                Mower mower = new Mower(new Position(x, y), orientation);
                mowerService.move(mower, instructions, maxX, maxY);

                System.out.println(mower.getPosition().getX() + " " +
                        mower.getPosition().getY() + " " +
                        mower.getOrientation());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found!");
        } catch (NoSuchElementException e) {
            System.err.println("The file is empty!");
        }
    }
}
