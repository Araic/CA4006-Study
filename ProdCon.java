import java.util.*;
import java.util.concurrent.*;

public class ProdCon{
	Semaphore elements = new Semaphore(0);
	Semaphore spaces = new Semaphore(20);
	Semaphore mutex = new Semaphore(1);
	int in = 0;
	int out = 0;
	int[] buff = new int[20];

	void produce(){
		while(true){
			try{
			spaces.acquire();
			mutex.acquire();
			System.out.println("Produce in:"+in);
			buff[in] = 1;
			in=(in+1+20)%20;
			mutex.release();
			elements.release();
			}catch(Exception e){e.printStackTrace();}
		}
	}
	void consume(){
		while(true){
			try{
			elements.acquire();
			mutex.acquire();
			System.out.println("Consume in:"+in);
			int element = buff[in];
			in = (in-1+20)%20;
			mutex.release();
			spaces.release();
			}catch(Exception e){e.printStackTrace();}
		}
	}

	public static void main(String args[]){
		ProdCon pc = new ProdCon();
		Thread t1 = new Thread(){
			public void run(){
				pc.consume();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				pc.produce();
			}
		};
		t1.start();
		t2.start();
	}
}
