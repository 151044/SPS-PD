/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
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
<<<<<<< HEAD:src/com/watabou/noosa/ColorBlock.java

package com.watabou.noosa;

import com.watabou.gltextures.TextureCache;
=======
package com.hmdzl.spspd.actors.buffs;

import com.hmdzl.spspd.messages.Messages;
import com.hmdzl.spspd.ui.BuffIndicator;

public class MagicWeak extends FlavourBuff {
>>>>>>> adb979673be899d5ff36c06d8074ae3692b3ebdd:java/com/hmdzl/spspd/actors/buffs/MagicWeak.java

public class ColorBlock extends Image implements Resizable {
	
	public ColorBlock( float width, float height, int color ) {
		super( TextureCache.createSolid( color ) );
		scale.set( width, height );
		origin.set( 0, 0 );
	}

	@Override
<<<<<<< HEAD:src/com/watabou/noosa/ColorBlock.java
	public void size( float width, float height ) {
		scale.set( width, height );
=======
	public int icon() {
		return BuffIndicator.MAGICWEAK;
>>>>>>> adb979673be899d5ff36c06d8074ae3692b3ebdd:java/com/hmdzl/spspd/actors/buffs/MagicWeak.java
	}
	
	@Override
	public float width() {
		return scale.x;
	}
	
	@Override
	public float height() {
		return scale.y;
	}
}
