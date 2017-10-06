import java.util.*;

abstract public class Character implements Subject, Targetable
{
	private String name;
	private int maxHealth;
	private int curHealth;
	private LinkedList<Ability> abilities;
    private LinkedList<Observer> observers;

	//	=====CONSTRUCTORS=====
	//	-----Default Constructor-----
	public Character()
	{
		name = null;
		maxHealth = 1;
		curHealth = 1;
		abilities = new LinkedList<Ability>();
        observers = new LinkedList<Observer>();
	} // end constructor

	// -----Alternate Constructor-----
	public Character( String inName, int inMax, LinkedList<Ability> inAbility ) throws CharacterException
	{
		setName( inName );
		setMaxHealth( inMax );
		setCurHealth( inMax );
		abilities = inAbility;
	} // end constructor

	// =====ACCESSORS=====
	public String getName() { return new String( name ); }
	public int getMaxHealth() { return maxHealth; }
	public int getCurHealth() { return curHealth; }
	public LinkedList<Ability> getAbilities() { return (LinkedList<Ability>)abilities.clone(); }
    public LinkedList<Observer> getObservers() { return (LinkedList<Observer>)observers.clone(); }

	// =====MUTATORS=====
	public void setName ( String inName )
	{
		name = new String( inName );
	} // end mutator

	public void setMaxHealth( int inMax ) throws CharacterException
	{
		if ( inMax < 20 )
		{
			throw new CharacterException( "Max Health must be at least 20" );
		}
		else
		{
			maxHealth = inMax;
		}
	} // end mutator

	public void setCurHealth( int inCur )
	{
		if ( inCur > maxHealth )
		{
			curHealth = maxHealth;
		}
		else if ( inCur <= 0 )
		{
			curHealth = 0;
			notifyObservers();
		}
		curHealth = inCur;
	} // end mutator

	public void changeHealth( int amount )
	{
		setCurHealth( curHealth + amount );
	} // end mutator

	public void addAbility( Ability inAbility )
	{
		abilities.add( inAbility );
	} // end mutator
	
	@Override
	public boolean equals( Object inObj )
	{
		Character inChara;
		boolean result = false;
		
		if ( inObj instanceof Character )
		{
			inChara = (Character)inObj;
			result = ( name == inChara.getName() && maxHealth == inChara.getMaxHealth() && curHealth == inChara.getCurHealth() && abilities.equals( inChara.getAbilities() ) );
		}
		
		return result;
	} // end method
    
    public String toString()
    {
        String outStr;
        
        outStr = "Name: " + name + "    HP: " + curHealth + "/" + maxHealth + "\nAbilities:    ";
        for( Ability a : abilities )
        {
            outStr += a.getName() + "    ";
        } // end for
        
        return outStr;
    } // end method

    public void registerObserver( Observer inObv )
    {
        observers.add( inObv );
    } // end mutator
    
    public void removeObserver( Observer inObv )
    {
        int index;
        
        index = observers.indexOf( inObv );
        if ( index != -1 )
        {
            observers.remove( index );
        }
    } // end mutator
    
    abstract public void notifyObservers();
} // end class