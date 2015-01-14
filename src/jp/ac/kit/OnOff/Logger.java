package jp.ac.kit.OnOff;


import lejos.util.Datalogger;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;
import lejos.nxt.addon.ColorHTSensor;

class Logger extends Thread {
	
	private static Logger aLogger = new Logger(); 
	private Logger() {}
	
    private long start_time;
    private int maxSize;
    private int aReadValue = 0;
    private LogDataArray array;
    private boolean aSwitch;
    private ColorHTSensor aSensor = new ColorHTSensor(SensorPort.S3);

	static Logger getInstance() { 
		return aLogger; 
	}
	void readyGo(int maxSize) { // array size 
		this.maxSize = maxSize;
		start(); 
	}
	int readValue() { 
		return aReadValue; 
	}
	void stop() { 
		aSwitch = false;
	}
    public void run() {
       array = new LogDataArray(maxSize);
       aSwitch = true;
       start_time = System.currentTimeMillis();
       while(aSwitch) {
    	   aReadValue = aSensor.getRGBComponent(Color.RED); // 5mS for each reading 
    	   array.append(start_time, // start time
    			   System.currentTimeMillis(), // current time 
    			   aReadValue, // Red compornent color value 
    			   0
    	   );
           try{ 
        	   sleep(5); 
           } catch(Exception e){ 
        	   
           }
        }
        array.sendLog();
    }
}
class LogData{
	
    private long start_time;
    private long current_time;
    private int sensor_value;
    private int tacho_count;
    
    LogData(long start_time, long current_time, int sensor_value, int tacho_count) {
       this.start_time = start_time;
       this.current_time = current_time;
       this.sensor_value = sensor_value;
       this.tacho_count = tacho_count; 
    }
    float getF1() {
    	return (float)(current_time - start_time);
    }
    float getF2() {
    	return (float)sensor_value;
    }
    float getF3() {
    	return (float)tacho_count;
    }
    float getF4() {
    	return (float)0;
    }
}

class LogDataArray {
	
	private Datalogger datalog = new Datalogger(); 
	private LogData[] a; // reference to array 
	private int nElems; // number of data items
	
	LogDataArray(int maxSize) { 	// constructor
		a = new LogData[maxSize];	// create the array
		nElems = 0;					// no items yet
    }
    void append(long start_time, long current_time, int sensor_value, int tacho_count) {
       a[nElems] = new LogData(start_time, current_time, sensor_value, tacho_count);
       nElems++; 
    }
    void sendLog() {
       for(int i=0; i<nElems; i++)
           datalog.writeLog(a[i].getF1(), a[i].getF2(), a[i].getF3(), a[i].getF4());
       datalog.transmit();
    } 
}