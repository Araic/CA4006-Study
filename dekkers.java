class dekkers {
	int wantq = 0;
	int wantp = 0;
	int turn = 1;

	void p(){
		System.out.println("p non-CS");
		wantp = 1;
		System.out.println(wantq);
		while(wantq==1){
			if(turn==2){
				wantp = 0;
				while(turn==2){Thread.currentThread().yield();};
			}
			wantp = 1;
		}
		System.out.println("p CS");
		turn = 2;
		wantp = 0;
	}

	void q(){
		System.out.println("q non-CS");
		wantq = 1;
		while(wantp==1){
			if(turn ==1){
				wantq = 0;
				while(turn==1){Thread.currentThread().yield();};
			}
			wantq = 1;
		}
		System.out.println("q CS");
		turn = 1;
		wantq = 0;
	}
	
	public static void main(String [ ] args) {
		dekkers d = new dekkers();		
		Thread t1 = new Thread(){
			public void run(){
				while(true)
					d.p();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				while(true)
					d.q();
			}
		};
		t1.start();
		t2.start();
		
	}
}

