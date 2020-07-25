package customClasses;

import java.util.Date;

public class Item {
	int itemId;
	Date purchaseDate;
	Item( int id, Date date ){
		this.itemId = id;
		this.purchaseDate = date;
	}
}
