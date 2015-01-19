package jp.ac.kit.OnOff;

class LineSensor extends Thread {
	private static LineSensor aLineSensor = new LineSensor();
	private LineSensor() {}
	
	static LineSensor getInstance() { 
    	return aLineSensor; 
    }
	void setPoints(int onPoint, int offPoint) { 
		this.onPoint = onPoint;
		this.offPoint = offPoint; 
	}
	void readyGo() {
		aBody = Body.getInstance(); 
		aLogger = Logger.getInstance(); 
		aSwitch = true;
		start(); 
	}
	void halt() { 
		aSwitch = false; 
	}
	public void run() { 
		while(aSwitch) {
			if(aLogger.readValue() < onPoint)
				aBody.turnLeft(); // 黒色なら、左旋回せよ (左エッジ走行時)
			else if(aLogger.readValue() > offPoint)
				aBody.turnRight(); // 白色なら、右旋回せよ (左エッジ走行時)
            else
                //aBody.runStraight();
            try{ 
            	sleep(10); 
            } catch(Exception e){
            	
            } 
		}
		Body.getInstance().halt(); 
	}
	
    private int onPoint = 20;
    private int offPoint = 28;
    private boolean aSwitch;
    private Body aBody;
    private Logger aLogger;

}