package com.hmdzl.spspd.items.weapon.spammo;

import com.hmdzl.spspd.actors.Char;
import com.hmdzl.spspd.actors.buffs.ArmorBreak;
import com.hmdzl.spspd.actors.buffs.Buff;
import com.hmdzl.spspd.actors.buffs.Cripple;
import com.hmdzl.spspd.actors.buffs.Slow;
import com.hmdzl.spspd.items.Item;
import com.hmdzl.spspd.items.weapon.guns.GunA;
import com.hmdzl.spspd.items.weapon.guns.GunB;
import com.hmdzl.spspd.items.weapon.guns.GunC;
import com.hmdzl.spspd.items.weapon.guns.GunD;
import com.hmdzl.spspd.items.weapon.guns.GunE;
import com.hmdzl.spspd.items.weapon.guns.GunWeapon;
import com.hmdzl.spspd.items.weapon.missiles.Boomerang;
import com.hmdzl.spspd.items.weapon.missiles.ManyKnive;
import com.hmdzl.spspd.sprites.ItemSprite.Glowing;
import com.hmdzl.spspd.items.weapon.guns.Sling;
import com.hmdzl.spspd.sprites.ItemSprite;
import com.hmdzl.spspd.sprites.ItemSpriteSheet;

public class DreamAmmo extends SpAmmo {

	private static Glowing GREEN = new ItemSprite.Glowing(0x22CC44);

	@Override
	public Glowing glowing() {
		return GREEN;
	}

	@Override
	public void onHit(GunWeapon gunweapon, Char attacker, Char defender, int damage) {
		defender.damage((int)(0.20*damage), attacker);
		Buff.prolong(defender, ArmorBreak.class,6f).level(25);
		Buff.prolong(defender, Slow.class,3f);
	}
	@Override
	public void onHit(Boomerang boomerang, Char attacker, Char defender, int damage) {
		defender.damage((int)(0.20*damage), attacker);
		Buff.prolong(defender, ArmorBreak.class,6f).level(25);
		Buff.prolong(defender, Slow.class,3f);
	}
	@Override
	public void onHit(ManyKnive manyknive, Char attacker, Char defender, int damage) {
		defender.damage((int)(0.20*damage), attacker);
		Buff.prolong(defender, ArmorBreak.class,6f).level(25);
		Buff.prolong(defender, Slow.class,3f);
	}
}