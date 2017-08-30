import java.util.*;
/**
 * Test Driver for Homework #1: Axis & Allies 1942, 2nd Edition
 * 
 * @author Jordan Cartwright
 * @version 1.0
 */
public class DriverQuestion3
{
    /**
     * Main program for our DriverQuestion3
     * 
     * @param args  Command Line Arguments
     */
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        System.out.println("3. Given 100 units of cost, what is the most effective");
        System.out.println("   attacking force against 33 Infantry?"); //assuming no AA because of this
        System.out.println("");
        System.out.println("============ Attacking Army ============");
        System.out.print("Type in the number of attacking infantry units => ");
        int aInfantry, aArtillery, aTank, aFighter, aBomber, dInfantry, dArtillery, dTank, dFighter, dBomber;
        aInfantry = Integer.parseInt(s.next());
        System.out.print("Type in the number of attacking artillery units => ");
        aArtillery = Integer.parseInt(s.next());
        System.out.print("Type in the number of attacking tank units => ");
        aTank = Integer.parseInt(s.next());
        System.out.print("Type in the number of attacking fighter units => ");
        aFighter = Integer.parseInt(s.next());
        System.out.print("Type in the number of attacking bomber units => ");
        aBomber = Integer.parseInt(s.next());
        System.out.println("");
        System.out.println("============ Defending Army ============");
        System.out.println("");
        System.out.println("           33 - 0 - 0 - 0 - 0");
        System.out.println("");
        System.out.println("========== Battle Results ==========");
        AABattleSimulator simulator01 = new AABattleSimulator(new int[]{aInfantry, aArtillery, aTank, aFighter, aBomber},
                new int[]{33,0,0,0,0});
        simulator01.setSimulationLength(300000);
        System.out.println(simulator01.run());
    }
}