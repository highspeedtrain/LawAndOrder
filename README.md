<p align="center">
# **Create: Law and Order**

# THIS MOD IS STILL IN DEVELOPMENT. VERY UNSTABLE AND UNFINISHED
</p>

**!! This mod requires Create. Currently only 0.5.1j is supported !!**

A mod that adds the judicial system to Create! Sue players, criminal records, laws and more!
Main Features:
Player Lawsuits - Sue other players!
Judge (Player Job) - Take control of the court!
Criminal Records - These affect your trades with villagers!
Attorney General - A new villager type, don't let him see you commit crimes!
Custom Laws - Server owners can implement their own laws!

Integrations:
Create Numismatics - You can set custom prices to start lawsuits, penalties for civil cases etc


# Main Info

**-> Player Lawsuits**
	
 	-> Players can file lawsuits against other players.

	-> Integration with Numismatics, server owner able to set price of lawsuit.

	-> Mod finds nearest useable court and teleports all players involved on the next morning.

	-> The rest of the players not involved in the lawsuit (depending on the capacity of the Jury Benches) are teleported as jury.

	-> At the end of the case, every jury member gets a gui asking for Guilty or Not Guilty

	-> If guilty, judge decides how much to pay the Plaintiff.

**-> Judge Job**
	
	-> Players can sign up for this job by right clicking on an available Judge Seat

	-> They get a gavel (if possible a wig?)

	-> These players get to decide the amount of money awarded in civil cases

	-> And the criminal record for criminal cases

**-> Criminal Records & Attorney General (AG)**

	-> In each village, there is a guaranteed spawn of an Attorney General villager.

	-> If this villager witnesses a crime, or sees a villager that had a crime commited upon, they will start a Criminal Trial

	-> If the outcome of the trial is Guilty, the player that commited the crime will get a criminal record

	-> Depending on the severity, these affect your trades with villagers
		-> Misdemeanor crimes (punching once) will affect slightly (10 emeralds for 1 bread instead of 8 emeralds for one bread)
		-> Misdemenaor-Felony crimes (killing one villager) will affect your trades a bit more (12 emeralds instead of 8 emeralds)
		-> Felony crimes (mass villager murder) will severely affect your trades (16 emeralds instead of 8 or double)

	-> If you have a large criminal record (5 felonies or more), you are unable to take weapons into villages. If witnessed by AG, you can be taken
	   for a criminal trial.

**-> Custom laws???**

	-> Server Owners can set custom laws that players must follow.

	-> If the AG witnesses one of these laws being broken, they will take the player for a criminal trial
	
**-> Sherriff (maybe later)**

	-> Another job, players can become sheriffs where they ensure laws are being followed.
	
	-> If a player is breaking laws, they get a red popup above their head which notifies the sheriff about them

	-> If the sheriff either handcuffs them (right click with handcuff tool for 2 seconds) or kills them, they are taken for a criminal trial

	-> If the sheriff attempts to do that on someone who isn't wanted, they are taken for a criminal trial themselves.

**-> Lawyers (definitely later)**

	-> Players can get lawyers which defend for them.
	
	-> These will require a law course to be taken.

# Blocks
**-> Lawsuit Paper**

	-> Crafting Recipe:
		-> Paper
		-> Ink Feather
	-> REQUIRED to start a lawsuit (needed in the block GUI)
**-> Ink Feather**

	-> Crafting Recipe:
		-> Feather
		-> Ink Sack
	-> Only used for the lawsuit crafting recipe
**-> Court Terminal**

	-> Crafting Recipe:
		-> Brass Casing
		-> Precision Mechanism
	-> Needs a rotational input. The more rotational input, the faster it processes.
	-> Used to create lawsuits