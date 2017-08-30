import java.util.Random;
/**
 * This is a Simulator for land battles in the game Axis and Allies 1942, Second Edition.
 * The main assumptions and constraints are: 
 * the cheapest cost units are taken as casulties first.
 * 
 * @author (Jordan Cartwright)
 * @version (2.0)
 * 
 */
public class AABattleSimulator
{
    int[] attackClone;
    int[] defenseClone;
    int diceRoll = 0;
    int simulationLength = 0;
    int aInfantry = 0;
    int aArtillery = 0;
    int aTank = 0;
    int aFighters = 0;
    int aBombers = 0;
    int dInfantry = 0;
    int dArtillery = 0;
    int dTank = 0;
    int dFighters = 0;
    int dBombers = 0;
    int dAA = 0;
    int numAA = 0;
    /**
     * Constructor for objects of class AABattleSimulator
     * 
     * @param attack - Array of attacking units in the order
     *                {infantry, artillery, tank, fighter, bomber}
     * 
     * @param defense - Array of defending units in the previously stated order
     */
    public AABattleSimulator(int[] attack, int[] defense)
    {
        //setting attacking troops
        aInfantry = attack[0];
        aArtillery = attack[1];
        aTank = attack[2];
        aFighters = attack[3];
        aBombers = attack[4];

        //clone array "attack"
        //used to reset the game for every simulation
        attackClone = attack.clone();

        //setting defending troops
        dInfantry = defense[0];
        dArtillery = defense[1];
        dTank = defense[2];
        dFighters = defense[3];
        dBombers = defense[4];

        //clone array "defense"
        //Used to reset the game for every simulation
        defenseClone = defense.clone();
    }
    
    /**
     * 2nd Constructor for objects of class AABattleSimulator
     * 
     * @param attack - Array of attacking units in the order
     *                {infantry, artillery, tank, fighter, bomber}
     * 
     * @param defense - Array of defending units in the previously stated order
     * 
     * @param AA - Number of defending Anti-Air Craft units
     */
    public AABattleSimulator(int[] attack, int[] defense, int AA)
    {
        //setting attacking troops
        aInfantry = attack[0];
        aArtillery = attack[1];
        aTank = attack[2];
        aFighters = attack[3];
        aBombers = attack[4];

        //clone array "attack"
        //used to reset the game for every simulation
        attackClone = attack.clone();

        //setting defending troops
        dInfantry = defense[0];
        dArtillery = defense[1];
        dTank = defense[2];
        dFighters = defense[3];
        dBombers = defense[4];
        dAA = AA;
        numAA = AA;

        //clone array "defense"
        //Used to reset the game for every simulation
        defenseClone = defense.clone();
    }

