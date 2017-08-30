import java.util.*;
/**
 * Test Driver for Homework #1: Axis & Allies 1942, 2nd Edition
 * 
 * @author Jordan Cartwright
 * @version 1.0
 */
public class DriverQuestion4
{
    /**
     * Main program for our DriverQuestion4
     * 
     * @param args  Command Line Arguments
     */
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        System.out.println("4. Given 100 units of cost, what is the most effective defensive force");
        System.out.println("   against 10 tanks and 4 fighters?");
        System.out.println("");
        System.out.println("============ Attacking Army ============");
        System.out.println("");
        System.out.println("           0 - 0 - 10 - 4 - 0");
        System.out.println("");
        System.out.println("============ Defending Army ============");
        System.out.print("Type in the number of defending infantry units => ");
        int dInfantry, dArtillery, dTank, dFighter, dBomber, dAA;
        dInfantry = Integer.parseInt(s.next());
        System.out.print("Type in the number of defending artillery units => ");
        dArtillery = Integer.parseInt(s.next());
        System.out.print("Type in the number of defending tank units => ");
        dTank = Integer.parseInt(s.next());
        System.out.print("Type in the number of defending fighter units => ");
        dFighter = Integer.parseInt(s.next());
        System.out.print("Type in the number of defending bomber units => ");
        dBomber = Integer.parseInt(s.next());
        System.out.print("Type in the number of Anti-Aircraft units => ");
        dAA = Integer.parseInt(s.next());
        AABattleSimulator simulator = new AABattleSimulator(new int[]{0,0,10,4,0},
                    new int[]{dInfantry, dArtillery, dTank, dFighter, dBomber}, dAA);
        simulator.setSimulationLength(300000);
        System.out.println("");
        System.out.print(simulator.run());
        s.close();
    }
}