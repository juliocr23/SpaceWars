package Entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class MissileController {

	public ArrayList<Missile> b = new ArrayList<Missile>();
	
	Missile TempMissile;
	
	
	public void update() {
		
		for(int i = 0; i < b.size(); i++) {
			TempMissile = b.get(i);
			
			if(TempMissile.getY() <= 0) {
				removeMissile(TempMissile);
			}
			
			TempMissile.update();
		}
	}
	
	public void addMissile(Missile missile) { b.add(missile); }
	public void removeMissile(Missile missile) { b.remove(missile); }
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < b.size(); i++) {
			TempMissile = b.get(i);
			
			TempMissile.draw(g);
		}
		
	}
}
