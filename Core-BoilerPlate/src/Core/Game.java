package Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import GameObjects.Block;
import GameObjects.Player;
import demoObjects.DemoObj;

public class Game extends Canvas  implements Runnable{
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private BufferedImage level = null;
	
	public Game() {
		
		// inicia a classe Window que é a janela do jogo
		new Window(1024, 768, "BoilerPlate", this);
		start();
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler)); // pegar inputs de keys
		
		BufferedImageLoader loader = new BufferedImageLoader(); // carregar o level da png
		level = loader.loadImage("/game_level.png");
		
		loadLevel(level);
	}
	
	private void start() {
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		// game loop notch - minecraft
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop(); 
	}
	
	private void render() {
		
		// este metodo desenha na tela
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g; // converter os graficos para 2d
		// aqui inicia o desenho em tela
		
		g.setColor(Color.red);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(-camera.getX(),-camera.getY());
		
		
		handler.render(g);
		
		g2d.translate(camera.getX(),camera.getY());
		
		// aqui termina o desenho em tela
		g.dispose();
		bs.show();
	}
	
	// loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16)& 0xff;
				int green = (pixel >> 8)& 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255) {
					handler.addObject(new Block(xx*32, yy*32, ID.Block));
				}
				if(blue == 255) {
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler));
				}
			}
		}
	}

	private void tick() {
		
		// aqui passa a camera para o player
		for(int i =0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));		
			}
			
		}
		// este metodo faz o update 
		handler.tick();
		
	}

	public static void main(String args[]) {
		
		// inicio da classe chama o construtor game para começar a rodar o programa
		new Game();
	}

	
}
