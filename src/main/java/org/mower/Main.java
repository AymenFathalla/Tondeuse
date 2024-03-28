package org.mower;

import org.mower.service.MowerService;
import org.mower.util.MowerUtil;

public class Main {
    public static void main(String[] args) {
        MowerService mowerService = new MowerService();
        MowerUtil controller = new MowerUtil(mowerService);
        controller.processInputFile("src/main/resources/input.txt");
    }
}