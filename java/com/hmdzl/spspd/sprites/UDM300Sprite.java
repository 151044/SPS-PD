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
import com.hmdzl.spspd.effects.Speck;
import com.watabou.noosa.TextureFilm;

public class UDM300Sprite extends MobSprite {

	public UDM300Sprite() {
		super();

		texture(Assets.DM300);

		TextureFilm frames = new TextureFilm(texture, 22, 20);

		idle = new Animation(10, true);
		idle.frames(frames, 9, 10);

		run = new Animation(10, true);
		run.frames(frames, 11, 12);

		attack = new Animation(15, false);
		attack.frames(frames, 13, 14, 15);

		die = new Animation(20, false);
		die.frames(frames, 9, 16, 9, 16, 9, 16, 9, 16, 9, 16, 9, 16, 17);

		play(idle);
	}

	@Override
	public void onComplete(Animation anim) {

		super.onComplete(anim);

		if (anim == die) {
			emitter().burst(Speck.factory(Speck.WOOL), 15);
		}
	}
	
	@Override
	public int blood() {
		return 0xFFFFFF88;
	}
}