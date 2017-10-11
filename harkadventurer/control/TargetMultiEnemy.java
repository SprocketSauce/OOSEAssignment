package harkadventurer.control;

import java.util.*;
import java.io.*;
import harkadventurer.model.Targetable;
import harkadventurer.model.Team;

public class TargetMultiEnemy implements TargetingStrategy, Serializable
{
	public LinkedList<Targetable> getTargets( Team allyTeam, LinkedList<Team> enemyTeams )
	{
		LinkedList<Targetable> targets = new LinkedList<Targetable>();
		
		for ( Team team : enemyTeams )
		{
			targets.add( (Targetable)team );
		} // end for
		
		return targets;
	} // end method
	
	public String toString()
	{
		return "Enemy Team";
	} // end method
} // end class