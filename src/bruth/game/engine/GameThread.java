package bruth.game.engine;

/* Welcome to the main game thread, the heart of the game's engine.
 * This is where screen refreshing (FPS) and updating (moving players/objects) occurs.
 * The run() method is the code that is executed by whatever thread you give this class to.
 */

public class GameThread extends Thread implements Runnable {
	private boolean running = false;
	private int tickCount;
	
	public void start() {
		this.running = true;
		this.run();
	}
	
	//public void stop() {
		//this.running = false;
	//}
	
	public void tick() {
		this.tickCount++;
	}
	
	public void render() {
		
	}
	
	@Override
	public void run() {
		//Do any game initialization here later (if needed)
		//...
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0; 
		long lastTimer1 = System.currentTimeMillis();
		boolean shouldRender = true;
		
		while(this.running) {
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nsPerTick;
			lastTime = now;
			shouldRender = true;
			
			while(deltaTime >= 1) {
				ticks++;
				this.tick();
				deltaTime -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender) {
				frames++;
				this.render();
			}
			
			if(System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				//System.out.println("Ticks: " + ticks + "\tFPS: " + frames);
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
		
		
	}
}
