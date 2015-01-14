package jp.ac.kit.OnOff;

public class OnOff {

	private static int onPoint = 20;
	private static int offPoint = 28;
	private static long miliSecond = 2000;
	private static int maxSize = (int)miliSecond; // max log size

	public static void main(String[] args) {
		LineSensor.getInstance().setPoints(onPoint, offPoint); 
		LineSensor.getInstance().readyGo();
		Logger.getInstance().readyGo(maxSize);
		try{ 
			Thread.sleep(miliSecond); 
		} catch(Exception e){
			
		}
		LineSensor.getInstance().halt(); 
		Logger.getInstance().stop();
	}
}
