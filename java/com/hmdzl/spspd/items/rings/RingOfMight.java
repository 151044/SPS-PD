package com.hmdzl.spspd.items.rings;

import com.hmdzl.spspd.messages.Messages;

/**
 * Created by debenhame on 10/09/2014.
 */
public class RingOfMight extends Ring {

	{
		//name = "Ring of Might";
		initials = 7;
	}
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats",level/5,level*10);
		} else {
			return "???";
		}
	}	
	
	
	
	@Override
	protected RingBuff buff() {
		return new Might();
	}


	public class Might extends RingBuff {
	}
}
