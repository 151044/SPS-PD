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
import com.watabou.noosa.TextureFilm;

public class DragonkingSprite extends MobSprite {

	public DragonkingSprite() {
		super();

		texture(Assets.DRAGONKING);

		TextureFilm frames = new TextureFilm(texture, 16, 16);

		idle = new Animation(1, true);
		idle.frames(frames, 0, 0, 0, 1, 1, 1);

		run = new Animation(1, true);
		run.frames(frames, 0, 0, 0, 1, 1, 1);

		attack = new Animation(10, false);
		attack.frames(frames, 0, 3, 4, 5);

		die = new Animation(10, false);
		die.frames(frames, 0, 6, 7, 8, 9);

		play(idle);
	}
}