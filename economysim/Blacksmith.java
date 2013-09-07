package economysim;
import java.util.ArrayList;

import economysim.Person;

public class Blacksmith extends Person {
	protected int metalLimit;
	public Blacksmith(String name)
	{
		super(name);
		metalLimit = 5;
	}
	public void run()
	{
		super.run();
		if(food > 0)
		{
			tools+=metal;
			metal = 0;
		}
	}
	public void print()
	{
		super.print();

		System.out.print("Tools: ");
		System.out.println(tools);
		System.out.print("Metal: ");
		System.out.println(metal);
		System.out.println("");
	}
	public ArrayList<Offer> createOffer()
	{
		ArrayList<Offer> ret = new ArrayList<Offer>();
		for(int i = 0; i < this.tools; ++i)
		{
			Offer newOffer = new Offer(name, 10, "tools");
			ret.add(newOffer);
		}
		return ret;
	}
	public ArrayList<Bid> createBid()
	{
		//buy ore and food
		ArrayList<Bid> ret = new ArrayList<Bid>();
		ret.addAll(super.createBid());
		if(metal < metalLimit)
		{
			for(int i = 0; i < metalLimit - metal; ++i)
			{
				Bid newBid = new Bid(name, 10, "metal");
				ret.add(newBid);
			}
		}


		return ret;
	}
	public String getProfession()
	{
		return "Blacksmith";
	}
	
	
}
