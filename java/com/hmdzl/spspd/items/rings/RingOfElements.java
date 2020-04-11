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
package com.hmdzl.spspd.items.rings;

import java.util.HashSet;

import com.hmdzl.spspd.actors.blobs.ConfusionGas;
import com.hmdzl.spspd.actors.blobs.CorruptGas;
import com.hmdzl.spspd.actors.blobs.DarkGas;
import com.hmdzl.spspd.actors.blobs.ElectriShock;
import com.hmdzl.spspd.actors.blobs.FrostGas;
import com.hmdzl.spspd.actors.blobs.ParalyticGas;
import com.hmdzl.spspd.actors.blobs.ShockWeb;
import com.hmdzl.spspd.actors.blobs.SlowWeb;
import com.hmdzl.spspd.actors.blobs.ToxicGas;
import com.hmdzl.spspd.actors.blobs.VenomGas;
import com.hmdzl.spspd.actors.blobs.Web;
import com.hmdzl.spspd.actors.blobs.weather.WeatherOfRain;
import com.hmdzl.spspd.actors.blobs.weather.WeatherOfSand;
import com.hmdzl.spspd.actors.blobs.weather.WeatherOfSnow;
import com.hmdzl.spspd.actors.blobs.weather.WeatherOfSun;
import com.hmdzl.spspd.actors.buffs.Blindness;
import com.hmdzl.spspd.actors.buffs.Burning;
import com.hmdzl.spspd.actors.buffs.Disarm;
import com.hmdzl.spspd.actors.buffs.Locked;
import com.hmdzl.spspd.actors.buffs.Poison;
import com.hmdzl.spspd.actors.buffs.Silent;
import com.hmdzl.spspd.actors.mobs.BrokenRobot;
import com.hmdzl.spspd.actors.mobs.DM300;
import com.hmdzl.spspd.actors.mobs.Eye;
import com.hmdzl.spspd.actors.mobs.LitTower;
import com.hmdzl.spspd.actors.mobs.Otiluke;
import com.hmdzl.spspd.actors.mobs.GnollShaman;
import com.hmdzl.spspd.actors.mobs.Shell;
import com.hmdzl.spspd.actors.mobs.Warlock;
import com.hmdzl.spspd.actors.mobs.Yog;
import com.hmdzl.spspd.levels.traps.LightningTrap;
import com.hmdzl.spspd.levels.traps.SpearTrap;
import com.hmdzl.spspd.messages.Messages;
import com.watabou.utils.Random;

public class RingOfElements extends Ring {

	{
		//name = "Ring of Elements";
		initials = 1;
	}

	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats");
		} else {
			return "???";
		}
	}	
		
	
	@Override
	protected RingBuff buff() {
		return new Resistance();
	}

	private static final HashSet<Class<?>> EMPTY = new HashSet<Class<?>>();
	private static final HashSet<Class<?>> FULL;
	static {
		FULL = new HashSet<Class<?>>();
		FULL.add(Burning.class);
		FULL.add(ToxicGas.class);
		FULL.add(VenomGas.class);
		FULL.add(SpearTrap.class);
		FULL.add(ParalyticGas.class);
		FULL.add(CorruptGas.class);
		FULL.add(DarkGas.class);
		FULL.add(ElectriShock.class);
		FULL.add(FrostGas.class);
		FULL.add(ConfusionGas.class);
		FULL.add(ShockWeb.class);
		FULL.add(SlowWeb.class);
		FULL.add(Web.class);
		FULL.add(Blindness.class);
		FULL.add(Disarm.class);
		FULL.add(Locked.class);
		FULL.add(Silent.class);
		FULL.add(WeatherOfRain.class);
		FULL.add(WeatherOfSand.class);
		FULL.add(WeatherOfSnow.class);
		FULL.add(WeatherOfSun.class);
		FULL.add(Poison.class);
		FULL.add(LightningTrap.Electricity.class);
		FULL.add(Warlock.class);
		FULL.add(GnollShaman.class);
		FULL.add(BrokenRobot.class);
		FULL.add(DM300.class);
		FULL.add(Eye.class);
		FULL.add(Otiluke.class);
		FULL.add(LitTower.class);
		FULL.add(Shell.class);
		FULL.add(Yog.BurningFist.class);
		FULL.add(Yog.PinningFist.class);

	}

	public class Resistance extends RingBuff {

		public HashSet<Class<?>> resistances() {
			if (Random.Int(level + 2) >= 2) {
				return FULL;
			} else {
				return EMPTY;
			}
		}

		public float durationFactor() {
			return level < 0 ? 1 : (1 + 0.5f * level) / (1 + level);
		}
	}
}
