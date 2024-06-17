import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ToDoList extends JFrame implements ActionListener{
    // taskPanel will act as the container for the taskcomponentpanel
    // taskcomponentpanel will store all of the taskcomponents
    private JPanel taskPanel, taskComponentPanel;

 public ToDoList() {
    super ("To Do List Application");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setPreferredSize(CommonConstants.GUI_SIZE);
    pack();
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);

     addGuiComp();
 }

 private void addGuiComp() {
   // create the font
   Font tittleLabel = new Font("Serif", Font.BOLD, 40);
   Font taskText = new Font("Serif", Font.BOLD, 20);

   // banner text
   JLabel bannerLabel = new JLabel("To Do List");
   bannerLabel.setFont(tittleLabel); // the text to font
   bannerLabel.setBounds(
      (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2,
      15,
      CommonConstants.BANNER_SIZE.width,
      CommonConstants.BANNER_SIZE.height
      );

      // taskpanel
taskPanel = new JPanel();


// taskcomponentpanel
taskComponentPanel = new JPanel();
taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
taskPanel.add(taskComponentPanel);

// add scroling to the task panel
JScrollPane scrollPane = new JScrollPane(taskPanel);
scrollPane.setBounds(8, 70, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height);
scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// change speed of scroll
JScrollBar verticalScroll = scrollPane.getVerticalScrollBar();
verticalScroll.setUnitIncrement(20);

// add task button
JButton addTaskButton = new JButton("Add Task");
addTaskButton.setFont(taskText);
addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88,
   CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height); 
addTaskButton.addActionListener(this);

// add to frame
this.getContentPane().add(bannerLabel);
this.getContentPane().add(scrollPane);
this.getContentPane().add(addTaskButton);
 }

@Override
public void actionPerformed(ActionEvent e) {
   String command = e.getActionCommand();
   if (command.equalsIgnoreCase("Add Task")) {
      // create task component
      TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
      taskComponentPanel.add(taskComponent);

      // make the previous tak appear disabled
      if (taskComponentPanel.getComponentCount() > 1) {
         TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
            taskComponentPanel.getComponentCount() - 2);
         previousTask.gettaskField().setBackground(null);
      }


      // make the task field request focus after creation
      taskComponent.gettaskField().requestFocus();
      repaint();
      revalidate();
   }
}
}
