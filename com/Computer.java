package com;
/*
 * �˻���ս���㷨,����Ľӿ���getQuan()��getSame()��getQuan()���˻���ս�Ǽ������ÿ�����Ȩֵ
 * ȡ���ĵ�ֵ��Ϊ����㣬getSame()�������ж���Ӯ�Ľӿ�
 * */
public class Computer {
	 // q�������� O�����     ������Ȩֵ
	private static int Q2O=1;
	private static int Q2=10;
	private static int Q3O=10;
	private static int Q3=100;
	private static int Q4O=100;
	private static int Q4=2000;
	private static int Q5=100000;
	private static int level=1;
	private int a,b;
	static public int num[][]=new int[15][15];
	int c[][]=new int[2][2];//��¼i��j����ʼλ��
	private int troub1=0,troub2=0,t1,t2;
	public void trouble(){//����ģʽ��ģ���岽����
		level=3;
		Q2O=1;
		Q2=10;
		Q3O=10;
		Q3=100;
		Q4O=100;
		Q4=2000;
		Q5=100000;
	}
	public void trouble1() {//�е�ģʽ
		level=2;
		Q2O=30;
		Q2=100;
		Q3O=300;
		Q3=1200;
		Q4O=1100;
		Q4=2000;
		Q5=10000;
	}
	public void easy(){//��ģʽ��ģ����������
		level=1;
		Q2O=1;
		Q2=10;
		Q3O=10;
		Q3=100;
		Q4O=100; 
		Q4=2000;
		Q5=100000;
	}
	public void copyChess(int num1[][]){//���Ƶ�ǰ����	
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)
				num[i][j]=num1[i][j];
	}
	boolean generator(int i,int j){//����ģʽ
		//��֦���ھӵĶ����ǣ������������������һ����Ϊ�յĵ㼴��
		//������ b[7,7] ��һ���ӣ���ô b[6,7]�������ھӣ�b[5,7] Ҳ�ǣ����� b[4,7] �Ͳ��ǣ���Ϊ���������  
		//�������Ҹ��Կճ�3�񣬣�������ռ�õĻ�  ���true
		int min_i,min_j,max_i,max_j;
		boolean k=false;
		min_i=i-2>=0?i-2:0;
		max_i=i+2<=14?i+2:0;
		min_j=j-2>=0?j-2:14;
		max_j=j+2<=14?j+2:14;
		for(i=min_i;i<=max_i;i++)
			{for(j=min_j;j<=max_j;j++)
				if(num[i][j]!=0){k=true;break;}
			}
		return k;
	}
	class struct//����ʽ������struct
	{
		int i;
		int j;
		int value=0;
		
	}
	public  int alphaBeta1(int chess,int alpha ,int beta,int i,int j){
		//ģʽѡ��
		if(level==3)return alphaBeta(1, 5, alpha,  beta,i, j);
		else if(level==1)return alphaBeta(1, 1, alpha,  beta,i, j);
		else return getQuan(i,j);
	}
	
	/*����ʽ�������������ŵĵ����˴��֮�����ǾͿ��԰���
	* �����ߵͽ��������ˡ�����ʵ�ֵ�ʱ���Ǹ��ݰ��� ���壬���ģ�˫�������������� ��˳��������ġ�
	* ����Ѷ�Ҳ�Ƚϸߣ��ҾͰ������˳������ȡ���ŵ����������һ��ѭ�����������˻���m������������������*/
	private int alphaBeta(int chess, int depth, int alpha, int beta,int i,int j){ 
	//alphaBeta��֦������С�������˹����ܵ�һ�� ģ���岽����
		int best;//����ֵ
		if( getQuan(i,j,chess%2+1)>=Q5)//��������
		{
			return getComputerQuan()-getPeopleQuan();//���Ե�Ȩֵ��ȥ�˵�Ȩֵ
		}
		else if(depth==0)//depthΪ0 �������ľ�ͷ
		{
				//System.out.println(getComputerQuan()-getPeopleQuan());
			return getComputerQuan()-getPeopleQuan();	
		}
		else {
			struct bestson[]=new struct[225];//Ѱ�����ŵ�����ڵ�
			int cnt=0;
			if(chess==2)//��������
			{
				int now;//һ����¼��ǰֵ������
				for( i = 0;i<= 14;i++)
					for(j = 0;j<=14;j++)
					{
						if(num[i][j]==0)
						{
							if(alpha>=beta)return alpha;//alpha��֦
							else if(generator( i,j)==true){	//���ڼ�֦
								num[i][j]=2;
					// now=getQuan(i,j,chess);
								now=getQuan(i,j,1)+getQuan(i,j,2);
								num[i][j]=0;
								bestson[cnt]= new struct();//���
								bestson[cnt].i=i;
								bestson[cnt].j=j;
								bestson[cnt++].value=now;
							}
						}
					}
				struct t=new struct();
				for(i=0;i<cnt;i++)//ð������
					for(j=i+1;j<cnt;j++)
						if(bestson[i].value<bestson[j].value)
						{
							t=bestson[i];
							bestson[i]=bestson[j];
							bestson[j]=t;
						}
				cnt=cnt<5?cnt:5;
				for(i=0;i<cnt;i++)//����ʽ������ȡǰ��
				{
					num[bestson[i].i][bestson[i].j]=2;
					now=alphaBeta(1,depth-1,alpha,beta,bestson[i].i,bestson[i].j);
					if(now>alpha)alpha=now;
					num[bestson[i].i][bestson[i].j]=0;
				}
			best=alpha;	
			}
			else {//������
				int now;
				cnt=0;
				for( i = 0;i<= 14;i++)
					for(j = 0;j<=14;j++)
					{
						if(num[i][j]==0)
						{
							if(alpha>=beta)return beta;//beat��֦
							else if(generator(i,j)==true){//���ڼ�֦
								num[i][j]=1;
								now=getQuan(i,j,1)+getQuan(i,j,2);
								num[i][j]=0;
								bestson[cnt]= new struct();//���
								bestson[cnt].i=i;
								bestson[cnt].j=j;
								bestson[cnt++].value=now;
							}
						}
					}
				struct t=new struct();
				for(i=0;i<cnt;i++)//ð������
					for(j=i+1;j<cnt;j++)
						if(bestson[i].value<bestson[j].value)
						{
							t=bestson[i];
							bestson[i]=bestson[j];
							bestson[j]=t;
						}
				cnt=cnt<5?cnt:5;
				for(i=0;i<cnt;i++)//����ʽ������ȡǰ��
				{
					num[bestson[i].i][bestson[i].j]=1;
					now=alphaBeta(2,depth-1,alpha,beta,bestson[i].i,bestson[i].j);
					if(now<beta)beta=now;  
					num[bestson[i].i][bestson[i].j]=0;
				}
			best=beta;	
			}
		}
		return best;
	} 
	private int getPeopleQuan(){
		int sum=0;
		for(int i=0;i<14;i++)
		{
			for(int j=0;j<=14;j++)
				if(num[i][j]==1)sum+=getQuan(i,j,1);
		}
		return sum;
	}
	private int getComputerQuan(){
		int sum=0;
		for(int i=0;i<14;i++)
		{
			for(int j=0;j<=14;j++)
				if(num[i][j]==2)sum+=getQuan(i,j,2);
		}
		return sum;
	}
	public int getQuan(int i,int j,int chess){
		int q=0;
		q+=getQuan0(i,j,chess);//4�������Ȩֵ
		q+=getQuan90(i,j,chess);
		q+=getQuan135(i,j,chess);
		q+=getQuan45(i,j,chess);
		return q;
	}
	private int getQuan0(int i,int j,int chess){
		a=1;
		b=0;
		if(chess==1)return getquanPeople(i,j);
		else return getquanComputer(i,j);
	}
	private int getQuan90(int i,int j,int chess){
		a=0;
		b=1;
		if(chess==1)return getquanPeople(i,j);
		else return getquanComputer(i,j);
	}
	private int getQuan135(int i,int j,int chess){
		a=1;
		b=-1;
		if(chess==1)return getquanPeople(i,j);
		else return getquanComputer(i,j);
	}
	private int getQuan45(int i,int j,int chess){
		a=1;
		b=1;
		if(chess==1)return getquanPeople(i,j);
		else return getquanComputer(i,j);
	}
	private void initc(int i,int j){
		c[0][0]=c[0][1]=i;
		c[1][0]=c[1][1]=j;
	}
	private int getquanPeople(int i,int j){//�����Լ��ͶԷ���Ȩֵ��Ӿ������Ȩֵ
		int samechesSnums=0;//��ͬ���Ӹ���
		int blankNums=0;
		int qs=0;
		initc(i,j);
		//�����˵�Ȩֵ
		samechesSnums=getSamechessNums(1);//�õ����ӵ�����
		if(c[0][0]>=0&&c[0][0]<=14&&c[1][0]>=0&&c[1][0]<=14)//�õ��յ�����
			if(num[c[0][0]][c[1][0]]==0)blankNums++;
		
		if(c[0][1]>=0&&c[0][1]<=14&&c[1][1]>=0&&c[1][1]<=14)	
			{
			if(num[c[0][1]][c[1][1]]==0)blankNums++;
			}
			qs=getQuanEqual(samechesSnums,blankNums);	
		return qs;
	}
	private int getquanComputer(int i,int j){
		int samechessNumf=0;//��ͬ���ӵ�����
		int blankNumf=0;//���߱��¼���
		int qf=0;
		//�����Լ���Ȩֵ
				initc(i,j);
				samechessNumf=getSamechessNums(2);//�õ����ӵ�����
				if(c[0][0]>=0&&c[0][0]<=14&&c[1][0]>=0&&c[1][0]<=14)//�õ��յ�����
					if(num[c[0][0]][c[1][0]]==0)blankNumf++;
				
				if(c[0][1]>=0&&c[0][1]<=14&&c[1][1]>=0&&c[1][1]<=14)	
					if(num[c[0][1]][c[1][1]]==0)blankNumf++;
				qf=getQuanEqual(samechessNumf,blankNumf);
				return qf;
	}
	private int  getSamechessNums(int id){ //�жϼ�������
	//�õ����ӵ�����
		int num1=1;
		c[0][0]+=a;//�ҷ��򡢡�a1 b0
		c[1][0]+=b;
		while(c[0][0]>=0&&c[0][0]<=14&&num1<5&&c[1][0]>=0&&c[1][0]<=14)
		{
			if(num[c[0][0]][c[1][0]]!=id)break;
			num1++;
			c[0][0]+=a;
			c[1][0]+=b;
		}
		//����
		c[0][1]-=a;
		c[1][1]-=b;
		while(c[1][1]>=0&&c[1][1]<=14&&num1<5&&c[0][1]>=0&&c[0][1]<=14)
		{
			if(num[c[0][1]][c[1][1]]!=id)break;
			num1++;
			c[0][1]-=a;
			c[1][1]-=b;
		}
		return num1;
	}
	private int getQuanEqual(int chess,int blank){//��Ȩֵ
		if(chess==2&&blank==1)return Q2O;
		else if(chess==2&&blank==2)return Q2;
		else if(chess==3&&blank==1)return Q3O;
		else if(chess==3&&blank==2)return Q3;
		else if(chess==4&&blank==1)return Q4O;
		else if(chess==4&&blank==2)return Q4;
		else if(chess==5)return Q5;
		else return 0;
	}
	public int getSame(int i,int j,int id){//�ж���Ӯ
		int q=0,k=0;
		a=1;b=0;initc(i,j);
		k=getSamechessNums(id);
		q=q<k?k:q;
		
		a=0;b=1;initc(i,j);
		k=getSamechessNums(id);
		q=q<k?k:q;
	
		a=1;
		b=1;initc(i,j);
		k=getSamechessNums(id);
		q=q<k?k:q;
	
		a=1;b=-1;initc(i,j);
		k=getSamechessNums(id);
		q=q<k?k:q;
		return q;
	}
	
	
	
	
	
		//��ʼ���ģ��е��˻�
	private int getQuan(int i,int j){//ÿ��λ�õ�Ȩֵ
		int q=0;
		q+=getQuan0(i,j);//4�������Ȩֵ
		q+=getQuan90(i,j);
		q+=getQuan135(i,j);
		q+=getQuan45(i,j);
		return q;
	}
	private int getQuan0(int i,int j){
		a=1;
		b=0;
		return getquanALL(i,j);
	}
	private int getQuan90(int i,int j){
		a=0;
		b=1;
		return getquanALL(i,j);
	}
	private int getQuan135(int i,int j){
		a=1;
		b=-1;
		return getquanALL(i,j);
	}
	private int getQuan45(int i,int j){
		a=1;
		b=1;
		return getquanALL(i,j);
	}
	//�����Լ��ͶԷ���Ȩֵ��Ӿ������Ȩֵ
	private int getquanALL(int i,int j){
		int samechesSnums=0;//��ͬ���Ӹ���
		int samechessNumf=0;
		int blankNums=0;//ʣ���ȱ
		int blankNumf=0;
		int q=0,qs=0,qf=0;
		initc(i,j);
		//�����˵�Ȩֵ
		samechesSnums=getSamechessNums(1);//�õ����ӵ�����
		if(c[0][0]>=0&&c[0][0]<=14&&c[1][0]>=0&&c[1][0]<=14)//�õ��յ�����
			if(num[c[0][0]][c[1][0]]==0)blankNums++;
		
		if(c[0][1]>=0&&c[0][1]<=14&&c[1][1]>=0&&c[1][1]<=14)	
			{
			if(num[c[0][1]][c[1][1]]==0)blankNums++;
			}
			qs=getQuanEqual(samechesSnums,blankNums);
			
			//�����Լ���Ȩֵ
			initc(i,j);
			samechessNumf=getSamechessNums(2);//�õ����ӵ�����
			if(c[0][0]>=0&&c[0][0]<=14&&c[1][0]>=0&&c[1][0]<=14)//�õ��յ�����
				if(num[c[0][0]][c[1][0]]==0)blankNumf++;
			
			if(c[0][1]>=0&&c[0][1]<=14&&c[1][1]>=0&&c[1][1]<=14)	
				if(num[c[0][1]][c[1][1]]==0)blankNumf++;
			qf=getQuanEqual(samechessNumf,blankNumf);
			q=qs+qf;
			
		return q;
	}
}
	