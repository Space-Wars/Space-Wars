package com.rentarosato520.spacefighter.asset.animation;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int sw = 64, sh = 64;
	private static final int lh = 9;
	private static final int colSize = 32, rowSize = 32, h = 32, w = 32;
	
	public static BufferedImage player, playerBack, playerLeft, playerRight, playerR1, playerR2, playerR3, playerR4, Enemy, Enemy1, Enemy2, Enemy3, EnemyHurt, Space, Space1, Space2, Space3, playerLaser, playerLaser1, playerLaser2, playerLaser3;
	public static BufferedImage exp, exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9, exp10, exp11, Mexp, Mexp1, Mexp2, Mexp3, Mexp4, Mexp5;
	public static BufferedImage Stars, Star1, Star2, Star3, Star4, Cash, Cash1, Shipp1, Shipp; 
	public static BufferedImage ArenaBarrier, ArenaBarrier1, ArenaBarrier2, meteor, Broad;
	public static BufferedImage ld, ld1, ld2, ld3, ld4, ld5, ld6, ld7, ld8, ld9, ld10, ld11, ld12, ld13, ld14, ld15;
	public static BufferedImage rb, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15, rb16, rb17, rb18, rb19, rb20, rb21, rb22, rb23, rb24, rb25, rb26, rb27;
	public static BufferedImage neutral, neutral1, neutral2, neutral3, friend, friend1, friend2, friend3, SupplyShip;
	public static BufferedImage Goliath, Goliath1, Goliath2, Goliath3, Goliath8, Goliath9, Goliath10, Goliath11, Goliath4, Goliath5, Goliath6, Goliath7, DeathSpavager, DeathSpavager1, DeathSpavager2, DeathSpavager3;
	public static BufferedImage StealthDrone, StealthDrone1, StealthDrone2, StealthDrone3, XBullet44, XBullet441, XBullet442, XBullet443;
	public static BufferedImage ex, ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10;
	
	public static void load(){
		SpriteLoader loader = new SpriteLoader();
		Sprite shipSheet = new Sprite(loader.load("/Models/Ships/PlayerShip.png"));
		Sprite shipRSheet = new Sprite(loader.load("/Models/Ships/PlayerShipRotate.png"));
		Sprite spaceSheet = new Sprite(loader.load("/Models/Misc/Space.png"));
		Sprite laserSheet = new Sprite(loader.load("/Models/Misc/Lasers.png"));
		Sprite basicESheet = new Sprite(loader.load("/Models/Ships/BasicEnemy.png"));
		Sprite expSheet = new Sprite(loader.load("/Models/Misc/Explosion.png"));
		Sprite starSheet = new Sprite(loader.load("/Models/Misc/SpaceStars.png"));
		Sprite miniSheet = new Sprite(loader.load("/Models/Misc/MiniExplosion.png"));
		Sprite ldSheet = new Sprite(loader.load("/Models/Misc/LaserDust.png"));
		Sprite rbSheet = new Sprite(loader.load("/Models/Misc/RocketFire.png"));
		Sprite lSheet = new Sprite(loader.load("/Models/Misc/TheBarrier.png"));
		Sprite spSheet = new Sprite(loader.load("/Models/Misc/ShipParts.png"));
		Sprite monSheet = new Sprite(loader.load("/Models/Misc/Money.png"));
		Sprite Meteor = new Sprite(loader.load("/Models/Misc/Meteor.png"));
		Sprite basicNSheet = new Sprite(loader.load("/Models/Ships/NeutralShip.png"));
		Sprite basicFSheet = new Sprite(loader.load("/Models/Ships/FriendlyShip.png"));
		Sprite supplySheet = new Sprite(loader.load("/Models/Ships/SupplyShip.png"));
		Sprite basicGolSheet = new Sprite(loader.load("/Models/Ships/Goliath.png"));
		Sprite basicDSSheet = new Sprite(loader.load("/Models/Ships/DeathSpavager.png"));
		Sprite SDSheet = new Sprite(loader.load("/Models/Ships/StealthDrone.png"));
		Sprite XBulNSheet = new Sprite(loader.load("/Models/Ships/XBullet44.png"));
		Sprite BroadSheet = new Sprite(loader.load("/Models/Misc/Broadside.png"));
		Sprite exSheet = new Sprite(loader.load("/Models/Misc/Explosion2.png"));
		
		Broad = BroadSheet.crop(0, 0, 32, 32, 32, 32);

		ex1 = exSheet.crop(0, 0, 32, 32, 32, 32);
		ex2 = exSheet.crop(1, 0, 32, 32, 32, 32);
		ex3 = exSheet.crop(2, 0, 32, 32, 32, 32);
		ex4 = exSheet.crop(3, 0, 32, 32, 32, 32);
		ex5 = exSheet.crop(4, 0, 32, 32, 32, 32);
		ex6 = exSheet.crop(5, 0, 32, 32, 32, 32);
		ex7 = exSheet.crop(6, 0, 32, 32, 32, 32);
		ex8 = exSheet.crop(7, 0, 32, 32, 32, 32);
		ex9 = exSheet.crop(8, 0, 32, 32, 32, 32);
		ex10 = exSheet.crop(9, 0, 32, 32, 32, 32);
		
		Goliath = basicGolSheet.crop(0, 0, 32, 32, 32, 32);
		Goliath1 = basicGolSheet.crop(1, 0, 32, 32, 32, 32);
		Goliath2 = basicGolSheet.crop(2, 0, 32, 32, 32, 32);
		Goliath3 = basicGolSheet.crop(3, 0, 32, 32, 32, 32);
		Goliath4 = basicGolSheet.crop(4, 0, 32, 32, 32, 32);
		Goliath5 = basicGolSheet.crop(5, 0, 32, 32, 32, 32);
		Goliath6 = basicGolSheet.crop(6, 0, 32, 32, 32, 32);
		Goliath7 = basicGolSheet.crop(7, 0, 32, 32, 32, 32);
		Goliath8 = basicGolSheet.crop(8, 0, 32, 32, 32, 32);
		Goliath9 = basicGolSheet.crop(9, 0, 32, 32, 32, 32);
		Goliath10 = basicGolSheet.crop(10, 0, 32, 32, 32, 32);
		Goliath11 = basicGolSheet.crop(11, 0, 32, 32, 32, 32);

		DeathSpavager = basicDSSheet.crop(0, 0, 32, 32, 32, 32);
		DeathSpavager1 = basicDSSheet.crop(1, 0, 32, 32, 32, 32);
		DeathSpavager2 = basicDSSheet.crop(2, 0, 32, 32, 32, 32);
		DeathSpavager3 = basicDSSheet.crop(3, 0, 32, 32, 32, 32);
		
		StealthDrone = SDSheet.crop(0, 0, 64, 64, 64, 64);
		StealthDrone1 = SDSheet.crop(1, 0, 64, 64, 64, 64);
		StealthDrone2 = SDSheet.crop(2, 0, 64, 64, 64, 64);
		StealthDrone3 = SDSheet.crop(3, 0, 64, 64, 64, 64);

		XBullet44 = XBulNSheet.crop(0, 0, 32, 32, 32, 32);
		XBullet441 = XBulNSheet.crop(1, 0, 32, 32, 32, 32);
		XBullet442 = XBulNSheet.crop(2, 0, 32, 32, 32, 32);
		XBullet443 = XBulNSheet.crop(3, 0, 32, 32, 32, 32);
		
		meteor = Meteor.crop(0, 0, 32, 32, 32, 32);
		
		Cash = monSheet.crop(0, 0, 32, 32, 32, 32);
		Cash1 = monSheet.crop(1, 0, 32, 32, 32, 32);
		
		SupplyShip = supplySheet.crop(0, 0, 32, 32, 32, 32);
		
		Shipp = spSheet.crop(0, 0, 32, 32, 32, 32);
		Shipp1 = spSheet.crop(1, 0, 32, 32, 32, 32);
		
		player = shipSheet.crop(0, 0, sw, sh, sw, sh);
		playerLeft = shipSheet.crop(1, 0, sw, sh, sw, sh);
		playerBack = shipSheet.crop(2, 0, sw, sh, sw, sh);
		playerRight = shipSheet.crop(3,  0, sw, sh, sw, sh);
		playerR1 = shipRSheet.crop(0, 0, sw, sh, sw, sh);
		playerR2 = shipRSheet.crop(1, 0, sw, sh, sw, sh);
		playerR3 = shipRSheet.crop(2, 0, sw, sh, sw, sh);
		playerR4 = shipRSheet.crop(3, 0, sw, sh, sw, sh);
		
		neutral = basicNSheet.crop(0, 0, 33, 32, 33, 32);
		neutral1 = basicNSheet.crop(1, 0, 33, 32, 33, 32);
		neutral2 = basicNSheet.crop(2, 0, 33, 32, 33, 32);
		neutral3 = basicNSheet.crop(3, 0, 32, 32, 32, 32);
	
		friend = basicFSheet.crop(0, 0, 33, 32, 33, 32);
		friend1 = basicFSheet.crop(1, 0, 33, 32, 33, 32);
		friend2 = basicFSheet.crop(2, 0, 33, 32, 33, 32);
		friend3 = basicFSheet.crop(3, 0, 32, 32, 32, 32);
		
		Enemy = basicESheet.crop(0, 0, 33, 32, 33, 32);
		Enemy1 = basicESheet.crop(1, 0, 33, 32, 33, 32);
		Enemy2 = basicESheet.crop(2, 0, 33, 32, 33, 32);
		Enemy3 = basicESheet.crop(3, 0, 32, 32, 32, 32);
		
		ld = ldSheet.crop(0, 0, rowSize, colSize, w, h);
		ld1 = ldSheet.crop(1, 0, rowSize, colSize, w, h);
		ld2 = ldSheet.crop(2, 0, rowSize, colSize, w, h);
		ld3 = ldSheet.crop(3, 0, rowSize, colSize, w, h);
		ld4 = ldSheet.crop(4, 0, rowSize, colSize, w, h);
		ld5 = ldSheet.crop(5, 0, rowSize, colSize, w, h);
		ld6 = ldSheet.crop(6, 0, rowSize, colSize, w, h);
		ld7 = ldSheet.crop(7, 0, rowSize, colSize, w, h);
		ld8 = ldSheet.crop(8, 0, rowSize, colSize, w, h);
		ld9 = ldSheet.crop(9, 0, rowSize, colSize, w, h);
		ld10 = ldSheet.crop(10, 0, rowSize, colSize, w, h);
		ld11 = ldSheet.crop(11, 0, rowSize, colSize, w, h);
		ld12 = ldSheet.crop(12, 0, rowSize, colSize, w, h);
		ld13 = ldSheet.crop(13, 0, rowSize, colSize, w, h);
		ld14 = ldSheet.crop(14, 0, rowSize, colSize, w, h);
		ld15 = ldSheet.crop(15, 0, rowSize, colSize, w, h);
		
		meteor = Meteor.crop(0, 0, 32, 32, 32, 32);
		
		Cash = monSheet.crop(0, 0, 32, 32, 32, 32);
		Cash1 = monSheet.crop(1, 0, 32, 32, 32, 32);
		
		Shipp = spSheet.crop(0, 0, 32, 32, 32, 32);
		Shipp1 = spSheet.crop(1, 0, 32, 32, 32, 32);
		
		rb = rbSheet.crop(0, 0, rowSize, colSize, w, h);
		rb1 = rbSheet.crop(1, 0, rowSize, colSize, w, h);
		rb2 = rbSheet.crop(2, 0, rowSize, colSize, w, h);
		rb3 = rbSheet.crop(3, 0, rowSize, colSize, w, h);
		rb4 = rbSheet.crop(4, 0, rowSize, colSize, w, h);
		rb5 = rbSheet.crop(5, 0, rowSize, colSize, w, h);
		rb6 = rbSheet.crop(6, 0, rowSize, colSize, w, h);
		rb7 = rbSheet.crop(7, 0, rowSize, colSize, w, h);
		
		rb8 = rbSheet.crop(8, 0, rowSize, colSize, w, h);
		rb9 = rbSheet.crop(9, 0, rowSize, colSize, w, h);
		rb10 = rbSheet.crop(10, 0, rowSize, colSize, w, h);
		rb11 = rbSheet.crop(11, 0, rowSize, colSize, w, h);
		rb12 = rbSheet.crop(12, 0, rowSize, colSize, w, h);
		rb13 = rbSheet.crop(13, 0, rowSize, colSize, w, h);
		rb14 = rbSheet.crop(14, 0, rowSize, colSize, w, h);
		rb15 = rbSheet.crop(15, 0, rowSize, colSize, w, h);
		rb16 = rbSheet.crop(16, 0, rowSize, colSize, w, h);
		rb17 = rbSheet.crop(17, 0, rowSize, colSize, w, h);
		rb18 = rbSheet.crop(18, 0, rowSize, colSize, w, h);
		rb19 = rbSheet.crop(19, 0, rowSize, colSize, w, h);
		rb20 = rbSheet.crop(20, 0, rowSize, colSize, w, h);
		rb21 = rbSheet.crop(21, 0, rowSize, colSize, w, h);
		rb22 = rbSheet.crop(22, 0, rowSize, colSize, w, h);
		rb23 = rbSheet.crop(23, 0, rowSize, colSize, w, h);
		rb24 = rbSheet.crop(24, 0, rowSize, colSize, w, h);
		rb25 = rbSheet.crop(25, 0, rowSize, colSize, w, h);
		rb26 = rbSheet.crop(26, 0, rowSize, colSize, w, h);
		rb27 = rbSheet.crop(27, 0, rowSize, colSize, w, h);
		
		ArenaBarrier = lSheet.crop(0, 0, 32, 32, 32, 32);
		ArenaBarrier1 = lSheet.crop(1, 0, 32, 32, 32, 32);
		ArenaBarrier2 = lSheet.crop(2, 0, 32, 32, 32, 32);
		
		Mexp = miniSheet.crop(0, 0, rowSize, colSize, w, h);
		Mexp1 = miniSheet.crop(1, 0, rowSize, colSize, w, h);
		Mexp2 = miniSheet.crop(2, 0, rowSize, colSize, w, h);
		Mexp3 = miniSheet.crop(3, 0, rowSize, colSize, w, h);
		Mexp4 = miniSheet.crop(4, 0, rowSize, colSize, w, h);
		Mexp5 = miniSheet.crop(5, 0, rowSize, colSize, w, h);
		
		Space = spaceSheet.crop(0, 0, 32, 32, 32, 32);
		Space1 = spaceSheet.crop(1, 0, 32, 32, 32, 32);
		Space2 = spaceSheet.crop(2, 0, 32, 32, 32, 32);
		Space3 = spaceSheet.crop(3, 0, 32, 32, 32, 32);
		
		Stars = starSheet.crop(0, 0, 32, 32, 32, 32);
		Star1 = starSheet.crop(1, 0, 32, 32, 32, 32);
		Star2 = starSheet.crop(2, 0, 32, 32, 32, 32);
		Star3 = starSheet.crop(3, 0, 32, 32, 32, 32);
		Star4 = starSheet.crop(4, 0, 32, 32, 32, 32);
		
		exp = expSheet.crop(0, 0, 32, 32, 32, 32);
		exp1 = expSheet.crop(1, 0, 32, 32, 32, 32);
		exp2 = expSheet.crop(2, 0, 32, 32, 32, 32);
		exp3 = expSheet.crop(3, 0, 32, 32, 32, 32);
		exp4 = expSheet.crop(4, 0, 32, 32, 32, 32);
		exp5 = expSheet.crop(5, 0, 32, 32, 32, 32);
		exp6 = expSheet.crop(6, 0, 32, 32, 32, 32);
		exp7 = expSheet.crop(7, 0, 32, 32, 32, 32);
		exp8 = expSheet.crop(8, 0, 32, 32, 32, 32);
		exp9 = expSheet.crop(9, 0, 32, 32, 32, 32);
		exp10 = expSheet.crop(10, 0, 32, 32, 32, 32);
		exp11 = expSheet.crop(11, 0, 32, 32, 32, 32);

		playerLaser = laserSheet.crop(0, 0, 5, lh, 5, lh);
		playerLaser1 = laserSheet.crop(1, 0, 5, lh, 5, lh);
		playerLaser2 = laserSheet.crop(2, 0, 5, lh, 5, lh);
		playerLaser3 = laserSheet.crop(3, 0, 5, lh, 5, lh);
	}
}