    /**
     *  Runs a Simulation of an Axis and Allies Battle 
     *  and determines the probability of victory of the attacking force.
     *  
     *  @return - returns the decimal percentage of victory for the attacking side
     */
    public double run()
    {
        double percent = 0.0;
        int attackerCount = aInfantry + aArtillery + aTank + aFighters + aBombers;
        int defenderCount = dInfantry + dArtillery + dTank + dFighters + dBombers + dAA;
        int attackerCasualtyCount = 0;
        int defenderCasualtyCount = 0;
        int missCount = 0;
        int victoryCount = 0;
        int actualSimLength = 0;
        int battleRoundCount = 1;

        for(int currentSim = 1; currentSim < simulationLength+ 1; currentSim++)
        {
            //if there are no enemy defenders
            //Added in in case you attack a country with no defenders
            if(defenderCount == 0 && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
            {
                victoryCount++;
                defenderCount = 0;
            }

            //Check for any attackers or defenders to run the simulation
            while(attackerCount > 0 && defenderCount > 0)
            {
                //==========================================
                //Defending Antiaircraft Artillery fire
                //==========================================
                if((aFighters + aBombers) > 0 && battleRoundCount == 1)   //Stop firing early if hits exceed number of aircraft left
                {
                    int alowedDiceRolls;
                    /*No Combat Value: 
                    It can, however, be taken as a casualty.
                    If a territory containing AAA units and no combat units is attacked, the AAA units are automatically destroyed. AAA units may never attack.

                    Air Defense:
                    An Antiaircraft artillery (AAA) unit can fire only at an air unit when that unit attacks the territory containing
                    that AAA unit. 

                    AAA units fire only once, before the first round of combat. //if((aFighters + aBombers) > 0 && BattleRoundCount == 1)

                    Each AAA unit in the territory may fire up to 3 times, but only once per attacking air unit.

                    In other words, the total number of air defense alowedDiceRolls(dice rolled) is three times the number of AAA units, 
                    or the number of attacking air units, whichever is the lesser.

                    Once the number of air defense dice is determined, the dice are rolled.

                    For each “1” rolled, the attacker chooses one air unit as a casualty.

                    These casualties are removed immediately, and will not participate in the remainder of the battle.

                    This AAA attack is made immediately before normal combat occurs in the territory containing the AAA unit.

                    1 AA and 1 plane = 1 shot
                    2 aa and 2 planes = 2 shots
                    2 aa and 4 planes = 4 shots
                     */
                    if((aFighters + aBombers) > 0)//check for aircraft to fire at
                    {
                        //caluculate the dice rolls possible
                        if((3 * dAA) < (aFighters + aBombers))
                        {
                            alowedDiceRolls = 3 * dAA;
                        }
                        else
                        {
                            alowedDiceRolls = aFighters + aBombers;
                        }

                        //roll the dice X times for each Enemy aircraft and clauculate for hits
                        for(int roundsToFire = alowedDiceRolls; roundsToFire > 0; roundsToFire--)
                        {
                            if((aFighters + aBombers) > 0)  //check for aircraft to fire at
                            {
                                rollDice();
                                if(diceRoll <= 1)   //if the diceRoll <= attackValue (hit occurs)
                                {
                                    //takes aircraft out immediately
                                    if(aFighters > 0) //checks for remaining fighters to remove
                                    {
                                        aFighters = aFighters - 1;
                                        attackerCount--;
                                    }
                                    else if(aBombers > 0) //checks for remaining bombers to remove
                                    {
                                        aBombers = aBombers - 1;
                                        attackerCount--;
                                    }

                                }
                                else //(miss occurs)
                                {
                                    missCount++;
                                }
                            }
                            else
                            {
                                break;
                            }
                        }
                    }

                }

                //==============
                //Attackers fire
                //==============

                //============================
                //Attacking Infantry
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numInfantry = aInfantry; numInfantry > 0; numInfantry--)
                    {
                        //Infanatry
                        //attack: 1
                        //Defense: 2
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(numInfantry <= aArtillery && diceRoll <= 2) //check for artillery bonus attack/defence
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else if(diceRoll <= 1) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numInfantry = 0;
                        }
                    }
                }

                //============================
                //Attacking Artillery
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numArty = aArtillery; numArty > 0; numArty--)
                    {
                        //Artillery
                        //attack: 2
                        //Defense: 2
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numArty = 0;
                        }
                    }
                }

                //============================
                //Attacking Tanks
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numTanks = aTank; numTanks > 0; numTanks--)
                    {
                        //Tanks
                        //attack: 3
                        //Defense: 3
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numTanks = 0;
                        }
                    }
                }

                //============================
                //Attacking Fighters
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numFighters = aFighters; numFighters > 0; numFighters--)
                    {
                        //Fighters
                        //attack: 3
                        //Defense: 4
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numFighters = 0;
                        }
                    }
                }

                //============================
                //Attacking Bombers
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numBombers = aBombers; numBombers > 0; numBombers--)
                    {
                        //Bombers
                        //attack: 4
                        //Defense: 1
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 4) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numBombers = 0;
                        }
                    }
                }

                //==============
                //Defenders fire
                //==============

                //============================
                //Defending Infantry
                //============================
                if(attackerCount > 0)   //checks for remaining Attaclers to fire at
                {
                    for(int numInfantry = dInfantry; numInfantry > 0; numInfantry--)
                    {
                        //Infanatry
                        //attack: 1
                        //Defense: 2

                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numInfantry = 0;
                        }
                    }
                }

                //============================
                //Defending Artillery
                //============================
                if(attackerCount > 0)   //checks for remaining Attaclers to fire at
                {
                    for(int numArty = dArtillery; numArty > 0; numArty--)
                    {
                        //Artillery
                        //attack: 2
                        //Defense: 2
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numArty = 0;
                        }
                    }
                }

                //============================
                //Defending Tanks
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numTanks = dTank; numTanks > 0; numTanks--)
                    {
                        //Tanks
                        //attack: 3
                        //Defense: 3
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numTanks = 0;
                        }
                    }
                }

                //============================
                //Defending Fighters
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numFighters = dFighters; numFighters > 0; numFighters--)
                    {
                        //Fighters
                        //attack: 3
                        //Defense: 4
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 4) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numFighters = 0;
                        }
                    }
                }

                //============================
                //Defending Bombers
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numBombers = dBombers; numBombers > 0; numBombers--)
                    {
                        //Bombers
                        //attack: 4
                        //Defense: 1
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 1) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numBombers = 0;
                        }
                    }
                }

                //==========================================
                //Remove Cheapest Casualties from play
                //==========================================

                //============================
                //Remove Attacker Casualties
                //============================
                for(int x = attackerCasualtyCount; x > 0; x--)
                {
                    if(aInfantry > 0) //checks for remaining infantry to remove
                    {
                        aInfantry = aInfantry - 1;
                    }
                    else if(aArtillery > 0) //checks for remaining artillery to remove
                    {
                        aArtillery = aArtillery - 1;
                    }
                    else if(aTank > 0) //checks for remaining tanks to remove
                    {
                        aTank = aTank - 1;
                    }
                    else if(aFighters > 0) //checks for remaining fighters to remove
                    {
                        aFighters = aFighters - 1;
                    }
                    else if(aBombers > 0) //checks for remaining bombers to remove
                    {
                        aBombers = aBombers - 1;
                    }
                }

                //============================
                //Remove Defender Casualties
                //============================
                for(int x = defenderCasualtyCount; x > 0; x--)
                {
                    if(dInfantry > 0) //checks for remaining infantry to remove
                    {
                        dInfantry = dInfantry - 1;
                    }
                    else if(dArtillery > 0) //checks for remaining artillery to remove
                    {
                        dArtillery = dArtillery - 1;
                    }
                    else if(dTank > 0) //checks for remaining tanks to remove
                    {
                        dTank = dTank - 1;
                    }
                    else if(dAA > 0) //checks for remaining AA to remove
                    {
                        dAA = dAA - 1;
                    }
                    else if(dFighters > 0) //checks for remaining fighters to remove
                    {
                        dFighters = dFighters - 1;
                    }
                    else if(dBombers > 0) //checks for remaining bombers to remove
                    {
                        dBombers = dBombers - 1;
                    }
                }

                //===========================================
                //Reset CasualtyCounts for next battle round 
                //===========================================
                attackerCasualtyCount = 0;
                defenderCasualtyCount = 0;
                battleRoundCount++;

                //=====================================================
                //check for a victory condition for the attacking army 
                //=====================================================
                if(defenderCount == 0 && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
                {
                    victoryCount++;
                }

            }

            //has to reset to origional inputs eachtime after while loop
            if(currentSim != simulationLength)  //only resets the loop if its isnt the last simulation
            {
                //resetting will only occur if the simulation has to run again
                //resetting attacking troops
                aInfantry = attackClone[0];
                aArtillery = attackClone[1];
                aTank = attackClone[2];
                aFighters = attackClone[3];
                aBombers = attackClone[4];

                //resetting defending troops
                dInfantry = defenseClone[0];
                dArtillery = defenseClone[1];
                dTank = defenseClone[2];
                dFighters = defenseClone[3];
                dBombers = defenseClone[4];
                dAA = numAA;

                //reset the attacker/defender counts
                battleRoundCount = 1;
                attackerCount = 0;
                defenderCount = 0;
                attackerCount = aInfantry + aArtillery + aTank + aFighters + aBombers;
                defenderCount = dInfantry + dArtillery + dTank + dFighters + dBombers + dAA;
            }
            actualSimLength++;
        }

        //calculate percentage of victory for the attacking side
        if(victoryCount > 0)
        {
            percent = ((double)victoryCount / (double)simulationLength);
        }
        //For debugging purposes
        System.out.println("Simulation Length: " + actualSimLength);
        System.out.println("Victory Count: " + victoryCount);
        System.out.print("Attackers Win Percentage: ");

        return percent;
    }
    
    /**
     *  Runs a Simulation of an Axis and Allies Battle 
     *  and determines the probability of victory of the attacking force.
     *  
     *  @return - returns the decimal percentage of victory for the attacking side
     */
    public double run2()
    {
        double percent = 0.0;
        int attackerCount = aInfantry + aArtillery + aTank + aFighters + aBombers;
        int defenderCount = dInfantry + dArtillery + dTank + dFighters + dBombers + dAA;
        int attackerCasualtyCount = 0;
        int defenderCasualtyCount = 0;
        int missCount = 0;
        int victoryCount = 0;
        int actualSimLength = 0;
        int battleRoundCount = 1;

        for(int currentSim = 1; currentSim < simulationLength+ 1; currentSim++)
        {
            //Check for any attackers or defenders
            //to run through the simulation

            //if there are no enemy defenders
            //Added in in case you attack a country with no defenders or just AA units
            //if((defenderCount == 0 || defenderCount <= dAA) && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
            if(defenderCount == 0 && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
            {
                victoryCount++;
                defenderCount = 0;
            }

            while(attackerCount > 0 && defenderCount > 0)
            {
                //==========================================
                //Defending Antiaircraft Artillery fire
                //==========================================
                if((aFighters + aBombers) > 0 && battleRoundCount == 1)   //Stop firing early if hits exceed number of aircraft left
                {
                    int alowedDiceRolls;
                    /*No Combat Value: 
                    It can, however, be taken as a casualty.
                    If a territory containing AAA units and no combat units is attacked, the AAA units are automatically destroyed. AAA units may never attack.

                    Air Defense:
                    An Antiaircraft artillery (AAA) unit can fire only at an air unit when that unit attacks the territory containing
                    that AAA unit. 

                    AAA units fire only once, before the first round of combat. //if((aFighters + aBombers) > 0 && BattleRoundCount == 1)

                    Each AAA unit in the territory may fire up to 3 times, but only once per attacking air unit.

                    In other words, the total number of air defense alowedDiceRolls(dice rolled) is three times the number of AAA units, 
                    or the number of attacking air units, whichever is the lesser.

                    Once the number of air defense dice is determined, the dice are rolled.

                    For each “1” rolled, the attacker chooses one air unit as a casualty.

                    These casualties are removed immediately, and will not participate in the remainder of the battle.

                    This AAA attack is made immediately before normal combat occurs in the territory containing the AAA unit.

                    AAA units do not defend industrial complexes against strategic bombing.

                    Industrial complexes have their own “built in” air defenses (see Industrial Complexes, below).

                    1 AA and 1 plane = 1 shot
                    2 aa and 2 planes = 2 shots
                    2 aa and 4 planes = 4 shots
                     */
                    if((aFighters + aBombers) > 0)//check for aircraft to fire at
                    {
                        //caluculate the dice rolls possible
                        if((3 * dAA) < (aFighters + aBombers))
                        {
                            alowedDiceRolls = 3 * dAA;
                        }
                        else
                        {
                            alowedDiceRolls = aFighters + aBombers;
                        }

                        //roll the dice X times for each Enemy aircraft and clauculate for hits
                        for(int roundsToFire = alowedDiceRolls; roundsToFire > 0; roundsToFire--)
                        {
                            if((aFighters + aBombers) > 0)  //check for aircraft to fire at
                            {
                                rollDice();
                                if(diceRoll <= 1)   //if the diceRoll <= attackValue (hit occurs)
                                {
                                    //takes aircraft out immediately
                                    if(aFighters > 0) //checks for remaining fighters to remove
                                    {
                                        aFighters = aFighters - 1;
                                        attackerCount--;
                                    }
                                    else if(aBombers > 0) //checks for remaining bombers to remove
                                    {
                                        aBombers = aBombers - 1;
                                        attackerCount--;
                                    }

                                }
                                else //(miss occurs)
                                {
                                    missCount++;
                                }
                            }
                            else
                            {
                                break;
                            }
                        }
                    }

                }

                //==============
                //Attackers fire
                //==============

                //============================
                //Attacking Infantry
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numInfantry = aInfantry; numInfantry > 0; numInfantry--)
                    {
                        //Infanatry
                        //attack: 1
                        //Defense: 2
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(numInfantry <= aArtillery && diceRoll <= 2) //check for artillery bonus attack/defence
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else if(diceRoll <= 1) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numInfantry = 0;
                        }
                    }
                }

                //============================
                //Attacking Artillery
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numArty = aArtillery; numArty > 0; numArty--)
                    {
                        //Artillery
                        //attack: 2
                        //Defense: 2
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numArty = 0;
                        }
                    }
                }

                //============================
                //Attacking Tanks
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numTanks = aTank; numTanks > 0; numTanks--)
                    {
                        //Tanks
                        //attack: 3
                        //Defense: 3
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numTanks = 0;
                        }
                    }
                }

                //============================
                //Attacking Fighters
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numFighters = aFighters; numFighters > 0; numFighters--)
                    {
                        //Fighters
                        //attack: 3
                        //Defense: 4
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numFighters = 0;
                        }
                    }
                }

                //============================
                //Attacking Bombers
                //============================
                if(defenderCount > 0)   //checks for remaining defenders to fire at
                {
                    for(int numBombers = aBombers; numBombers > 0; numBombers--)
                    {
                        //Bombers
                        //attack: 4
                        //Defense: 1
                        if(defenderCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 4) //if the diceRoll <= attackValue (hit occurs)
                            {
                                defenderCount--;
                                defenderCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numBombers = 0;
                        }
                    }
                }

                //==============
                //Defenders fire
                //==============

                //============================
                //Defending Infantry
                //============================
                if(attackerCount > 0)   //checks for remaining Attaclers to fire at
                {
                    for(int numInfantry = dInfantry; numInfantry > 0; numInfantry--)
                    {
                        //Infanatry
                        //attack: 1
                        //Defense: 2

                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numInfantry = 0;
                        }
                    }
                }

                //============================
                //Defending Artillery
                //============================
                if(attackerCount > 0)   //checks for remaining Attaclers to fire at
                {
                    for(int numArty = dArtillery; numArty > 0; numArty--)
                    {
                        //Artillery
                        //attack: 2
                        //Defense: 2
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 2) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numArty = 0;
                        }
                    }
                }

                //============================
                //Defending Tanks
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numTanks = dTank; numTanks > 0; numTanks--)
                    {
                        //Tanks
                        //attack: 3
                        //Defense: 3
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 3) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numTanks = 0;
                        }
                    }
                }

                //============================
                //Defending Fighters
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numFighters = dFighters; numFighters > 0; numFighters--)
                    {
                        //Fighters
                        //attack: 3
                        //Defense: 4
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 4) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numFighters = 0;
                        }
                    }
                }

                //============================
                //Defending Bombers
                //============================
                if(attackerCount > 0)   //Stop firing early if hits exceed number of defenders left
                {
                    for(int numBombers = dBombers; numBombers > 0; numBombers--)
                    {
                        //Bombers
                        //attack: 4
                        //Defense: 1
                        if(attackerCount > 0)//check for soldiers to fire at
                        {
                            //roll the dice one time for each soldier
                            rollDice();
                            if(diceRoll <= 1) //if the diceRoll <= attackValue (hit occurs)
                            {
                                attackerCount--;
                                attackerCasualtyCount++;
                            }
                            else //(miss occurs)
                            {
                                missCount++;
                            }
                        }
                        else    //ends the loop early if there are no more enemy soldiers to fire at
                        {
                            numBombers = 0;
                        }
                    }
                }

                //==========================================
                //Remove Cheapest Casualties from play
                //==========================================

                //============================
                //Remove Attacker Casualties
                //============================
                for(int x = attackerCasualtyCount; x > 0; x--)
                {
                    if(aInfantry > 0) //checks for remaining infantry to remove
                    {
                        aInfantry = aInfantry - 1;
                    }
                    else if(aArtillery > 0) //checks for remaining artillery to remove
                    {
                        aArtillery = aArtillery - 1;
                    }
                    else if(aTank > 0) //checks for remaining tanks to remove
                    {
                        aTank = aTank - 1;
                    }
                    else if(aFighters > 0) //checks for remaining fighters to remove
                    {
                        aFighters = aFighters - 1;
                    }
                    else if(aBombers > 0) //checks for remaining bombers to remove
                    {
                        aBombers = aBombers - 1;
                    }
                }

                //============================
                //Remove Defender Casualties
                //============================
                for(int x = defenderCasualtyCount; x > 0; x--)
                {
                    if(dAA > 0) //checks for remaining AA to remove
                    {
                        dAA = dAA - 1;
                    }
                    
                    else if(dFighters > 0) //checks for remaining fighters to remove
                    {
                        dFighters = dFighters - 1;
                    }
                    
                    else if(dInfantry > 0) //checks for remaining infantry to remove
                    {
                        dInfantry = dInfantry - 1;
                    }
                    
                    else if(dArtillery > 0) //checks for remaining artillery to remove
                    {
                        dArtillery = dArtillery - 1;
                    }
                    
                    else if(dBombers > 0) //checks for remaining bombers to remove
                    {
                        dBombers = dBombers - 1;
                    }
                    
                    else if(dTank > 0) //checks for remaining tanks to remove
                    {
                        dTank = dTank - 1;
                    }
                }

                //===========================================
                //Reset CasualtyCounts for next battle round 
                //===========================================
                attackerCasualtyCount = 0;
                defenderCasualtyCount = 0;
                battleRoundCount++;

                //=====================================================
                //check for a victory condition for the attacking army 
                //=====================================================
                //if(attackerCount > 0 && (defenderCount == 0 &&(defenderCount == 0 || dAA > 0)) && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
                if(defenderCount == 0 && (aInfantry > 0 || aArtillery > 0 || aTank > 0))
                {
                    victoryCount++;
                }

            }

            //has to reset to origional inputs eachtime after while loop
            if(currentSim != simulationLength)  //only resets the loop if its isnt the last simulation
            {
                //resetting will only occur if the simulation has to run again
                //resetting attacking troops
                aInfantry = attackClone[0];
                aArtillery = attackClone[1];
                aTank = attackClone[2];
                aFighters = attackClone[3];
                aBombers = attackClone[4];

                //resetting defending troops
                dInfantry = defenseClone[0];
                dArtillery = defenseClone[1];
                dTank = defenseClone[2];
                dFighters = defenseClone[3];
                dBombers = defenseClone[4];
                dAA = numAA;

                //reset the attacker/defender counts
                battleRoundCount = 1;
                attackerCount = 0;
                defenderCount = 0;
                attackerCount = aInfantry + aArtillery + aTank + aFighters + aBombers;
                defenderCount = dInfantry + dArtillery + dTank + dFighters + dBombers + dAA;
            }
            actualSimLength++;
        }

        //calculate percentage of victory for the attacking side
        if(victoryCount > 0)
        {
            percent = ((double)victoryCount / (double)simulationLength);
        }
        //For debugging purposes
        //System.out.println("Simulation Length: " + actualSimLength);
        //System.out.println("Victory Count: " + victoryCount);
        //System.out.print("Attackers Win Percentage: ");

        return percent;
    }

    /**
     *  This method sets the simulation length, which 
     *  by default starts at 1000
     *  
     *  @param len - new length of simulation
     */
    public void setSimulationLength(int len)
    {
        simulationLength = len;
    }

    /**
     * generates a random number
     * and simulates a dice roll
     * 
     */
    public void rollDice()
    {
        //random call
        Random num = new Random();
        //set diceOne to random #
        int diceNum = num.nextInt(6);
        diceRoll = diceNum + 1;
    }
}
