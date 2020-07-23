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



public class Dashboard extends JFrame{
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
		
		JButton manageFridge = new JButton("Manage Fridge");
		manageFridge.setAlignmentX(CENTER_ALIGNMENT);
		manageFridge.setMaximumSize(new Dimension(200,100));
		navi.add(manageFridge);
//		navi.add(Box.createVerticalGlue());
		;
//		navi.add(Box.createRigidArea(new Dimension(1,100)));
		
		JButton manageItems = new JButton("Manage Items");
		manageItems.setAlignmentX(CENTER_ALIGNMENT);
		manageItems.setMaximumSize(new Dimension(200,100));
		navi.add(manageItems);
		
//		navi.add(Box.createVerticalGlue());
		
		JButton recipes = new JButton("Recipes");
		recipes.setMaximumSize(new Dimension(200,100));
		recipes.setAlignmentX(CENTER_ALIGNMENT);
		navi.add(recipes);
		
		navi.add(Box.createRigidArea(new Dimension(3,200)));
		add(navi,BorderLayout.WEST);
		
		
		JPanel topPane = new JPanel( new FlowLayout() );
		topPane.setBackground(Color.GREEN);
		JLabel title = new JLabel("Smidge");
		topPane.add(title);
		add(topPane,BorderLayout.NORTH);
		
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane,BoxLayout.Y_AXIS));
		centerPane.setPreferredSize(new Dimension((dim.width/4)*3,dim.height));
		centerPane.setBackground(Color.cyan);
		
		JLabel fridgeHead = new JLabel("Manage Fridges");
		fridgeHead.setAlignmentX(CENTER_ALIGNMENT);
		centerPane.add(fridgeHead);
		
		centerPane.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton addFridge = new JButton("Add Fridge");
		addFridge.setMaximumSize(new Dimension( 150,100 ));
		addFridge.setAlignmentX(CENTER_ALIGNMENT);
		centerPane.add(addFridge);
		
		centerPane.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton organizeFridge = new JButton("Organize Fridge");
		organizeFridge.setMaximumSize(new Dimension( 150,100 ));
		organizeFridge.setAlignmentX(CENTER_ALIGNMENT);
		centerPane.add(organizeFridge);
		
		centerPane.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton deleteFridge = new JButton("Delete Fridge");
		deleteFridge.setMaximumSize(new Dimension( 150,100 ));
		deleteFridge.setAlignmentX(CENTER_ALIGNMENT);
		centerPane.add(deleteFridge);
		
		
		add(centerPane,BorderLayout.CENTER);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dashboard();
	}

}



