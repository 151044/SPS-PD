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
package com.hmdzl.spspd.sprites;

import com.hmdzl.spspd.Assets;
import com.hmdzl.spspd.actors.mobs.pets.RedDragon;
import com.hmdzl.spspd.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class RedDragonSprite extends MobSprite {
	
	//Frames 1-4 are idle, 5-8 are moving, 9-12 are attack and the last are for death 

	public RedDragonSprite() {
		super();

		texture(Assets.PETDRAGON);

		TextureFilm frames = new TextureFilm(texture, 16, 16);

		idle = new Animation(2, true);
		idle.frames(frames, 0, 1, 2, 3);

		run = new Animation(8, true);
		run.frames(frames, 4, 5, 6, 7);

		attack = new Animation(8, false);
		attack.frames(frames, 8, 9, 10, 11);

		zap = attack.clone();
		
		die = new Animation(8, false);
		die.frames(frames, 12, 13, 14, 15);

		play(idle);
	}

	@Override
	public void zap(int cell) {

		turnTo(ch.pos, cell);
		play(zap);

		MagicMissile.fire(parent, ch.pos, cell, new Callback() {
			@Override
			public void call() {
				((RedDragon) ch).onZapComplete();
			}
		});
		Sample.INSTANCE.play(Assets.SND_ZAP);
	}
	
	@Override
	public int blood() {
		return 0xFFcdcdb7;
	}
}
