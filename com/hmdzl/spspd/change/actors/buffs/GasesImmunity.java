/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.hmdzl.spspd.change.actors.buffs;

import com.hmdzl.spspd.change.actors.blobs.ConfusionGas;
import com.hmdzl.spspd.change.actors.blobs.ParalyticGas;
import com.hmdzl.spspd.change.actors.blobs.StenchGas;
import com.hmdzl.spspd.change.actors.blobs.ToxicGas;
import com.hmdzl.spspd.change.messages.Messages;
import com.hmdzl.spspd.change.ui.BuffIndicator;

public class GasesImmunity extends FlavourBuff {

	public static final float DURATION = 15f;

	@Override
	public int icon() {
		return BuffIndicator.IMMUNITY;
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	{
		immunities.add(ParalyticGas.class);
		immunities.add(ToxicGas.class);
		immunities.add(ConfusionGas.class);
		immunities.add(StenchGas.class);
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}	
}
