/*
<<<<<<< HEAD:src/com/watabou/utils/Bundlable.java
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
=======
 * Copyright (C) 2012-2015 Oleg Dolya
>>>>>>> adb979673be899d5ff36c06d8074ae3692b3ebdd:libs/src/main/java/com/watabou/utils/Bundlable.java
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

package com.watabou.utils;

public interface Bundlable {

	void restoreFromBundle( Bundle bundle );
	void storeInBundle( Bundle bundle );
	
}
