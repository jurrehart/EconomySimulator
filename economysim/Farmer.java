package economysim;
import java.util.ArrayList;
import economysim.Person;


public class Farmer extends Person {

	public Farmer(String name)
	{
		//constructor
		//invoke person constructor
		super(name);

	}
	public void run()
	{
		//super.run();
		double totalPrice = ((averagePrice.get("wood")/3) * profitFactor) + 1;
		averagePrice.put("food", (int) (totalPrice));
		//every tick make food
		if(tools > 0 && wood > 0 && food > 0)
		{
			//we can do stuff
			wood --;
			food += 5;
			
			//break tools
			if((int)(Math.random() * 10) < 5)
			{
				tools--;
			}
		}
		else if(wood > 0 && food > 0)
		{
			//has no tools
			wood --;
			food += 3;
		}
	}
	public void print()
	{
		//lets print the resources the farmer has right now
		super.print();
		System.out.print("Tools: ");
		System.out.println(tools);
		System.out.print("Wood: ");
		System.out.println(wood);
		System.out.println("");
		
	}
	public ArrayList<Offer> createOffer()
	{
		ArrayList<Offer> ret = new ArrayList<Offer>();
		//sell everything above limit which is hardcoded to 5 right now
		if(food > goodBounds.get("food"))
		{
			for(int i = 0; i < (food - goodBounds.get("food")); ++i)
			{
				Offer newOffer = new Offer(name, averagePrice.get("food"), "food");
				ret.add(newOffer);
			}
			food = goodBounds.get("food");
		}
		return ret;
	}
	public ArrayList<Bid> createBid()
	{
		//farmer can just make food if they have wood so try to find wood first
		ArrayList<Bid> ret = new ArrayList<Bid>();
		if(tools < 1)
		{
			Bid newBid = new Bid(name, averagePrice.get("tools"), "tools");
			ret.add(newBid);
		}
		if(wood < goodBounds.get("wood"))
		{
			for(int i = 0; i < goodBounds.get("wood") - wood; ++i)
			{
				//need wood
				Bid newBid = new Bid(name, averagePrice.get("wood"), "wood");
				ret.add(newBid);
			}
		}
		if(food < 1)
		{
			//WE'RE DESPERATE!!!
			Bid newBid = new Bid(name, averagePrice.get("food"), "food");
			ret.add(newBid);
		}
		return ret;
	}
	public String getProfession()
	{
		return "Farmer";
	}

}
