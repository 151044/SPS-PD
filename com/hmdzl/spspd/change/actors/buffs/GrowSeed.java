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

import com.hmdzl.spspd.change.Dungeon;
import com.hmdzl.spspd.change.ResultDescriptions;
import com.hmdzl.spspd.change.actors.Actor;
import com.hmdzl.spspd.change.actors.Char;
import com.hmdzl.spspd.change.actors.hero.Hero;
import com.hmdzl.spspd.change.actors.mobs.Mob;
import com.hmdzl.spspd.change.items.rings.RingOfElements.Resistance;
import com.hmdzl.spspd.change.levels.Level;
import com.hmdzl.spspd.change.messages.Messages;
import com.hmdzl.spspd.change.sprites.CharSprite;
import com.hmdzl.spspd.change.ui.BuffIndicator;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class GrowSeed extends Buff implements Hero.Doom {

	{
		type = buffType.NEGATIVE;
	}

	private static final float DURATION = 10f;
	
	private float left;

	private static final String LEFT = "left";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEFT, left);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		left = bundle.getFloat(LEFT);
	}	
	
	@Override
	public boolean act() {

		if (target.isAlive()) {
			
			int dmg = Random.Int(1, target.HT/20);

			target.damage(dmg, this);

			int p = target.pos;
			for (int n : Level.NEIGHBOURS8) {
				Char ch = Actor.findChar(n+p);
				if (ch != null && ch != target && ch.isAlive()) {
					ch.HP += dmg;
				}
			}


		} else {
			detach();
		}


		spend(TICK);
		left -= TICK;

		if (left <= 0) {

			detach();
		}

		return true;
	}	
	
	public void reignite(Char ch) {
		left = duration(ch);
	}	
	
	@Override
	public int icon() {
		return BuffIndicator.GROW_SEED;
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.REGROW);
		else target.sprite.remove(CharSprite.State.REGROW);
	}	
	
	@Override
	public String heroMessage() {
		return Messages.get(this, "heromsg");
	}	
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(left));
	}

	public static float duration(Char ch) {
		Resistance r = ch.buff(Resistance.class);
		return r != null ? r.durationFactor() * DURATION : DURATION;
	}

	@Override
	public void onDeath() {
		Dungeon.fail(Messages.format(ResultDescriptions.ITEM));
	}
}
