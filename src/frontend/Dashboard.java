package frontend;

import javax.swing.*;
import javax.swing.Box.Filler;

import customClasses.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;



public class Dashboard extends JFrame implements ActionListener{
	JButton manageFridge, manageItems, recipes,addFridge;
	JButton organizeFridge,deleteFridge,createItem,listItem,addRecipe;
	JButton suggestRecipe,reports,expenseReport,maintenanceReport,logOut;
	JPanel manageFridgePane,itemPanel,recipePanel,reportPane;
	JLabel fridgeHead,itemPanelhead,recipeHead,reportHead;
	Dimension centerPaneButtonDimension = new Dimension( 150,100 );
	User currentUser;
	Dashboard(User user){
		this.currentUser = user;
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
		
		reports = new JButton("Reports");
		reports.setMaximumSize(new Dimension(200,100));
		reports.setAlignmentX(CENTER_ALIGNMENT);
		reports.addActionListener(this);
		navi.add(reports);
		
		navi.add(Box.createRigidArea(new Dimension(3,50)));

		logOut = new JButton("Logout");
		logOut.setMaximumSize(new Dimension(200,100));
		logOut.setAlignmentX(CENTER_ALIGNMENT);
		logOut.addActionListener(this);
		navi.add(logOut);
		
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
		addFridge.addActionListener(this);
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
		deleteFridge.addActionListener(this);
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

		listItem = new JButton("View Items");
		listItem.setMaximumSize(centerPaneButtonDimension);
		listItem.setAlignmentX(CENTER_ALIGNMENT);
		listItem.addActionListener(this);
		itemPanel.add(listItem);
		
		recipePanel = new JPanel();
		recipePanel.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		recipePanel.setLayout(new BoxLayout(recipePanel,BoxLayout.Y_AXIS));
		recipePanel.setVisible(false);
		
		recipeHead = new JLabel("Recipe Menu");
		recipeHead.setAlignmentX(CENTER_ALIGNMENT);
		recipePanel.add(recipeHead);
		
		recipePanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		addRecipe =  new JButton("Add Recipe");
		addRecipe.setMaximumSize(centerPaneButtonDimension);
		addRecipe.setAlignmentX(CENTER_ALIGNMENT);
		addRecipe.addActionListener(this);
		recipePanel.add(addRecipe);
		
		recipePanel.add(Box.createRigidArea(new Dimension(0,100)));
		
		suggestRecipe = new JButton("Suggest Recipe");
		suggestRecipe.setMaximumSize(centerPaneButtonDimension);
		suggestRecipe.setAlignmentX(CENTER_ALIGNMENT);
		suggestRecipe.addActionListener(this);
		recipePanel.add(suggestRecipe);
		

		
		reportPane = new JPanel();
		reportPane.setLayout(new BoxLayout(reportPane,BoxLayout.Y_AXIS));
		reportPane.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		reportPane.setBackground(Color.cyan);
		reportPane.setVisible(false);
		
		reportHead = new JLabel("Reports");
		reportHead.setAlignmentX(CENTER_ALIGNMENT);
		reportPane.add(reportHead);
		
		reportPane.add(Box.createRigidArea(new Dimension(0,100)));
		
		expenseReport = new JButton("Expense Report");
		expenseReport.setMaximumSize(centerPaneButtonDimension);
		expenseReport.setAlignmentX(CENTER_ALIGNMENT);
		expenseReport.addActionListener(this);
		reportPane.add(expenseReport);
		
		reportPane.add(Box.createRigidArea(new Dimension(0,100)));
		
		maintenanceReport = new JButton("Maintenance Report");
		maintenanceReport.setMaximumSize(centerPaneButtonDimension);
		maintenanceReport.setAlignmentX(CENTER_ALIGNMENT);
		maintenanceReport.addActionListener(this);
		reportPane.add(maintenanceReport);
		

		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if( e.getSource() == manageFridge ) {
			remove(itemPanel);
			remove(reportPane);
			remove(recipePanel);
			manageFridgePane.setVisible(true);
			add(manageFridgePane,BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if( e.getSource() == manageItems ) {
			remove(manageFridgePane);
			remove(recipePanel);
			remove(reportPane);
			itemPanel.setVisible(true);
			add(itemPanel, BorderLayout.CENTER);
			revalidate();
			repaint();
			System.out.println(currentUser.recipes.get("dish 2").ingredients);
		}
		else if( e.getSource() == recipes ) {
			remove(itemPanel);
			remove(manageFridgePane);
			remove(reportPane);
			recipePanel.setVisible(true);
			add(recipePanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if( e.getSource() == reports ) {
			remove(itemPanel);
			remove(manageFridgePane);
			remove(recipePanel);
			reportPane.setVisible(true);
			add(reportPane,BorderLayout.CENTER);
			revalidate();
			repaint();
		}
		else if( e.getSource() == logOut ) {
			currentUser.logout();
			JOptionPane.showMessageDialog(this, "Logged Out Successfully!");
			dispose();
			try {
				
				new Login();
			}
			catch (Exception ex) {
				System.out.println(" dashboard logout "+ex.getMessage());
			}
		}
		else if(e.getSource()==addRecipe) {
			new AddRecipe(currentUser);
		}
		else if( e.getSource()==suggestRecipe ) {
			new SuggestRecipe(currentUser);
		}
		else if( e.getSource()==listItem ) {
			new ListItems();
		}
		else if( e.getSource()==addFridge ) {
			try {
				new AddFridge(currentUser);
				System.out.println( currentUser.fridges );
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Exception in add fridge"+e1.getMessage());
			}
		}
		else if( e.getSource()==deleteFridge ) {
			try {
				new DeleteFridge(currentUser);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Exception in delete fridge"+e1.getMessage());
			}
		}
		else if( e.getSource()==expenseReport ) {
			new ExpenditureReport(currentUser);
		}
		else if( e.getSource()== maintenanceReport ) {
			new MaintainceReport(currentUser);
		}
	}
	
	
	public static void main(String[] args) {
	}

}



