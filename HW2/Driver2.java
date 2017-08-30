import java.util.*;
/**
 * Test Driver for Homework #1: Axis & Allies 1942, 2nd Edition
 * 
 * @author Jordan Cartwright
 * @version 2.0
 */
public class Driver2
{
    /**
     * Main program for our Driver 2.0
     * 
     * @param args  Command Line Arguments
     */
    public static void main(String args[])
    {
        //         //hardcode of Lim tests HW1 with old remove method
        //         System.out.println("====================Case: 1====================");
        //         System.out.println("===Attackers===");
        //         System.out.println("1 - 0 - 0 - 0 - 0");
        //         System.out.println("===Defenders===");
        //         System.out.println("1 - 0 - 0 - 0 - 0");
        //         AABattleSimulator simulator1 = new AABattleSimulator(new int[]{1,0,0,0,0},
        //                 new int[]{1,0,0,0,0}, 1);
        //         simulator1.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.248726666666666");
        //         System.out.println("Optimized Output: " + simulator1.run());
        //         System.out.println("====================Case: 2====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("10 - 0 - 10 - 0 - 0");
        //         System.out.println("===Defenders===");
        //         System.out.println("20 - 0 - 0 - 0 - 0");
        //         AABattleSimulator simulator2 = new AABattleSimulator(new int[]{10,0,10,0,0},
        //                 new int[]{20,0,0,0,0}, 1);
        //         simulator2.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.72047");
        //         System.out.println("Optimized Output: " + simulator2.run());
        //         System.out.println("====================Case: 3====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("10 - 10 - 0 - 0 - 0");
        //         System.out.println("===Defenders===");
        //         System.out.println("15 - 0 - 0 - 0 - 0");
        //         AABattleSimulator simulator3 = new AABattleSimulator(new int[]{10,10,0,0,0},
        //                 new int[]{15,0,0,0,0}, 1);
        //         simulator3.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.969886666666666");
        //         System.out.println("Optimized Output: " + simulator3.run());
        //         System.out.println("====================Case: 4====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("0 - 0 - 0 - 5 - 5");
        //         System.out.println("===Defenders===");
        //         System.out.println("1 - 0 - 0 - 0 - 0");
        //         AABattleSimulator simulator4 = new AABattleSimulator(new int[]{0,0,0,5,5},
        //                 new int[]{1,0,0,0,0}, 1);
        //         simulator4.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0");
        //         System.out.println("Optimized Output: " + simulator4.run());
        //         System.out.println("====================Case: 5====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("1 - 2 - 3 - 4 - 5");
        //         System.out.println("===Defenders===");
        //         System.out.println("5 - 4 - 3 - 2 - 1");
        //         AABattleSimulator simulator5 = new AABattleSimulator(new int[]{1,2,3,4,5},
        //                 new int[]{5,4,3,2,1}, 1);
        //         simulator5.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.0378533333333333");
        //         System.out.println("Optimized Output: " + simulator5.run());
        //         System.out.println("====================Case: 6====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("3 - 0 - 2 - 1 - 1");
        //         System.out.println("===Defenders===");
        //         System.out.println("3 - 1 - 2 - 1 - 0");
        //         AABattleSimulator simulator6 = new AABattleSimulator(new int[]{3,0,2,1,1},
        //                 new int[]{3,1,2,1,0}, 1);
        //         simulator6.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.179333333333333");
        //         System.out.println("Optimized Output: " + simulator6.run());
        //         System.out.println("====================Case: 7====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("5 - 3 - 2 - 0 - 0");
        //         System.out.println("===Defenders===");
        //         System.out.println("4 - 3 - 2 - 2 - 0");
        //         AABattleSimulator simulator7 = new AABattleSimulator(new int[]{5,3,2,0,0},
        //                 new int[]{4,3,2,2,0}, 1);
        //         simulator7.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.09927");
        //         System.out.println("Optimized Output: " + simulator7.run());
        //         System.out.println("====================Case: 8====================");
        //         //========================================================================
        //         System.out.println("===Attackers===");
        //         System.out.println("10 - 10 - 0 - 0 - 0");
        //         System.out.println("===Defenders===");
        //         System.out.println("20 - 0 - 0 - 0 - 0");
        //         AABattleSimulator simulator8 = new AABattleSimulator(new int[]{10,10,0,0,0,},
        //                 new int[]{20,0,0,0,0}, 1);
        //         simulator8.setSimulationLength(300000);
        //         System.out.println("Expected Output : 0.497873333333333");
        //         System.out.println("Optimized Output: " + simulator8.run());
        //         System.out.println("===============================================");

        //hardcode of Lim testHW1 with NEW remove method
        System.out.println("====================Case: 1====================");
        System.out.println("===Attackers===");
        System.out.println("10 - 3 - 0 - 0 - 0");
        System.out.println("===Defenders===");
        System.out.println("10 - 0 - 0 - 1 - 0");
        AABattleSimulator simulator01 = new AABattleSimulator(new int[]{10,3,0,0,0},
                new int[]{10,0,0,1,0}, 1);
        simulator01.setSimulationLength(300000);
        System.out.println("Expected Output : 0.478020333");
        System.out.println("Optimized Output: " + simulator01.run2());
        System.out.println("====================Case: 2====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("10 - 0 - 10 - 0 - 0");
        System.out.println("===Defenders===");
        System.out.println("20 - 0 - 0 - 0 - 0");
        AABattleSimulator simulator02 = new AABattleSimulator(new int[]{10,0,10,0,0},
                new int[]{20,0,0,0,0}, 1);
        simulator02.setSimulationLength(300000);
        System.out.println("Expected Output : 0.72047");
        System.out.println("Optimized Output: " + simulator02.run2());
        System.out.println("====================Case: 3====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("10 - 10 - 0 - 0 - 0");
        System.out.println("===Defenders===");
        System.out.println("15 - 0 - 0 - 0 - 0");
        AABattleSimulator simulator03 = new AABattleSimulator(new int[]{10,10,0,0,0},
                new int[]{15,0,0,0,0}, 1);
        simulator03.setSimulationLength(300000);
        System.out.println("Expected Output : 0.969886666666666");
        System.out.println("Optimized Output: " + simulator03.run2());
        System.out.println("====================Case: 4====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("1 - 1 - 1 - 1 - 1");
        System.out.println("===Defenders===");
        System.out.println("0 - 0 - 0 - 5 - 0");
        AABattleSimulator simulator04 = new AABattleSimulator(new int[]{1,1,1,1,1},
                new int[]{0,0,0,5,0}, 1);
        simulator04.setSimulationLength(300000);
        System.out.println("Expected Output : 0.029165");
        System.out.println("Optimized Output: " + simulator04.run2());
        System.out.println("====================Case: 5====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("1 - 2 - 3 - 4 - 5");
        System.out.println("===Defenders===");
        System.out.println("5 - 4 - 3 - 2 - 1");
        AABattleSimulator simulator05 = new AABattleSimulator(new int[]{1,2,3,4,5},
                new int[]{5,4,3,2,1}, 1);
        simulator05.setSimulationLength(300000);
        System.out.println("Expected Output : 0.0378533333333333");
        System.out.println("Optimized Output: " + simulator05.run2());
        System.out.println("====================Case: 6====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("3 - 0 - 2 - 1 - 1");
        System.out.println("===Defenders===");
        System.out.println("3 - 1 - 2 - 1 - 0");
        AABattleSimulator simulator06 = new AABattleSimulator(new int[]{3,0,2,1,1},
                new int[]{3,1,2,1,0}, 1);
        simulator06.setSimulationLength(300000);
        System.out.println("Expected Output : 0.179333333333333");
        System.out.println("Optimized Output: " + simulator06.run2());
        System.out.println("====================Case: 7====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("5 - 3 - 2 - 0 - 0");
        System.out.println("===Defenders===");
        System.out.println("4 - 3 - 2 - 2 - 0");
        AABattleSimulator simulator07 = new AABattleSimulator(new int[]{5,3,2,0,0},
                new int[]{4,3,2,2,0}, 1);
        simulator07.setSimulationLength(300000);
        System.out.println("Expected Output : 0.09927");
        System.out.println("Optimized Output: " + simulator07.run2());
        System.out.println("====================Case: 8====================");
        //========================================================================
        System.out.println("===Attackers===");
        System.out.println("10 - 10 - 0 - 0 - 0");
        System.out.println("===Defenders===");
        System.out.println("20 - 0 - 0 - 0 - 0");
        AABattleSimulator simulator08 = new AABattleSimulator(new int[]{10,10,0,0,0,},
                new int[]{20,0,0,0,0}, 1);
        simulator08.setSimulationLength(300000);
        System.out.println("Expected Output : 0.497873333333333");
        System.out.println("Optimized Output: " + simulator08.run2());
        System.out.println("===============================================");
    }
}