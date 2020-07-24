package frontend;

import javax.swing.*;
import javax.swing.Box.Filler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;



public class Dashboard extends JFrame implements ActionListener{
	JButton manageFridge, manageItems, recipes,addFridge,organizeFridge,deleteFridge,createItem,deleteItem;
	JPanel manageFridgePane,itemPanel,recipePanel;
	JLabel fridgeHead,itemPanelhead;
	Dimension centerPaneButtonDimension = new Dimension( 150,100 );
	Dashboard(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width,dim.height);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setResizable(false);
		
		JPanel navi = new JPanel();
		navi.setLayout(new BoxLayout(navi, BoxLayout.Y_AXIS));
		navi.setPreferredSize(new Dimension(dim.width/4,dim.height));
		navi.setBackground(Color.red);
		
		navi.add(Box.createRigidArea(new Dimension(1,200)));
		
		manageFridge = new JButton("Manage Fridge");
		manageFridge.setAlignmentX(CENTER_ALIGNMENT);
		manageFridge.setMaximumSize(new Dimension(200,100));
		manageFridge.addActionListener(this);
		navi.add(manageFridge);
//		navi.add(Box.createVerticalGlue());
//		navi.add(Box.createRigidArea(new Dimension(1,100)));
		
		manageItems = new JButton("Manage Items");
		manageItems.setAlignmentX(CENTER_ALIGNMENT);
		manageItems.setMaximumSize(new Dimension(200,100));
		manageItems.addActionListener(this);
		navi.add(manageItems);
		
//		navi.add(Box.createVerticalGlue());
		
		recipes = new JButton("Recipes");
		recipes.setMaximumSize(new Dimension(200,100));
		recipes.setAlignmentX(CENTER_ALIGNMENT);
		recipes.addActionListener(this);
		navi.add(recipes);
		
		navi.add(Box.createRigidArea(new Dimension(3,200)));
		add(navi,BorderLayout.WEST);
		
		
		JPanel topPane = new JPanel( new FlowLayout() );
		topPane.setBackground(Color.GREEN);
		JLabel title = new JLabel("Smidge");
		topPane.add(title);
		add(topPane,BorderLayout.NORTH);
		
		manageFridgePane = new JPanel();
		manageFridgePane.setLayout(new BoxLayout(manageFridgePane,BoxLayout.Y_AXIS));
		manageFridgePane.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		manageFridgePane.setBackground(Color.cyan);
		manageFridgePane.setVisible(false);
		
		fridgeHead = new JLabel("Manage Fridges");
		fridgeHead.setAlignmentX(CENTER_ALIGNMENT);
		manageFridgePane.add(fridgeHead);
		
		manageFridgePane.add(Box.createRigidArea(new Dimension(0,100)));
		
		addFridge = new JButton("Add Fridge");
		addFridge.setMaximumSize(centerPaneButtonDimension);
		addFridge.setAlignmentX(CENTER_ALIGNMENT);
		manageFridgePane.add(addFridge);
		
		manageFridgePane.add(Box.createRigidArea(new Dimension(0,100)));
		
		organizeFridge = new JButton("Organize Fridge");
		organizeFridge.setMaximumSize(centerPaneButtonDimension);
		organizeFridge.setAlignmentX(CENTER_ALIGNMENT);
		manageFridgePane.add(organizeFridge);
		
		manageFridgePane.add(Box.createRigidArea(new Dimension(0,100)));
		
		deleteFridge = new JButton("Delete Fridge");
		deleteFridge.setMaximumSize(centerPaneButtonDimension);
		deleteFridge.setAlignmentX(CENTER_ALIGNMENT);
		manageFridgePane.add(deleteFridge);
		
		itemPanel = new JPanel();
		itemPanel.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		itemPanel.setLayout(new BoxLayout(itemPanel,BoxLayout.Y_AXIS));
		itemPanel.setVisible(false);
		
		itemPanelhead = new JLabel("Manage Items");
		itemPanelhead.setAlignmentX(CENTER_ALIGNMENT);
		itemPanel.add(itemPanelhead);
		
		itemPanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		createItem = new JButton("Create Item");
		createItem.setMaximumSize(centerPaneButtonDimension);
		createItem.setAlignmentX(CENTER_ALIGNMENT);
		itemPanel.add(createItem);
		
		itemPanel.add(Box.createRigidArea(new Dimension(0,100)));

		deleteItem = new JButton("Delete Item");
		deleteItem.setMaximumSize(centerPaneButtonDimension);
		deleteItem.setAlignmentX(CENTER_ALIGNMENT);
		itemPanel.add(deleteItem);
		
		recipePanel = new JPanel();
		recipePanel.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		recipePanel.setLayout(new BoxLayout(recipePanel,BoxLayout.Y_AXIS));
		
		JLabel recipeHead = new JLabel("Recipe Menu");
		recipeHead.setAlignmentX(CENTER_ALIGNMENT);
		recipePanel.add(recipeHead);
		
		recipePanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton addRecipe =  new JButton("Add Recipe");
		addRecipe.setMaximumSize(centerPaneButtonDimension);
		addRecipe.setAlignmentX(CENTER_ALIGNMENT);
		recipePanel.add(addRecipe);
		
		recipePanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton suggestRecipe = new JButton("Suggest Recipe");
		suggestRecipe.setMaximumSize(centerPaneButtonDimension);
		suggestRecipe.setAlignmentX(CENTER_ALIGNMENT);
		recipePanel.add(suggestRecipe);
		
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if( e.getSource() == manageFridge ) {
			remove(itemPanel);
			remove(recipePanel);
			manageFridgePane.setVisible(true);
			add(manageFridgePane,BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if( e.getSource() == manageItems ) {
			remove(manageFridgePane);
			remove(recipePanel);
			itemPanel.setVisible(true);
			add(itemPanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if( e.getSource() == recipes ) {
			remove(itemPanel);
			remove(manageFridgePane);
			recipePanel.setVisible(true);
			add(recipePanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		}
	}
	
	
	public static void main(String[] args) {
		new Dashboard();
	}

}



