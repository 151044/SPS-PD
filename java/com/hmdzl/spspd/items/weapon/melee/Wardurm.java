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
package com.hmdzl.spspd.items.weapon.melee;

import com.hmdzl.spspd.Dungeon;
import com.hmdzl.spspd.actors.buffs.Vertigo;
import com.hmdzl.spspd.items.Item;
import com.hmdzl.spspd.items.KindOfWeapon;
import com.hmdzl.spspd.actors.Actor;
import com.hmdzl.spspd.actors.buffs.Terror;
import com.hmdzl.spspd.levels.Level;
import com.hmdzl.spspd.messages.Messages;
import com.hmdzl.spspd.sprites.ItemSpriteSheet;
import com.hmdzl.spspd.actors.Char;
import com.hmdzl.spspd.actors.buffs.Buff;
import com.watabou.utils.Random;
import com.hmdzl.spspd.utils.GLog;

public class Wardurm extends MeleeWeapon {

	{
		//name = "Wardurm";
		image = ItemSpriteSheet.WARDURM;
	}

	public Wardurm() {
		super(3, 1f, 1f, 2);
	}
	

	@Override
	public Item upgrade(boolean enchant) {
        MIN+=1;
        MAX+=2;		
		return super.upgrade(enchant);
    }
	
    @Override
    public void proc(Char attacker, Char defender, int damage) {

		
		int p = defender.pos;
		for (int n : Level.NEIGHBOURS8) {
			Char ch = Actor.findChar(n+p);
			if (ch != null && ch != defender && ch != attacker && ch.isAlive()) {

				int dr = Random.IntRange( 0, 1 );
				int dmg = Random.Int( MIN, MAX );
				int effectiveDamage = Math.max( dmg - dr, 0 );

				ch.damage( effectiveDamage/2, attacker );

				Buff.prolong(ch, Vertigo.class, 3f);
				Buff.prolong(ch, Terror.class, 3f).object = attacker.id();
			}
		}		


		if (enchantment != null) {
			enchantment.proc(this, attacker, defender, damage);		
		}
		
		if (durable() && attacker == Dungeon.hero){
			durable --;
            if (durable == 10){
                GLog.n(Messages.get(KindOfWeapon.class,"almost_destory"));
            }
			if (durable == 0){
				Dungeon.hero.belongings.weapon = null;
				GLog.n(Messages.get(KindOfWeapon.class,"destory"));
			}
		}		
    }		
}