package jp.ac.kit.OnOff;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

class Body {
    private static Body aBody = new Body();
    private Body() {}
    static Body getInstance() { 
    	return aBody; 
    }
    void turnRight() { 
    	// 右旋回する 
    	LWheel.setSpeed(200); 
    	RWheel.setSpeed(50); 
    	LWheel.forward(); 
    	RWheel.forward();
    }
    void turnLeft() { 
    	// 左旋回する 
    	LWheel.setSpeed(50); 
    	RWheel.setSpeed(200); 
    	LWheel.forward(); 
    	RWheel.forward();
    }
    void runStraight() { 
    	// 直進する 
    	LWheel.setSpeed(200); 
    	RWheel.setSpeed(200);
    	LWheel.forward();
    	RWheel.forward();
    }
    void halt() {
   	 	// 停止する
   	 	LWheel.stop(true);
   	 	RWheel.stop();
	}
 	static NXTRegulatedMotor LWheel = Motor.C; // 左輪
	static NXTRegulatedMotor RWheel = Motor.A; // 右輪 

}