package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.*;

import customClasses.Item;
import customClasses.User;
import database.Database;

public class AddRecipe extends JFrame implements ChangeListener,ActionListener,ItemListener{
	User currentUser;
	JComboBox<String> itemCombo;
	JLabel recipeNameLabel,cuisineLabel;
	JTextField recipeName,cuisine;
	ArrayList<Item> itemList;
	String selectedIngredientValue;
	JButton addIngredient;
	JSpinner itemQuantitySpinner;
	int itemQuantity;
	JTable ingredientsTable;
	JScrollPane scrollPane;
	String[][] ingredientsData;
	String[] tableHead= {"Ingredients","Quantity"};
	int rowCount=0;
	AddRecipe(User user){
		ingredientsData = new String[30][2];
		this.currentUser = user;
		
		
		setSize(new Dimension(550,400));
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width-getSize().width)/2; 
		int y = (dim.height-getSize().height)/2; 
		
		recipeNameLabel = new JLabel("Recipe Name");
		recipeNameLabel.setBounds(100, 50, 100, 20);
		add(recipeNameLabel);
		
		recipeName = new JTextField();
		recipeName.setBounds(200,50,150,20);
		add(recipeName);
		
		cuisineLabel = new JLabel("Cuisine");
		cuisineLabel.setBounds(100, 80, 100, 20);
		add(cuisineLabel);
		
		cuisine = new JTextField();
		cuisine.setBounds(200,80,150,20);
		add(cuisine);
		
		JLabel itemName = new JLabel("Select Ingredient");
		itemName.setBounds(100,120,100,20);
		add(itemName);
		
		itemCombo = new JComboBox<String>();
		itemCombo.addItemListener(this);
		itemCombo.setBounds(200,120,100,20);
		itemList = Database.getItemList();
		itemCombo.addItem("--Select--");
		for(Item i: itemList) {
			itemCombo.addItem(i.toString());
		}
		add(itemCombo);
		
		itemQuantitySpinner = new JSpinner();
		itemQuantitySpinner.setBounds(310,120,50,20);
		SpinnerNumberModel model = new SpinnerNumberModel(1, 0, 50, 1);
		itemQuantitySpinner.setModel(model);
		((DefaultEditor) itemQuantitySpinner.getEditor()).getTextField().setEditable(false);
		itemQuantitySpinner.addChangeListener(this);
		add(itemQuantitySpinner);
		
		addIngredient = new JButton("Add");
		addIngredient.setBounds(370,120,80,20);
		addIngredient.addActionListener(this);
		add(addIngredient);
		
		JLabel createItem = new JLabel("Item not found? Create new Item here..");
		createItem.setBounds(100,150,300,20);
		createItem.setForeground(Color.BLUE.darker());
		createItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(createItem);
		
		ingredientsTable = new JTable(ingredientsData,tableHead) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
		}
			};
		
		scrollPane = new JScrollPane(ingredientsTable);
		scrollPane.setBounds(100,170,250,150);
		add(scrollPane);
		
		setLocation(x,y);
		setVisible(true);
	}
	
	public static void main(String[] arg) {
		new AddRecipe(new User());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
	
	private void generateTable() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==addIngredient) {
			
			itemQuantity = (Integer)itemQuantitySpinner.getValue();
			ingredientsData[rowCount][0]=selectedIngredientValue;
			ingredientsData[rowCount][1]=String.valueOf(itemQuantity);
			rowCount++;
			generateTable();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==itemCombo && e.getStateChange()==ItemEvent.SELECTED) {
			selectedIngredientValue = (String)itemCombo.getSelectedItem();
			System.out.println(((String)itemCombo.getSelectedItem()));
		}
	}

}
