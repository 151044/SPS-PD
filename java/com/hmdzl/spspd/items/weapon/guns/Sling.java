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
package com.hmdzl.spspd.items.weapon.guns;

import com.hmdzl.spspd.actors.hero.Hero;
import com.hmdzl.spspd.sprites.ItemSpriteSheet;
import com.hmdzl.spspd.items.Item;
import com.watabou.utils.Random;

public class Sling extends GunWeapon {

	{
		//name = "Sling";
		image = ItemSpriteSheet.SLING;
	}

	public Sling() {
		super(0, 1);
		STR = 8;
		MIN = 3;
		MAX = 7;
	}

	public Item upgrade() {
		MIN++;
		MAX += 2;
		return super.upgrade();
	}

	@Override
	public int damageRoll(Hero owner) {
		return Random.Int(MIN, MAX) / 2;
	}
}



