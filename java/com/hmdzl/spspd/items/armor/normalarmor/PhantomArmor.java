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
package com.hmdzl.spspd.items.armor.normalarmor;

import com.hmdzl.spspd.items.Item;
import com.hmdzl.spspd.sprites.ItemSpriteSheet;

public class PhantomArmor extends NormalArmor {

	{
		//name = "phantom armor";
		image = ItemSpriteSheet.PHANTOM_ARMOR;
		STR -= 1;
		MAX = 35;
		MIN = 0;
	}

	public PhantomArmor() {
		super(6,2f,2f,2);
	}

	@Override
	public Item upgrade(boolean hasglyph) {
		MIN -= 1;
		return super.upgrade(hasglyph);
	}
}