public class LabyrinthDFS {
	protected Datas labyrinth[][];
	protected Datas start,end;
	protected int num;
	protected LinkedStack<Datas> stack;
	
	public LabyrinthDFS (Datas labyrinth[][],Datas start,Datas end)
	{
		this.labyrinth=labyrinth;
		this.start=start;
		this.end=end;
		this.num=0;
		stack=new LinkedStack<Datas>();
	}
	
	public void Find()
	{
		int cout=0;
		
		for(int i=0;i<labyrinth.length;i++)
			for(int j=0;j<labyrinth[0].length;j++)
			{
				if((j!=this.labyrinth[0].length-1&&this.labyrinth[i][j+1].isWalk==false&&this.labyrinth[i][j+1].isWhite==true))
					cout++;
				if(i!=0&&this.labyrinth[i-1][j].isWalk==false&&this.labyrinth[i-1][j].isWhite==true)
					cout++;
				if(j!=0&&this.labyrinth[i][j-1].isWalk==false&&this.labyrinth[i][j-1].isWhite==true)
					cout++;
				if(i!=this.labyrinth.length-1&&this.labyrinth[i+1][j].isWalk==false&&this.labyrinth[i+1][j].isWhite==true)
					cout++;
				if(cout>=3)
					labyrinth[i][j].isfork=true;
				cout=0;
			}
		find(start);
		System.out.println("一共有"+num+"条路径。");
	}
	public void find(Datas data)
	{
		stack.push(data);
		data.isWalk=true;
		if(data.x==end.x&&data.y==end.y)
		{
			System.out.println("找到出口。");
			LinkedStack stack2=new LinkedStack();
			String s="正确通路（";
			String str="";
			while(stack.isEmpty()==false)
			{
				Datas dataa=stack.pop();
				str="["+dataa.x+","+dataa.y+"]"+str;
				stack2.push(dataa);
			}
			System.out.println(s+str+"）");
			while(!stack2.isEmpty())
			{
				stack.push((Datas) stack2.pop());
			}
			num++;
		}
		else
		{
			if(data.y!=this.labyrinth[0].length-1&&
					this.labyrinth[data.x][data.y+1].isWalk==false&&
					this.labyrinth[data.x][data.y+1].isWhite==true)
			{
				find(labyrinth[data.x][data.y+1]);
			}
			if(data.x!=0&&
					this.labyrinth[data.x-1][data.y].isWalk==false&&
					this.labyrinth[data.x-1][data.y].isWhite==true)
			{
				find(labyrinth[data.x-1][data.y]);
			}
			if(data.x!=this.labyrinth.length-1&&
					this.labyrinth[data.x+1][data.y].isWalk==false&&
					this.labyrinth[data.x+1][data.y].isWhite==true)
			{
				find(labyrinth[data.x+1][data.y]);
			}
			if(data.y!=0&&
					this.labyrinth[data.x][data.y-1].isWalk==false&&
					this.labyrinth[data.x][data.y-1].isWhite==true)
			{
				find(labyrinth[data.x][data.y-1]);
			}	
		}
		stack.pop();
		data.isWalk=false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Datas  data[][]=new Datas[8][12];
		boolean bool[][]={
				{false,false,false,false,false,false,false,false,false,false,false,false},
				{true,true,false,true,true,true,true,true,true,true,true,false},
				{false,true,false,true,false,false,false,true,false,false,false,false},
				{false,true,true,true,true,true,true,true,true,true,true,false},
				{false,true,false,false,false,false,false,true,false,false,true,true},
				{false,true,true,true,true,true,true,true,false,false,true,false},
				{false,true,false,false,false,false,false,true,true,true,true,false},
				{false,false,false,false,false,false,false,false,false,false,false,false}};
		for(int i=0;i<8;i++)
			for(int j=0;j<12;j++)
			{
				data[i][j]=new Datas(bool[i][j],i,j);
			}
		LabyrinthDFS laby=new LabyrinthDFS (data,data[4][11],data[1][0]);
		laby.Find();
	}

}


class Datas {

		public int x,y;
		public boolean isWhite;
		public boolean isWalk;
		public boolean isfork;
		public boolean isExerting;
		
		public Datas(boolean isWhite,int x,int y)
		{
			this.isWhite=isWhite;
			this.x=x;
			this.y=y;
		}
}
