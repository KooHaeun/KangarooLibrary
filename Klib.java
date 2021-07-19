import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;

class Login extends JFrame implements ActionListener{
   JTextField id; JPasswordField passwd;
   JButton login, schId, schPwd, newMem;
   boolean isUser = true;
   Login(String title){
      setTitle(title);
      Container ct = getContentPane();
      ct.setLayout(null);
      JRadioButton user = new JRadioButton("�����");
      user.addActionListener(this);
      JRadioButton manager = new JRadioButton("������");
      manager.addActionListener(this);   
      user.setSelected(true);   
      ButtonGroup choose = new ButtonGroup();
      choose.add(user);      choose.add(manager);
      ct.add(user);      ct.add(manager);
      user.setBounds(400,80,100,100);
      manager.setBounds(600,80,100,100);
      JLabel l1 = new JLabel("ID");
      id = new JTextField(8);
      l1.setBounds(250, 200, 120, 30);
      id.setBounds(280, 200, 450, 30);
      ct.add(l1);   ct.add(id);
            JLabel l2 = new JLabel("P/D");
              passwd = new JPasswordField(10);
              l2.setBounds(250, 270, 120, 30);
              passwd.setBounds(280, 270, 450, 30);     
              ct.add(l2);   ct.add(passwd);
              login = new JButton("�α���");
      login.addActionListener(this);
              schId = new JButton("���̵� ã��");
      schId.addActionListener(this);
              schPwd = new JButton("��й�ȣ ã��");
      schPwd.addActionListener(this);
             newMem = new JButton("ȸ������");
      newMem.addActionListener(this);   

      login.setBounds(750, 200, 100, 100);

      schId.setBounds(330, 340, 120, 30);
      schPwd.setBounds(610, 340, 120, 30);
      newMem.setBounds(490, 420, 90, 30);
      ct.add(login);   ct.add(schId);   ct.add(schPwd);   ct.add(newMem);
   }

   

    public void actionPerformed(ActionEvent ae){
      String s = ae.getActionCommand();
      if(s.equals("�����")){ isUser = true; }
      else if(s.equals("������")){ isUser = false; }
      String t_id = "",t_passwd = "";   

      if(isUser == true){
          if(s.equals("�α���")){
            t_id = id.getText(); 
            t_passwd = passwd.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
         
               strSql="SELECT * FROM mem WHERE mid='"+t_id+"' and passwd='"+t_passwd+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  MessageDialog md = new MessageDialog(this, "�α���", true, "ȯ���մϴ�!! Ļ�ŷ� �������Դϴ�!!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();

	     Scene2 sc = new Scene2(t_id);
	     sc.setTitle("������� ȭ��");
	     sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     sc.setSize(1600, 800);
	     sc.setVisible(true);
               }
               else{
                  MessageDialog md = new MessageDialog(this, "�α��� ����", true, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");   
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
         
         }
         else if(s.equals("ȸ������")) {
            NewMemberUser my = new NewMemberUser("ȸ������");
            my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            my.setSize(1100, 600);
            my.setLocation(400, 235);
            my.show();
          }
          else if(s.equals("���̵� ã��")) {
            FindIdU fi = new FindIdU("���̵� ã��");
            fi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fi.setSize(1100, 600);
            fi.setLocation(400, 235);
            fi.show();
          }
           else if(s.equals("��й�ȣ ã��")) {
            FindPasswdU fp = new FindPasswdU("��й�ȣ ã��");
            fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fp.setSize(1100, 600);
            fp.setLocation(400, 235);
            fp.show();
          }
         else{}
      }

      if(isUser == false){
          if(s.equals("�α���")){
            t_id = id.getText(); 
            t_passwd = passwd.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
         
               strSql="SELECT * FROM manager WHERE id='"+t_id+"'and passwd='"+t_passwd+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  MessageDialog md = new MessageDialog(this, "�α���", true, "ȯ���մϴ�!! Ļ�ŷ� �������Դϴ�!!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                  md.show();

	     Administrator admin= new Administrator();
    	     admin.setTitle("������ ȭ��");
    	     admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	     admin.setSize(1600, 800);
    	     admin.setVisible(true);     
               }
               else{
                  MessageDialog md = new MessageDialog(this, "�α��� ����", true, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");   
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();;
               }
               
               dbSt.close();       
               con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
         
         }
         else if(s.equals("ȸ������")) {

            NewMemberMgr my = new NewMemberMgr("ȸ������");
            my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            my.setSize(1100, 600);
            my.setLocation(400, 235);
            my.show();
          }
          else if(s.equals("���̵� ã��")) {
            FindIdM fi = new FindIdM("���̵� ã��");
            fi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fi.setSize(1100, 600);
            fi.setLocation(400, 235);
            fi.show();
          }
           else if(s.equals("��й�ȣ ã��")) {
            FindPasswdM fp = new FindPasswdM("��й�ȣ ã��");
            fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fp.setSize(1100, 600);
            fp.setLocation(400, 235);
            fp.show();
          }
         else{}
      }
   }
}
       

class NewMemberUser extends JFrame implements ActionListener{                           //����� ȸ������
   JTextField id, tel_number, address, hintAns; JPasswordField passwd, passwdCh;
   String hintList[] = {"��Ӵ� ������?", "� �� ������?", "��� �ʵ��б���?", "����л� �� ��Ҵ� ����?",
          "���� ��￡ ���� ��������?"};
   JComboBox hint;
     boolean ch1 = true;
      boolean numCh = true;
 NewMemberUser(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("���̵�");
   id = new JTextField(8);
   l1.setBounds(195, 80, 80, 30);
   id.setBounds(260, 80, 500, 30);

   JButton idcheck = new JButton("�ߺ�Ȯ��");
   ct.add(l1); ct.add(id); ct.add(idcheck);
   idcheck.setBounds(800, 80, 100, 30);
   idcheck.addActionListener(this);

   JLabel l2 = new JLabel("��й�ȣ");
   passwd = new JPasswordField(8);
   ct.add(l2); ct.add(passwd);
   l2.setBounds(190, 160, 80, 30);
   passwd.setBounds(260, 160, 500, 30);

   JLabel l3 = new JLabel("��й�ȣ Ȯ��");
   passwdCh = new JPasswordField(8);
   ct.add(l3);   ct.add(passwdCh);
   l3.setBounds(170, 240, 100, 30);
   passwdCh.setBounds(260, 240, 500, 30);

   JLabel l4 = new JLabel("��й�ȣ ��Ʈ");
   hint = new JComboBox(hintList);
   ct.add(l4); ct.add(hint); 
   l4.setBounds(170, 320, 100, 30);
   hint.setBounds(260, 320, 500, 30);

       JLabel l5 = new JLabel("��");
       hintAns = new JTextField (20);
       ct.add(l5); ct.add(hintAns); 
   l5.setBounds(200, 400, 80, 30);
   hintAns.setBounds(260, 400, 500, 30);


      JButton b1 = new JButton("����");
       ct.add(b1);  
       b1.setBounds(500,480,80,30);
       b1.addActionListener(this);
 }
   
   public void actionPerformed(ActionEvent ae) {  
          String s = ae.getActionCommand();     
          String t_id="", t_passwd="", t_passwdCh="", t_hintAns="";
      int t_hint;

   if(ch1 == true){
      if( s.equals("�ߺ�Ȯ��")){
         t_id = id.getText();
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
              } catch(ClassNotFoundException e) {
                       System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB ���� �Ϸ�."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
            
            String strSql;
            strSql ="SELECT * FROM mem WHERE mid='"+t_id+"';";
            ResultSet result = dbSt.executeQuery(strSql);

            char check;
            for(int i = 0; i < t_id.length(); i++){
               check = t_id.charAt(i);
               if(Character.isDigit(check) == false){
                  numCh = false;}
            }
            if(numCh == false){
               MessageDialog md = new MessageDialog(this, "ID�Է°��", true, "���̵�� ���ڷθ� �Է����ּ���.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(!(t_id.length() >= 4 && t_id.length() <= 10)){
               MessageDialog md = new MessageDialog(this, "ID�Է°��", true, "���̵�� 4~10�ڸ� ���ڷ� �Է����ּ���.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            
            else if(result.next()){
               MessageDialog md = new MessageDialog(this, "ID�ߺ�üũ", true, "�̹� ������� ���̵��Դϴ�.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }

            else{
	   ch1 = false;  
               MessageDialog md = new MessageDialog(this, "ID�ߺ�üũ", true, "��밡���� ID�Դϴ�!");
               md.setSize(500,120);
               md.setLocation(400,235);
               md.show();
            }
	 dbSt.close();       
             con.close();
          }catch (SQLException e) {
                   System.out.println("SQLException : "+e.getMessage()); }
          }
   }

   if ( s.equals("����")) { 
          if(ch1 == false){
               t_id = id.getText();    t_passwd = passwd.getText();  
               t_passwdCh = passwdCh.getText();     t_hint = hint.getSelectedIndex();
               t_hintAns = hintAns.getText(); 

         	if(!(t_id.equals("")) && !(t_passwd.equals("")) && !(t_passwdCh.equals("")) && !(t_hintAns.equals(""))){                  
                   try {         
                  	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
                 	 System.out.println("DB ���� �Ϸ�."); 
                  	Statement dbSt = con.createStatement();
                  	System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");   
            
                  	String strSql = "INSERT INTO mem (mid, passwd, hint, answer, name, tel, mail, address) VALUES ('"+t_id+"', '"+t_passwd+"', '"+ t_hint+"', '"+t_hintAns+"','"+"','"+1+"','"+"','"+"');";			
                  	dbSt.executeUpdate(strSql);      // sql ���Ǿ� ���� strSql�� ����
                  	System.out.println("������ ���� �Ϸ�");
		String nextpage ="SELECT * FROM mem WHERE mid='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               	ResultSet result = dbSt.executeQuery(nextpage); 
               	if(result.next()){
                 		NextPage ne = new NextPage("ȸ������", t_id);
            			ne.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            			ne.setSize(1100, 600);
            			ne.setLocation(400, 235); 
			ne.show();
                  		dispose();
               	}

                  	dbSt.close();       
                  	con.close();        
                	}catch(SQLException e) {
                   	System.out.println("SQLException: " +e.getMessage());
                	}

         
            	
         }
         else{
            MessageDialog md = new MessageDialog(this, "�Է� ����", true, "����׸��� �ۼ��Ͻʽÿ�.");
            md.setSize(500,120);
            md.setLocation(400,235);
         }
       }
   }

   } 
}

class NextPage extends JFrame implements ActionListener{                              //ȸ������ ������ 2
   JTextField name, p, tel_number, email, address;
   String u_id;
 NextPage(String title, String id){
   u_id=id;
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("�̸�");
   name = new JTextField(8);
   l1.setBounds(200, 80, 80, 30);
   name.setBounds(280, 80, 500, 30);
   ct.add(l1); ct.add(name);

   JLabel l2 = new JLabel("��ȭ��ȣ");
    tel_number = new JTextField(8);
    l2.setBounds(200, 180, 80, 30);
    tel_number.setBounds(280, 180, 500, 30);
   ct.add(l2); ct.add(tel_number);    

   JLabel l3 = new JLabel("E-Mail");
   email = new JTextField(8);
   l3.setBounds(200, 280, 80, 30);
   email.setBounds(280, 280, 500, 30);
   ct.add(l3); ct.add(email);    

   JLabel l4 = new JLabel("�ּ�");
   address = new JTextField(8);
   l4.setBounds(200, 380, 80, 30);
   address.setBounds(280, 380, 500, 30);
   ct.add(l4); ct.add(address);    

      JButton b1 = new JButton("����");
       b1.addActionListener(this);    
       ct.add(b1);  
   b1.setBounds(500,480,80,30);
  }

   public void actionPerformed(ActionEvent ae){
         String s = ae.getActionCommand();
         String t_name="", t_tel_number="", t_email="", t_address=""; 
            if( s == "����"){
            t_name = name.getText();    t_tel_number = tel_number.getText();  
            t_email = email.getText();     t_address = address.getText();   

      if(!(t_name.equals("")) && !(t_tel_number.equals("")) && !(t_email.equals("")) && !(t_address.equals(""))){
              try{   
                 Class.forName("com.mysql.jdbc.Driver");  // mysql�� jdbc Driver ����
                 System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
              } catch(ClassNotFoundException e) {
                   System.err.println("����̹� �ε忡 �����߽��ϴ�.");
              }  

          try {          
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
             System.out.println("DB ���� �Ϸ�."); 
             Statement dbSt = con.createStatement();
             System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
  
               String strSql1 = "UPDATE mem SET name = '"+t_name+"' WHERE mid = '"+u_id+"';"; 
	  String strSql2 = "UPDATE mem SET tel = '"+t_tel_number+"' WHERE mid = '"+u_id+"' ;";
	  String strSql3 = "UPDATE mem SET mail = '"+  t_email+"' WHERE mid = '"+u_id+"' ;";
	  String strSql4 ="UPDATE mem SET address = '"+t_address+"' WHERE mid = '"+u_id+"';";
               dbSt.executeUpdate(strSql1); dbSt.executeUpdate(strSql2); dbSt.executeUpdate(strSql3); dbSt.executeUpdate(strSql4);

               System.out.println("������ ���� �Ϸ�");
         dbSt.close();       
              con.close();        
             }catch(SQLException e) {
                System.out.println("SQLException: " +e.getMessage());
             }
         MessageDialog md = new MessageDialog(this, "ȸ������", true, "ȸ�����ԵǼ̽��ϴ�!");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show(); 
         dispose();
          }
      else{
         MessageDialog md = new MessageDialog(this, "�Է� ����", true, "����׸��� �ۼ��Ͻʽÿ�.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
       }
   }
} 

class NewMemberMgr extends JFrame implements ActionListener{                           //����� ȸ������
   JTextField id, name, hintAns; JPasswordField passwd, passwdCh;
   String hintList[] = {"��Ӵ� ������?", "� �� ������?", "��� �ʵ��б���?", "����л� �� ��Ҵ� ����?",
          "���� ��￡ ���� ��������?"};
   JComboBox hint;
      boolean ch = true;
      boolean numCh = true;   

 NewMemberMgr(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("���̵�");
   id = new JTextField(8);
   l1.setBounds(195, 80, 80, 30);
   id.setBounds(260, 80, 150, 30);

   JButton idcheck = new JButton("�ߺ�Ȯ��");
   idcheck.addActionListener(this);  
   ct.add(l1); ct.add(id); ct.add(idcheck);
   idcheck.setBounds(420, 80, 100, 30);

   JLabel l2 = new JLabel("�̸�");
   name = new JTextField(8);
   ct.add(l2); ct.add(name);
   l2.setBounds(570, 80, 80, 30);
   name.setBounds(610, 80, 150, 30);

   JLabel l3 = new JLabel("��й�ȣ");
   passwd = new JPasswordField(8);
   ct.add(l3); ct.add(passwd);
   l3.setBounds(190, 160, 80, 30);
   passwd.setBounds(260, 160, 500, 30);

   JLabel l4 = new JLabel("��й�ȣ Ȯ��");
   passwdCh = new JPasswordField(8);
   ct.add(l4);   ct.add(passwdCh);
   l4.setBounds(170, 240, 100, 30);
   passwdCh.setBounds(260, 240, 500, 30);

   JLabel l5 = new JLabel("��й�ȣ ��Ʈ");
   hint = new JComboBox(hintList);
   ct.add(l5); ct.add(hint); 
   l5.setBounds(170, 320, 100, 30);
   hint.setBounds(260, 320, 500, 30);

       JLabel l6 = new JLabel("��");
       hintAns = new JTextField(20);
       ct.add(l6); ct.add(hintAns); 
   l6.setBounds(200, 400, 80, 30);
   hintAns.setBounds(260, 400, 500, 30);


      JButton b1 = new JButton("����");
       ct.add(b1);  
   b1.setBounds(500,480,80,30);
   b1.addActionListener(this);
 }
   
   public void actionPerformed(ActionEvent ae) {  
          String s = ae.getActionCommand();     
          String t_id="", t_name, t_passwd="", t_passwdCh="", t_hintAns="";
      int t_hint;
   if(ch == true){
          if( s.equals("�ߺ�Ȯ��")){
         t_id = id.getText(); ;
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
              } catch(ClassNotFoundException e) {
                       System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB ���� �Ϸ�."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
            String strSql;
            strSql ="SELECT * FROM mem WHERE mid='"+t_id+"';";
            ResultSet result = dbSt.executeQuery(strSql);

            char check;
            for(int i = 0; i < t_id.length(); i++){
               check = t_id.charAt(i);
               if(Character.isDigit(check) == false){
                  numCh = false;}
            }
            if(numCh == false){
               MessageDialog md = new MessageDialog(this, "ID�Է°��", true, "���̵�� ���ڷθ� �Է����ּ���.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(!(t_id.length() >= 4 && t_id.length() <= 10)){
               MessageDialog md = new MessageDialog(this, "ID�Է°��", true, "���̵�� 4~10�ڸ� ���ڷ� �Է����ּ���.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(result.next()){
               MessageDialog md = new MessageDialog(this, "ID�ߺ�üũ", true, "�̹� ������� ���̵��Դϴ�.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else{
	   ch = false;
               MessageDialog md = new MessageDialog(this, "ID�ߺ�üũ", true, "��밡���� ID�Դϴ�!");
               md.setSize(500,120);
               md.setLocation(400,235);
               md.show();             
            }
            dbSt.close();       
            con.close();
          }catch (SQLException e) {
                      System.out.println("SQLException : "+e.getMessage()); }
          }
       }
  
   if ( s.equals("����")) {
	if(ch == false){
        	   t_id = id.getText();  t_name = name.getText();   t_passwd = passwd.getText();  
               t_passwdCh = passwdCh.getText();  t_hint = hint.getSelectedIndex();
               t_hintAns = hintAns.getText();  

         		if(!(t_id.equals("")) && !(t_name.equals("")) && !(t_passwd.equals("")) && !(t_passwdCh.equals("")) && !(t_hintAns.equals(""))){
         
                  	 try{
                    	 Class.forName("com.mysql.jdbc.Driver");  // mysql�� jdbc Driver ����
                     	 System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 	  } catch(ClassNotFoundException e) {
                         System.err.println("����̹� �ε忡 �����߽��ϴ�.");
                 	  }  
              	  try {         
              	       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
                   	  System.out.println("DB ���� �Ϸ�."); 
                   	  Statement dbSt = con.createStatement();
                   	  System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
                  

                 	 String strSql = "INSERT INTO manager (id, name, passwd, hint, answer) VALUES ("+"'"+t_id+"', '"+t_name+"', '"+t_passwd+"', '"+ t_hint+"', '"+t_hintAns+"');";
                 	 dbSt.executeUpdate(strSql);      // sql ���Ǿ� ���� strSql�� ����
                 	 System.out.println("������ ���� �Ϸ�");
                 	 dbSt.close();       
                  	con.close();        
              	  }catch(SQLException e) {
                   	System.out.println("SQLException: " +e.getMessage());}
            	MessageDialog md = new MessageDialog(this, "ȸ������", true, "ȸ�����ԵǼ̽��ϴ�!");
            	md.setSize(500,120);
            	md.setLocation(400,235);
            	md.show();
            	dispose();
         	           }
         		else{
         		MessageDialog md = new MessageDialog(this, "�Է� ����", true, "����׸��� �ۼ��Ͻʽÿ�.");
         		md.setSize(500,120);
         		md.setLocation(400,235);
       		md.show();
         		}
         }
   }
   } 
}




class FindIdU extends JFrame implements ActionListener{                              //���̵� ã�� ����ڿ�
   JTextField name, tel_number, email;
 FindIdU(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("�̸�");
   name = new JTextField(8);
   ct.add(l1); ct.add(name);
   l1.setBounds(220, 120, 80, 30);
   name.setBounds(300, 120, 530, 30);
   
   JLabel l2 = new JLabel("��ȭ��ȣ");
   tel_number = new JTextField(8);
   ct.add(l2); ct.add(tel_number);
   l2.setBounds(220, 220, 80, 30);
   tel_number.setBounds(300, 220, 530, 30);

   JLabel l3 = new JLabel("E-Mail");
   email = new JTextField(8);
   ct.add(l3); ct.add(email);
   l3.setBounds(220, 320, 80, 30);
   email.setBounds(300, 320, 530, 30);
   
   JButton b = new JButton("ã��");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_name = "", t_tel_number = "";
   String t_email="";
      if( s.equals("ã��")){
         t_name = name.getText(); 
         t_tel_number = tel_number.getText(); 
         t_email = email.getText();
         if(!(t_name.equals("")) && !(t_tel_number.equals("")) && !(t_email.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
               strSql ="SELECT mid FROM mem WHERE name='"+t_name+"' and tel ='"+t_tel_number+"'and mail = '"+t_email+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  String myid = result.getString(1);
                  MessageDialog md = new MessageDialog(this, "���̵� ã��", true, myid);
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "����", true, "��ȸ�� ȸ���� �����ϴ�!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
         }
         else{
         MessageDialog md = new MessageDialog(this, "�Է� ����", true, "����׸��� �ۼ��Ͻʽÿ�.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
         }
      } 
 }
}

class FindPasswdU extends JFrame implements ActionListener{                           //��й�ȣ ã�� ����ڿ�
   JTextField id, hintAns;   
   String hintList[] = {"��Ӵ� ������?", "� �� ������?", "��� �ʵ��б���?", "����л� �� ��Ҵ� ����?",
          "���� ��￡ ���� ��������?"};
   JComboBox hint;
 FindPasswdU(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("���̵�");
   id = new JTextField(8);
   ct.add(l1); ct.add(id);
   l1.setBounds(200, 100, 80, 30);
   id.setBounds(270, 100, 500, 30);
   
   JLabel l2 = new JLabel("��й�ȣ ��Ʈ");
   hint = new JComboBox(hintList);
   ct.add(l2); ct.add(hint); 
   l2.setBounds(170, 220, 100, 30);
   hint.setBounds(270, 220, 500, 30);

   JLabel l3 = new JLabel("��");
       hintAns = new JTextField (20);
       ct.add(l3); ct.add(hintAns); 
   l3.setBounds(210, 340, 80, 30);
   hintAns.setBounds(270, 340, 500, 30);
   
   JButton b = new JButton("ã��");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_id = "", t_hintAns = "";
   int t_hint;
   if( s.equals("ã��")){
      t_id = id.getText(); 
      t_hint = hint.getSelectedIndex();
      t_hintAns = hintAns.getText();
      if(!(t_id.equals("")) && !(t_hintAns.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
               strSql ="SELECT * FROM mem WHERE mid='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  FindPwdUser fp = new FindPwdUser("��й�ȣ ����", t_id);
                         fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                         fp.setSize(1100, 600);
                         fp.setLocation(400, 235);
                  fp.show();
                  dispose();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "����", true, "��ȸ�� ȸ���� �����ϴ�!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                      System.out.println("SQLException : "+e.getMessage()); }
      }
      else{
         MessageDialog md = new MessageDialog(this, "�Է� ����", true, "����׸��� �ۼ��Ͻʽÿ�.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
   } 
 }
}

class FindPwdUser extends JFrame implements ActionListener{                           //����� ��� ã��
   JTextField newPwd, newPwdCh;
   String u_id;
   FindPwdUser(String title, String id){ 
   u_id = id;
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("�� ��й�ȣ");
   newPwd = new JPasswordField(8);
   ct.add(l1); ct.add(newPwd);
   l1.setBounds(240, 150, 80, 30);
   newPwd.setBounds(340, 150, 400, 30);

   JLabel l2 = new JLabel("��й�ȣ Ȯ��");
   newPwdCh = new JPasswordField(8);
   ct.add(l2); ct.add(newPwdCh);
   l2.setBounds(240, 290, 100, 30);
   newPwdCh.setBounds(340, 290, 400, 30);

   JButton b = new JButton("���� �Ϸ�");
   ct.add(b);
   b.setBounds(500,420,100,30);
   b.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent ae) {  
      String s = ae.getActionCommand();
      String t_newPwd="", t_newPwdCh="";
      if(s.equals("���� �Ϸ�")){
         t_newPwd = newPwd.getText(); 
         t_newPwdCh = newPwdCh.getText();
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
              } catch(ClassNotFoundException e) {
                       System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB ���� �Ϸ�."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
            String strSql;
         
            if(t_newPwd.equals(t_newPwdCh)){
               strSql="UPDATE mem SET passwd = '"+t_newPwd+"' WHERE mid = '"+u_id+"';";
               dbSt.executeUpdate(strSql);
               MessageDialog md = new MessageDialog(this, "��й�ȣ ����", true, "����Ǿ����ϴ�!");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
               System.out.println("������ ���� �Ϸ�");
               dispose();
            }
            else{
               MessageDialog md = new MessageDialog(this, "��й�ȣ ����", true, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();         
            }
            dbSt.close();       
               con.close();
          }catch (SQLException e) {
                   System.out.println("SQLException : "+e.getMessage()); }
      }
   }
}


class FindIdM extends JFrame implements ActionListener{                              //�����ڿ� ���̵� ã��
   JTextField mname;;
 FindIdM(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("�̸�");
   mname = new JTextField(8);
   ct.add(l1); ct.add(mname);
   l1.setBounds(220, 250, 80, 30);
   mname.setBounds(300, 250, 530, 30);

   JButton b = new JButton("ã��");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_name = "";
   if( s.equals("ã��")){
      t_name = mname.getText();
      if(!(t_name.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                     Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
               strSql ="SELECT id FROM manager WHERE name='"+t_name+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  String myid = result.getString(1);
                  MessageDialog md = new MessageDialog(this, "���̵� ã��", true, myid);
                  md.setSize(500,120);
                  md.setLocation(400,235);
                  md.show();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "����", true, "��ȸ�� ȸ���� �����ϴ�!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
   
      }
      else{
         MessageDialog md = new MessageDialog(this, "�Է� ����", true, "�׸��� �Է��Ͻʽÿ�.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
   } 
 }
}

class FindPasswdM extends JFrame implements ActionListener{                              //�����ڿ� ���ã��
   JTextField id, hintAns;   
   String hintList[] = {"��Ӵ� ������?", "� �� ������?", "��� �ʵ��б���?", "����л� �� ��Ҵ� ����?",
          "���� ��￡ ���� ��������?"};
   JComboBox hint;
 FindPasswdM(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("���̵�");
   id = new JTextField(8);
   ct.add(l1); ct.add(id);
   l1.setBounds(200, 100, 80, 30);
   id.setBounds(270, 100, 500, 30);
   
   JLabel l2 = new JLabel("��й�ȣ ��Ʈ");
   hint = new JComboBox(hintList);
   ct.add(l2); ct.add(hint); 
   l2.setBounds(170, 220, 100, 30);
   hint.setBounds(270, 220, 500, 30);

   JLabel l3 = new JLabel("��");
       hintAns = new JTextField (20);
       ct.add(l3); ct.add(hintAns); 
   l3.setBounds(210, 340, 80, 30);
   hintAns.setBounds(270, 340, 500, 30);
   
   JButton b = new JButton("ã��");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);

   
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   if( s.equals("ã��")){
   String t_id = "", t_hintAns = "";
   int t_hint;
   if( s.equals("ã��")){
      t_id = id.getText(); 
      t_hint = hint.getSelectedIndex();
      t_hintAns = hintAns.getText();

      if(!(t_id.equals("") && t_hintAns.equals(""))){
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
              } catch(ClassNotFoundException e) {
                       System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB ���� �Ϸ�."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
            String strSql;
               strSql ="SELECT * FROM manager WHERE id='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  FindPwdUser fp = new FindPwdUser("��й�ȣ ����", t_id);
                         fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                         fp.setSize(1100, 600);
                         fp.setLocation(400, 235);
                  fp.show();
                  dispose();
               }
            else{
               MessageDialog md = new MessageDialog(this, "����", true, "��ȸ�� �����ڰ� �����ϴ�!");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            dbSt.close();       
               con.close();
          }catch (SQLException e) {
                   System.out.println("SQLException : "+e.getMessage()); }
      }
      else{
         MessageDialog md = new MessageDialog(this, "�Է� ����", true, "��� �׸��� �Է��Ͻʽÿ�.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
      
   } 
   } 
 }
}
class FindPwdMgr extends JFrame implements ActionListener{                     //�����ڿ� ��� ���� ȭ��2
   JTextField newPwd, newPwdCh;
   String m_id;
   FindPwdMgr(String title, String id){
   m_id = id;
   
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("�� ��й�ȣ");
   newPwd = new JPasswordField(8);
   ct.add(l1); ct.add(newPwd);
   l1.setBounds(240, 150, 80, 30);
   newPwd.setBounds(340, 150, 400, 30);

   JLabel l2 = new JLabel("��й�ȣ Ȯ��");
   newPwdCh = new JPasswordField(8);
   ct.add(l2); ct.add(newPwdCh);
   l2.setBounds(240, 290, 100, 30);
   newPwdCh.setBounds(340, 290, 400, 30);

   JButton b1 = new JButton("���� �Ϸ�");
   ct.add(b1);
   b1.setBounds(500,420,100,30);
   b1.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent ae) {  
      String s = ae.getActionCommand();
      String t_newPwd="", t_newPwdCh="";
      if(s.equals("���� �Ϸ�")){
         t_newPwd = newPwd.getText(); 
         t_newPwdCh = newPwdCh.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
                 } catch(ClassNotFoundException e) {
                          System.err.println("����̹� �ε忡 �����߽��ϴ�.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB ���� �Ϸ�."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
         
               String strSql;
         
               if(t_newPwd.equals(t_newPwdCh)){
                  strSql="UPDATE manager SET passwd = '"+t_newPwd+"' WHERE id = '"+m_id+"';";
                  dbSt.executeUpdate(strSql);
                  MessageDialog md = new MessageDialog(this, "��й�ȣ ����", true, "����Ǿ����ϴ�!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
                  System.out.println("������ ���� �Ϸ�");
               }
               else{
                  MessageDialog md = new MessageDialog(this, "��й�ȣ ����", true, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                  md.setLocation(400,235);
                  md.show();         
               }
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                      System.out.println("SQLException : "+e.getMessage()); }
      }
   }
}

class Search extends JPanel implements ActionListener, ItemListener{
	JComboBox big, detail;
	JComboBox title;
	JTextField sf;
	JButton sb;

	Vector<String> column;
	Vector<Vector<String>> row;
	JTable table = null;
	DefaultTableModel model = null;

	String[] Data2 = {"����", "�۰�", "���ǻ�"};

	String[] bigS = {"�ѷ�", "ö��", "����", "��ȸ����", "��������", "�������", "����", "����", "����", "����"};
	String[][] detailS = {{"�ѷ� ����Ʈ����", "������, ������", "����������", "�������", "������, ������", "�Ϲ� ���� ���๰", "�Ϲ���ȸ", "�Ź�, ���", "�Ϲ� ����", "�����ڷ�"}, 
			 {"ö���Ϲ�", "���̻���", "ö��ü��", "����", "����ö��", "����", "�ɸ���", "������, ����ö��"}, // 2���� 5���� null
			 {"�����Ϲ�", "������", "�ұ�", "�⵶��", "����", "õ����", "�ŵ�", "�ٶ󹮱�", "�̽�����", "��Ÿ ������"}, 
			 {"��ȸ�����Ϲ�", "�����", "������", "��ȸ��", "��ġ��", "������", "����", "������", "ǳ�ӹμ���", "���決����"}, 
			 {"���������Ϲ�", "����", "������", "ȭ��", "õ����", "����", "������", "�������", "�Ĺ���", "������"}, 
			 {"��������Ϲ�", "����, ����", "���, ������", "����, �����Ϲ�", "������", "�������", "ȭ�а���", "������, �μ��", "������ �� ������ȭ"}, 
			 {"�����Ϲ�", "�����", "����", "���� ��Ĺ̼�", "����", "ȸȭ, ��ȭ", "������", "����", "����, ��ȭ", "����, �"}, 
			 {"�����Ϲ�", "�ѱ���", "�߱���", "�Ϻ���", "����", "���Ͼ�", "��������", "�����ξ�", "��Ż���ƾ�", "��Ÿ����"}, 
			 {"�����Ϲ�", "�ѱ�����", "�߱�����", "�Ϻ�����", "���̹���", "���Ϲ���", "����������", "�����ι���", "��Ż���ƹ���", "��Ÿ������"}, 
			 {"�����Ϲ�", "�ƽþ�", "����", "������ī", "�ϾƸ޸�ī", "���Ƹ޸�ī", "�����ƴϾ�", "��������", "��������", "��������"}};

	JScrollPane tableSP;

	int i = 1;
	String t_bnum= "", t_title="", t_author="", t_publisher="", t_bsort="", t_ssort="", t_cond="", t_borw="";

	int ide, idx;
	boolean t;

	public Search() {						//�˻�ȭ�� ������
		this.setLayout(null);
		big = new JComboBox(bigS);
		detail = new JComboBox(detailS[0]);
		title = new JComboBox(Data2);
		big.addItemListener(this);		

		sf = new JTextField(10);
		sb = new JButton("�˻�");
		sb.addActionListener(this);

		big.setBounds(300, 10, 100, 30);
		detail.setBounds(410, 10, 100, 30);
		title.setBounds(525, 10, 100, 30);
		sf.setBounds(635, 10, 500, 30);
		sf.setFont(new Font("����", Font.PLAIN, 15));
		sb.setBounds(1225, 10, 100, 30);

		((JLabel)big.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)detail.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)title.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

		add(big); add(detail); 
		add(title); add(sf); add(sb);

		column = new Vector<String>();
		column.add("�ε���"); column.add("����"); column.add("å ��ȣ");
		column.add("�۰�"); column.add("���ǻ�"); column.add("���� ��Ȳ");

		row = new Vector<Vector<String>>();
		model = new DefaultTableModel(row, column);

		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("����", Font.PLAIN, 20));

		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(233, 45));
		header.setFont(new Font("����", Font.PLAIN, 20));

		tableSP = new JScrollPane(table);
		tableSP.setBounds(100, 50, 1400, 750);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);

		add(tableSP);
	}

	public void actionPerformed(ActionEvent ae) { //�˻� ��ư�� ������ �� ����� �̺�Ʈ�� ó�����ִ� �޼ҵ�
		int index = title.getSelectedIndex();
		ide = detail.getSelectedIndex();
		t_bsort = bigS[idx];
		t_ssort = detailS[idx][ide];

		String s = sf.getText();

		if(!(s.equals(""))) {
			if(Data2[index] == "����") {
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText("");
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
				} catch(ClassNotFoundException e) {
					System.out.println("����̹� �ε忡 �����߽��ϴ�.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB ���� �Ϸ�");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
					t_title = s;
					String strSql = "SELECT * FROM book WHERE title='"+t_title+"' and bsort='"+t_bsort+"' and ssort='"+t_ssort+"';";
					ResultSet rs = dbSt.executeQuery(strSql);
					while(rs.next()) {
						t_bnum = rs.getString("bnum");
						t_title = rs.getString("title");
						t_author = rs.getString("author");
						t_publisher = rs.getString("publisher");
						t_bsort = rs.getString("bsort");
						t_ssort = rs.getString("ssort");
						t_cond = rs.getString("cond");
						t_borw = rs.getString("borw");

						if(!(t_title.equals(""))){
							if(!(t_title.equals(""))){
							Vector<String> txt = new Vector<String>();
							txt.add(Integer.toString(i++)); txt.add(t_title); txt.add(t_bnum);
							txt.add(t_author); txt.add(t_publisher);

							if(t_borw.equals("0"))
								txt.add("���Ⱑ��");
							else if(t_borw.equals("1"))
								txt.add("������");
							else if(t_borw.equals("2"))
								txt.add("��ü��");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
					}
					dbSt.close();
					con.close();
					} catch(SQLException e) {
						System.out.println("SQLException: " + e.getMessage());
					}
			}
			else if (Data2[index] == "�۰�") {
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText("");
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
				} catch(ClassNotFoundException e) {
					System.out.println("����̹� �ε忡 �����߽��ϴ�.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB ���� �Ϸ�");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
					t_author = s;
					String strSql = "SELECT * FROM book WHERE author='"+t_author+"' and bsort='"+t_bsort+"' and ssort='"+t_ssort+"';";
					ResultSet rs = dbSt.executeQuery(strSql);
					while(rs.next()) {
						t_bnum = rs.getString("bnum");
						t_title = rs.getString("title");
						t_author = rs.getString("author");
						t_publisher = rs.getString("publisher");
						t_bsort = rs.getString("bsort");
						t_ssort = rs.getString("ssort");
						t_cond = rs.getString("cond");
						t_borw = rs.getString("borw");

						if(!(t_title.equals(""))){
							if(!(t_title.equals(""))){
							Vector<String> txt = new Vector<String>();
							txt.add(Integer.toString(i++)); txt.add(t_title); txt.add(t_bnum);
							txt.add(t_author); txt.add(t_publisher);

							if(t_borw.equals("0"))
								txt.add("���Ⱑ��");
							else if(t_borw.equals("1"))
								txt.add("������");
							else if(t_borw.equals("2"))
								txt.add("��ü��");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
					}
					dbSt.close();
					con.close();
				} catch(SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
			}
			else if(Data2[index] == "���ǻ�"){
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText(""); 
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
				} catch(ClassNotFoundException e) {
					System.out.println("����̹� �ε忡 �����߽��ϴ�.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB ���� �Ϸ�");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
					t_publisher = s;
					String strSql = "SELECT * FROM book WHERE publisher='"+t_publisher+"' and bsort='"+t_bsort+"' and ssort='"+t_ssort+"';";
					ResultSet rs = dbSt.executeQuery(strSql);
					while(rs.next()) {
						t_bnum = rs.getString("bnum");
						t_title = rs.getString("title");
						t_author = rs.getString("author");
						t_publisher = rs.getString("publisher");
						t_bsort = rs.getString("bsort");
						t_ssort = rs.getString("ssort");
						t_cond = rs.getString("cond");
						t_borw = rs.getString("borw");

						if(!(t_title.equals(""))){
							if(!(t_title.equals(""))){
							Vector<String> txt = new Vector<String>();
							txt.add(Integer.toString(i++)); txt.add(t_title); txt.add(t_bnum);
							txt.add(t_author); txt.add(t_publisher);

							if(t_borw.equals("0"))
								txt.add("���Ⱑ��");
							else if(t_borw.equals("1"))
								txt.add("������");
							else if(t_borw.equals("2"))
								txt.add("��ü��");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("���� �˻�", "�˻��Ͻ� ������ �������� �ʽ��ϴ�.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
					}
					dbSt.close();
					con.close();
				} catch(SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
			}
			else;
		}
		else {
				Message md = new Message("�Է¿���", Data2[index]+"��(��) �Է����ּ���");
				md.setSize(500, 200);
				md.setLocation(100, 200);
				md.show();
		}
	}

	public void itemStateChanged (ItemEvent ie) {
		idx = big.getSelectedIndex();

		DefaultComboBoxModel model = new DefaultComboBoxModel(detailS[idx]);
		detail.setModel(model);
	}
}

class Borrow extends JPanel {
	JLabel jl;
	Vector<String> column;
	Vector<Vector<String>> row;
	JTable table = null;
	DefaultTableModel model = null;
	JScrollPane tableSP;

	int j = 1;

	public Borrow(String id) {						//����ȭ�� ������
		this.setLayout(null);

		column = new Vector<String>();
		column.add("�ε���"); column.add("����"); column.add("å ��ȣ");
		column.add("���ǻ�"); column.add("���� ��"); column.add("�ݳ� ������");
		column.add("�˸�");

		model = new DefaultTableModel(row, column);
		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("����", Font.PLAIN, 20));

		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(233, 45));
		header.setFont(new Font("����", Font.PLAIN, 20));

		tableSP = new JScrollPane(table);
		tableSP.setBounds(100, 110, 1400, 750);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);

		add(tableSP);

		String t_mid= id;
		String t_name="", t_alarm="";
		String t_bnum= "", t_title="", t_author="", t_publisher="";
		String t_bory="", t_borm="", t_bord="", t_duey="", t_duem="", t_dued="";

		row = new Vector<Vector<String>>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �ε忡 �����߽��ϴ�.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB ���� �Ϸ�");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
			String strSql = "SELECT m.name, m.alarm, bk.title, bk.publisher, bk.bnum, Br.bory, Br.borm, Br.bord, Br.duey, Br.duem, Br.dued FROM  Borw Br JOIN book bk on bk.bnum=Br.bnum JOIN mem m on m.mid=Br.id WHERE m.mid='"+t_mid+"';";
			ResultSet rs = dbSt.executeQuery(strSql);
			while(rs.next()) {
				t_name = rs.getString(1);
				t_alarm = rs.getString(2);
				t_title = rs.getString(3);
				t_publisher = rs.getString(4);
				t_bnum = rs.getString(5);
				t_bory = rs.getString(6);
				t_borm = rs.getString(7);
				t_bord = rs.getString(8);
				t_duey = rs.getString(9);
				t_duem = rs.getString(10);
				t_dued = rs.getString(11);

				String str = Integer.toString(j++);
				String bor = t_bory + "-" + t_borm + "-" + t_bord;
				String due = t_duey + "-" + t_duem + "-" + t_dued;
				model.addRow(new Object[]{str, rs.getString(3), rs.getString(5), rs.getString(4), bor, due, rs.getString(2)});				
			}
			dbSt.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		String str = t_name + "���� ���� ��Ȳ";
		jl = new JLabel(str);
		jl.setBounds(650, 10, 400, 100);
		jl.setFont(new Font("����", Font.PLAIN, 30));
		jl.setHorizontalAlignment(JLabel.CENTER);

		add(jl);
	}
}

class Apply extends JPanel  implements ActionListener{
	JTextField tf, pf, wf, f1, f2, f3;
	JLabel jl1, jl2, jl3, jl4;
	JButton ab;
	String mid;

	public Apply(String id) {						//��ûȭ�� ������
		this.setLayout(null);

		mid = id;

		jl1 = new JLabel("��û ���� ����");
		tf = new JTextField(10);

		jl2 = new JLabel("���ǻ�");
		pf = new JTextField(10);

		jl3 = new JLabel("�۰�");
		wf = new JTextField(10);

		jl4 = new JLabel("��û��(��-��-��)");
		f1 = new JTextField(10);
		f2 = new JTextField(10);
		f3 = new JTextField(10);

		jl1.setBounds(450, 40, 200, 40);
		jl2.setBounds(450, 90, 200, 40);
		jl3.setBounds(450, 140, 200, 40);
		jl4.setBounds(450, 190, 200, 40);
		tf.setBounds(780, 40, 400, 40);
		pf.setBounds(780, 90, 400, 40);
		wf.setBounds(780, 140, 400, 40);
		f1.setBounds(780, 190, 100, 40);
		f2.setBounds(900, 190, 100, 40);
		f3.setBounds(1020, 190, 100, 40);

		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl2.setHorizontalAlignment(JLabel.CENTER);
		jl3.setHorizontalAlignment(JLabel.CENTER);
		jl4.setHorizontalAlignment(JLabel.CENTER);

		jl1.setFont(new Font("����", Font.PLAIN, 20));
		jl2.setFont(new Font("����", Font.PLAIN, 20));
		jl3.setFont(new Font("����", Font.PLAIN, 20));
		jl4.setFont(new Font("����", Font.PLAIN, 20));
		tf.setFont(new Font("����", Font.PLAIN, 20));
		pf.setFont(new Font("����", Font.PLAIN, 20));
		wf.setFont(new Font("����", Font.PLAIN, 20));
		f1.setFont(new Font("����", Font.PLAIN, 20));
		f2.setFont(new Font("����", Font.PLAIN, 20));
		f3.setFont(new Font("����", Font.PLAIN, 20));

		add(jl1); add(tf); add(jl2); add(pf); add(jl3); add(wf);
		add(jl4); add(f1); add(f2); add(f3);

		ab = new JButton("��û");
		ab.addActionListener(this);

		ab.setBounds(700, 300, 200, 30);

		add(ab);
	}

	public void actionPerformed(ActionEvent ae) { //��û��ư�� ������ �� ����� �̺�Ʈ ó�� ���ִ� �޼ҵ�
		JLabel jl = new JLabel();

		jl.setBounds(600, 400, 1000, 30);
		jl.setFont(new Font("����", Font.PLAIN, 30));

		add(jl);

		String s1 = tf.getText();
		String s2 = pf.getText();
		String s3 = wf.getText();
		String s4 = f1.getText();
		String s5 = f2.getText();
		String s6 = f3.getText();

		String t_onum="", t_otitle="", t_opublish="", t_owriter="", t_oid="", t_ostate="";
		String t_bnum= "", t_title="", t_author="", t_publisher="", t_bsort="", t_dsort="", t_cond="", t_borw="";
		String t_mid = mid;

		if(!(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals(""))){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
			} catch(ClassNotFoundException e) {
				System.out.println("����̹� �ε忡 �����߽��ϴ�.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB ���� �Ϸ�");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
				t_title = s1;
				String strSql = "SELECT title, bsort FROM book WHERE title='"+t_title+"';";
				ResultSet rs = dbSt.executeQuery(strSql);
				while(rs.next()) {
					t_title = rs.getString("title");
					t_bsort = rs.getString("bsort");
				}
				dbSt.close();
				con.close();
			} catch(SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}

			if(t_title.equals(s1))
				jl.setText(t_bsort + "�� �����ϴ� ���� �Դϴ�.");
			else{
				t_onum = s4 + "-" + s5 + "-" + s6;
				t_oid = t_mid;
				t_ostate = "0";
				t_otitle = s1;
				t_opublish = s2;
				t_owriter = s3;;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
				} catch(ClassNotFoundException e) {
					System.out.println("����̹� �ε忡 �����߽��ϴ�.");
				}
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
						System.out.println("DB ���� �Ϸ�");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
					t_title = tf.getText();
					String strSql = "INSERT INTO bkorder (oday, otitle, opublish, owriter, oid, ostate) VALUES ("+"'"+t_onum+"','"+t_otitle+"','"+t_opublish+"','"+t_owriter+"','"+t_oid+"','"+t_ostate+"');";
					dbSt.executeUpdate(strSql);
					dbSt.close();
					con.close();
				} catch(SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
				jl.setText("���� ��û�� �Ϸ�Ǿ����ϴ�.");
			}
		}
		else {
				Message md = new Message("�Է¿���", "��� ĭ�� ä������ �ʾҽ��ϴ�.");
				md.setSize(500, 200);
				md.setLocation(100, 200);
				md.show();
		}
	}
}

class Personal extends JPanel implements ActionListener{
	JButton psb, infb;
	JLabel jl1, jl2, jl3, jl4, jl5;
	String mid;

	public Personal(String id) {						//��������ȭ�� ������
		this.setLayout(null);
		mid = id;
		String t_mid=id;
		String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �ε忡 �����߽��ϴ�.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB ���� �Ϸ�");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
			String strSql = "SELECT mid, name, tel, mail, address FROM mem WHERE mid ='"+t_mid+"';";
			ResultSet rs = dbSt.executeQuery(strSql);
			while(rs.next()) {
				t_mid = rs.getString("mid");
				t_name = rs.getString("name");
				t_tel = rs.getString("tel");
				t_mail = rs.getString("mail");
				t_address = rs.getString("address");
			}
			dbSt.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		jl1 = new JLabel("�̸�:" + t_name);
		jl2 = new JLabel("���̵�:" + t_mid);
		jl3 = new JLabel("��ȭ��ȣ:" + t_tel);
		jl4 = new JLabel("E-Mail:" + t_mail);
		jl5 = new JLabel("�ּ�:" + t_address);

		jl1.setBounds(10, 10, 300, 100);
		jl2.setBounds(10, 120, 300, 100);
		jl3.setBounds(10, 230, 300, 100);
		jl4.setBounds(10, 340, 300, 100);
		jl5.setBounds(10, 450, 500, 100);

		jl1.setFont(new Font("����", Font.PLAIN, 20));
		jl2.setFont(new Font("����", Font.PLAIN, 20));
		jl3.setFont(new Font("����", Font.PLAIN, 20));
		jl4.setFont(new Font("����", Font.PLAIN, 20));
		jl5.setFont(new Font("����", Font.PLAIN, 20));

		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);

		psb = new JButton("��й�ȣ ����");
		psb.addActionListener(this);
		infb = new JButton("�������� ����");
		infb.addActionListener(this);

		psb.setBounds(500, 600, 150, 30);
		infb.setBounds(1000, 600, 150, 30);

		add(psb); add(infb);
	}

	public void actionPerformed(ActionEvent ae) { // ��й�ȣ �����̳� �������� ���� ��ư�� ������ �� ����� �̺�Ʈ ó�� ���ִ� �޼ҵ�
		String s = ae.getActionCommand();

		if (s == "��й�ȣ ����") {
			NewPass np = new NewPass(mid);
			np.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			np.setSize(800, 500);
			np.setLocation(400, 300);
			np.setVisible(true);
		}
		else if (s == "�������� ����") {
			ChangeInfo ci = new ChangeInfo(mid);
			ci.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ci.setSize(800, 500);
			ci.setLocation(400, 300);
			ci.setVisible(true);
		}
		else ;
	}
}

class NewPass extends JFrame implements ActionListener{
	JLabel jl1, jl2;
	JPasswordField tf1, tf2;
	JButton btn;
	String id;

	public NewPass(String mid) {				//��й�ȣ ����ȭ�� ������
		this.setTitle("��й�ȣ ����ȭ��");
		id = mid;

		Container ct = getContentPane();
		ct.setLayout(null);		

		jl1 = new JLabel("�� ��й�ȣ");
		jl2 = new JLabel("��й�ȣ Ȯ��");

		tf1 = new JPasswordField(20);
		tf2 = new JPasswordField(20);

		btn = new JButton("��й�ȣ ����");
		btn.addActionListener(this);

		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl2.setHorizontalAlignment(JLabel.CENTER);

		jl1.setBounds(100, 130, 200, 30);
		jl2.setBounds(100, 180, 200, 30);
		jl1.setFont(new Font("����", Font.PLAIN, 15));
		jl2.setFont(new Font("����", Font.PLAIN, 15));

		tf1.setBounds(320, 130, 300, 30);
		tf2.setBounds(320, 180, 300, 30);
		tf1.setFont(new Font("����", Font.PLAIN, 15));
		tf2.setFont(new Font("����", Font.PLAIN, 15));

		btn.setBounds(300, 300, 200, 30);

		ct.add(jl1); ct.add(jl2);
		ct.add(tf1); ct.add(tf2);;
		ct.add(btn);
	}

	public void actionPerformed(ActionEvent ae) { // ��й�ȣ �����ư�� ������ �� ����� �̺�Ʈ�� ó�����ִ� �޼ҵ�
		String s1 = tf1.getText();
		String s2 = tf2.getText();

		if(!(s1.equals("") || s2.equals(""))) {
			if(s1.equals(s2)){
				String str = tf1.getText();		
				CheckingPass ck = new CheckingPass(str, this, id);
				ck.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ck.setSize(500, 200);
				ck.setLocation(400, 300);
				ck.show();
			}
			else {
				Message md = new Message("�Է¿���", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				md.setSize(500, 200);
				md.setLocation(100, 200);
				md.show();
				tf1.setText(""); tf2.setText("");
			}
		}
		else {
			Message md = new Message("�Է¿���", "������ ��й�ȣ�� �Է����ּ���.");
			md.setSize(500, 200);
			md.setLocation(100, 200);
			md.show();
			tf1.setText(""); tf2.setText("");
		}
	}
}


class ChangeInfo extends JFrame implements ActionListener{
	Container ct = getContentPane();
	JLabel jl1, jl2;
	JLabel j1, j2, j3, l1, l2, l3;
	JTextField tf1, tf2;
	JButton btn;
	String id1;

	public ChangeInfo(String id) {					//�������� ����ȭ�� ������
		this.setTitle("�������� ����ȭ��");
		id1 = id;

		Container ct = getContentPane();
		ct.setLayout(null);

		String t_mid=id;
		String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹� �ε忡 �����߽��ϴ�.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB ���� �Ϸ�");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
			String strSql = "SELECT mid, name, tel FROM mem WHERE mid ='"+t_mid+"';";
			ResultSet rs = dbSt.executeQuery(strSql);
			while(rs.next()) {
				t_mid = rs.getString("mid");
				t_name = rs.getString("name");
				t_tel = rs.getString("tel");
			}
			dbSt.close();
			con.close();
		} catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

		jl1 = new JLabel("E-Mail");
		jl2 = new JLabel("�ּ�");

		j1 = new JLabel("�̸�");
		j2 = new JLabel("���̵�");
		j3 = new JLabel("��ȭ��ȣ");

		l1 = new JLabel(t_name);
		l2 = new JLabel(t_mid);
		l3 = new JLabel(t_tel);

		tf1 = new JTextField(10);
		tf2 = new JTextField(10);

		l1.setHorizontalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.CENTER);

		btn = new JButton("����");
		btn.addActionListener(this);

		jl1.setFont(new Font("����", Font.PLAIN, 15));
		jl2.setFont(new Font("����", Font.PLAIN, 15));

		j1.setFont(new Font("����", Font.PLAIN, 15));
		j2.setFont(new Font("����", Font.PLAIN, 15));
		j3.setFont(new Font("����", Font.PLAIN, 15));

		l1.setFont(new Font("����", Font.PLAIN, 15));
		l2.setFont(new Font("����", Font.PLAIN, 15));
		l3.setFont(new Font("����", Font.PLAIN, 15));

		tf1.setFont(new Font("����", Font.PLAIN, 15));
		tf2.setFont(new Font("����", Font.PLAIN, 15));

		j1.setBounds(100, 10, 200, 30);
		j2.setBounds(100, 60, 200, 30);
		j3.setBounds(100, 110, 200, 30);

		l1.setBounds(320, 10, 200, 30);
		l2.setBounds(320, 60, 200, 30);
		l3.setBounds(320, 110, 200, 30);

		jl1.setBounds(100, 160, 200, 30);
		jl2.setBounds(100, 210, 200, 30);

		tf1.setBounds(320, 160, 300, 30);
		tf2.setBounds(320, 210, 300, 30);

		btn.setBounds(250, 310, 200, 30);

		ct.add(j1); ct.add(j2); ct.add(j3);
		ct.add(l1); ct.add(l2); ct.add(l3);
		ct.add(jl1); ct.add(jl2);
		ct.add(tf1); ct.add(tf2);;
		ct.add(btn);
	}

	public void actionPerformed(ActionEvent ae) { // ���� ��ư�� ������ �� ����� �̺�Ʈ�� ó�����ִ� �޼ҵ�
		String str1 = tf1.getText();
		String str2 = tf2.getText();

		if(!(str1.equals("") || str2.equals(""))) {
			CheckingInfo ck = new CheckingInfo(str1, str2, this, id1);
			ck.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ck.setSize(500, 200);
			ck.setLocation(400, 300);
			ck.show();
		}
		else {
			Message md = new Message("�Է¿���", "����� ������ �Է����ּ���");
			md.setSize(500, 200);
			md.setLocation(100, 200);
			md.show();
			tf1.setText(""); tf2.setText("");
		}
	}
}

class CheckingPass extends JFrame implements ActionListener {
	String str1;
	NewPass n;
	String id;

	public CheckingPass(String s1, NewPass np, String mid) {
		this.setTitle("����Ȯ��");
		id = mid;

		str1 = s1;
		n = np;

		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(10, 0));

		JPanel center = new JPanel();
		JPanel bottom = new JPanel();

		ct.add(center, BorderLayout.CENTER);
		ct.add(bottom, BorderLayout.SOUTH);

		JLabel j = new JLabel("�����Ͻðڽ��ϱ�?");
		j.setFont(new Font("����", Font.PLAIN, 20));
		j.setHorizontalAlignment(JLabel.CENTER);
		center.add(j);

		JButton btn1 = new JButton("Ȯ��");
		JButton btn2 = new JButton("���");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		bottom.add(btn1); bottom.add(btn2);
	}

	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();

		if(s == "���") 
			dispose();
		else {
			String t_mid=id;
			String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
			} catch(ClassNotFoundException e) {
				System.out.println("����̹� �ε忡 �����߽��ϴ�.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB ���� �Ϸ�");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
				
				String strSql = "UPDATE mem SET passwd='"+str1+"' WHERE mid='"+t_mid+"';";
				dbSt.executeUpdate(strSql);
				dbSt.close();
				con.close();
			} catch(SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			dispose();
			n.dispose();
		}
	}
}

class CheckingInfo extends JFrame implements ActionListener {
	String str1, str2;
	ChangeInfo c;
	String id;

	public CheckingInfo(String s1, String s2, ChangeInfo ci, String mid) {
		this.setTitle("����Ȯ��");
		id = mid;

		str1 = s1; str2 = s2;
		c = ci;

		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(10, 0));

		JPanel center = new JPanel();
		JPanel bottom = new JPanel();

		ct.add(center, BorderLayout.CENTER);
		ct.add(bottom, BorderLayout.SOUTH);

		JLabel j = new JLabel("�����Ͻðڽ��ϱ�?");
		j.setFont(new Font("����", Font.PLAIN, 20));
		j.setHorizontalAlignment(JLabel.CENTER);
		center.add(j);

		JButton btn1 = new JButton("Ȯ��");
		JButton btn2 = new JButton("���");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		bottom.add(btn1); bottom.add(btn2);
	}

	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();

		if(s == "���") 
			dispose();
		else {
			String t_mid=id;
			String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC-ODBC ����̹��� ���������� �ε���");
			} catch(ClassNotFoundException e) {
				System.out.println("����̹� �ε忡 �����߽��ϴ�.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB ���� �Ϸ�");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
				
				String strSql = "UPDATE mem SET mail='"+str1+"',address='"+str2+"' WHERE mid='"+t_mid+"';";
				dbSt.executeUpdate(strSql);
				dbSt.close();
				con.close();
			} catch(SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			dispose();
			c.dispose();
		}
	}
}

class Message extends JFrame implements ActionListener {
	Button btn;
	Message(String title, String msg) {
		this.setTitle(title);
		JPanel pc = new JPanel();
		JLabel label = new JLabel(msg);
		pc.add(label);
		label.setFont(new Font("����", Font.PLAIN, 15));
		add(pc, BorderLayout.CENTER);
		JPanel ps = new JPanel();
		btn = new Button("Ȯ��");
		btn.addActionListener(this);
		ps.add(btn);
		add(ps, BorderLayout.SOUTH);
		pack();
	}
	public void actionPerformed(ActionEvent ae) {
		dispose();
	}
}

class Scene2 extends JFrame {
	public Scene2(String mid) {
		JTabbedPane jtp = new JTabbedPane();	//JTabbedPane ��ü ����
		Search se = new Search();			//�˻�ȭ��
		Borrow bo = new Borrow(mid);			//����ȭ��
		Apply ap = new Apply(mid);			//��ûȭ��
		Personal pe = new Personal(mid);		//��������ȭ��

		jtp.addTab("���� �˻�", se);
		jtp.addTab("���� ���� ��Ȳ", bo);
		jtp.addTab("���� ��û", ap);
		jtp.addTab("��������", pe);

		Container ct = getContentPane();
		ct.add(jtp);
	}
}
class Book extends JPanel implements ActionListener{ //�������� Ŭ���� 
  Calendar calendar = new GregorianCalendar(Locale.KOREA);
  int nYear = calendar.get(Calendar.YEAR);
  int nMonth = calendar.get(Calendar.MONTH) + 1;
  int nDay = calendar.get(Calendar.DAY_OF_MONTH);
  Vector<String> bookIF;
  Vector<Vector<String>> rowData;
  JTable table =null;
  JLabel label;
  DefaultTableModel model = null;
  JScrollPane tableSP;
  JComboBox boState, BState;
  JButton Bplus, search;
  String boSta[]={"��ü", "����", "��ü", "���Ⱑ��"};
  String bSta[] ={"��ü", "��ȣ", "�ҷ�"};
  String b_bnum, t_Bbnum;
  public Book(){
    this.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER); 

    bookIF = new Vector<String>();
    bookIF.add("������ȣ");  bookIF.add("����");
    bookIF.add("���ǻ�");  bookIF.add("�۰�");
    bookIF.add("���⿩��");  bookIF.add("����");
    bookIF.add("�ݳ�������");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, bookIF);
    table = new JTable(model);   
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.setRowHeight(30);

      //ó�� ȭ�� ���� �� ��� ���� �� �� �ְ�
    try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325"); 
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        model.setRowCount(0);  
        String info_Int;
        info_Int = "SELECT bnum, title, author, publisher, cond, borw FROM book;";
        ResultSet rs=dbSt.executeQuery(info_Int);     // DB�κ��� �о�� ���ڵ带 ��üȭ    
        while(rs.next()){  
          String co = rs.getString(5);
          String bo =rs.getString(6);
          if(co.equals("0"))
            co="��ȣ";
          else if(co.equals("1"))
            co="�ҷ�";

          if(bo.equals("0"))
           bo="���Ⱑ��";
          else if(bo.equals("1"))
            bo="����";      
           else if(bo.equals("2"))
            bo="��ü";         
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), bo, co});
         }
       dbSt.close();
       con.close();
       } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }

      //����� å�϶��� �ݳ� �������� �߰�
      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt1 = con1.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String date;
        date = "SELECT * from Borw;";
        ResultSet rs1=dbSt1.executeQuery(date);
         while(rs1.next()){
          int year, month, day;
          for(int i = 0; i<table.getRowCount(); i++){
            t_Bbnum = rs1.getString("bnum");
            String bnum= (String)table.getValueAt(i, 0);
           if(bnum.equals(t_Bbnum)){ 
            model.setValueAt(rs1.getString("duey")+"-"+rs1.getString("duem")+"-"+rs1.getString("dued"), i, 6);
            year = rs1.getInt("duey");
            month = rs1.getInt("duem");
            day = rs1.getInt("dued");
            if(nYear==year){
             if(nMonth==month){
               if(nDay>day)
                 model.setValueAt("��ü", i, 4);
                 }
             else if(nMonth>month)
               model.setValueAt("��ü", i, 4); }
             else if(nYear>year)
               model.setValueAt("��ü", i, 4); 
            }else
            ; }       }
        dbSt1.close();
        con1.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }
     for(int i =0; i<table.getRowCount(); i++){
       if(((String)model.getValueAt(i,4))=="��ü")
         b_bnum=(String)model.getValueAt(i,0);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String Bborrow = "Update book SET borw ='"+2+"' WHERE bnum='"+b_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("������ ���� �Ϸ�");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }//��ü�� ��� ���� ���¸� 2�� �ٲٱ�
     
    boState = new JComboBox(boSta);
    BState = new JComboBox(bSta); 
    boState.addActionListener(this);
    BState.addActionListener(this);
 
    Bplus = new JButton("�߰�");
    Bplus.addActionListener(this);
    search = new JButton("��ȸ");
    search.addActionListener(this);
    top.setLayout(new FlowLayout());
    JLabel label = new JLabel("���� ���");

    top.add(boState,FlowLayout.LEFT);  top.add(BState);  top.add(label);  top.add(Bplus); top.add(search);
  } 

  public void actionPerformed(ActionEvent ae){ 
    if(ae.getActionCommand().equals("��ȸ")){ 
      String brw = boState.getSelectedItem().toString();
      String bk = BState.getSelectedItem().toString(); 
       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325"); 
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");  
        model.setRowCount(0);
     
       String info_Int;
       info_Int =  "SELECT bnum, title, author, publisher, cond, borw FROM book;";
       ResultSet rs=dbSt.executeQuery(info_Int); 
       while(rs.next()){         
          int i=0; 
          String c = rs.getString(5);
          String b = rs.getString(6);
         
          if(c.equals("0"))
            c="��ȣ";
          else if(c.equals("1"))
            c="�ҷ�";

          if(b.equals("0"))
           b="���Ⱑ��";
          else if(b.equals("1"))
            b="����";
          else if(b.equals("2"))
            b="��ü";
           if(brw==b&&bk==c){
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});
            System.out.println(b); System.out.println(c);}
         else if(brw.equals("��ü")||bk.equals("��ü"))
            if(brw==b || bk==c)
              model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});
          else if(brw.equals("��ü")&&bk.equals("��ü"))
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});        
         } //�޺��ڽ� ���� ���� ������¿� ���¸� ���� å �˻�
       dbSt.close();
       con.close();
       } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }     
      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt1 = con1.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String date;
        date = "SELECT * from Borw;";
        ResultSet rs1=dbSt1.executeQuery(date);
         while(rs1.next()){
          int year, month, day;
          for(int i = 0; i<table.getRowCount(); i++){
            t_Bbnum = rs1.getString("bnum");
            String bnum= (String)table.getValueAt(i, 0);
           if(bnum.equals(t_Bbnum)){ 
            model.setValueAt(rs1.getString("duey")+"-"+rs1.getString("duem")+"-"+rs1.getString("dued"), i, 6);
            year = rs1.getInt("duey");
            month = rs1.getInt("duem");
            day = rs1.getInt("dued");
            if(nYear==year){
             if(nMonth==month){
               if(nDay>day)
                 model.setValueAt("��ü", i, 4);}
             else if(nMonth>month)
               model.setValueAt("��ü", i, 4); }
             else if(nYear>year)
               model.setValueAt("��ü", i, 4); 
            }else
            ; }       }
        dbSt1.close();
        con1.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }
        for(int i =0; i<table.getRowCount(); i++){
       if(((String)model.getValueAt(i,4))=="��ü")
         b_bnum=(String)model.getValueAt(i,0);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String Bborrow = "Update book SET borw ='"+2+"' WHERE bnum='"+b_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("������ ���� �Ϸ�");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }//��ü�� ��� ���� ���¸� 2�� �ٲٱ�
       }
         
  else if(ae.getActionCommand().equals("�߰�")){ //�߰� ��ư ������ ���� �߰� â
      NewBook plus = new NewBook();
      plus.setSize(350, 450);
      plus.setLocation(400, 300);
      plus.show(); }
  }

}

class NewBook extends JFrame implements ActionListener, ItemListener {//���� �߰��ϴ� Ŭ����
  JTextField num, title, author, publisher, bsort, ssort;
  String[] big = {"�ѷ�", "ö��", "����", "��ȸ����", "��������", "�������", "����", "����", "����", "����"};
  String[][] small = {{"�ѷ� ����Ʈ����", "������, ������", "����������", "�������", "������, ������", "�Ϲ� ���� ���๰", "�Ϲ���ȸ", "�Ź�, ���", "�Ϲ� ����", "�����ڷ�"}, 
          {"ö���Ϲ�", "���̻���", "ö��ü��", "����",  "����ö��", "����", "�ɸ���", "������, ����ö��"}, 
          {"�����Ϲ�", "������", "�ұ�", "�⵶��", "����", "õ����", "�ŵ�", "�ٶ󹮱�", "�̽�����", "��Ÿ ������"}, 
          {"��ȸ�����Ϲ�", "�����", "������", "��ȸ��", "��ġ��", "������", "����", "������", "ǳ�ӹμ���", "���決����"}, 
          {"���������Ϲ�", "����", "������", "ȭ��", "õ����", "����", "������", "�������", "�Ĺ���", "������"}, 
          {"��������Ϲ�", "����, ����", "���, ������", "����, �����Ϲ�", "������", "�������", "ȭ�а���", "������, �μ��", "������ �� ������ȭ"}, 
          {"�����Ϲ�", "�����", "����", "���� ��Ĺ̼�", "����", "ȸȭ, ��ȭ", "������", "����", "����, ��ȭ", "����, �"}, 
          {"�����Ϲ�", "�ѱ���", "�߱���", "�Ϻ���", "����", "���Ͼ�", "��������", "�����ξ�", "��Ż���ƾ�", "��Ÿ����"}, 
          {"�����Ϲ�", "�ѱ�����", "�߱�����", "�Ϻ�����", "���̹���", "���Ϲ���", "����������", "�����ι���", "��Ż���ƹ���", "��Ÿ������"}, 
          {"�����Ϲ�", "�ƽþ�", "����", "������ī", "�ϾƸ޸�ī", "���Ƹ޸�ī", "�����ƴϾ�", "��������", "��������", "��������"}};
  JComboBox Bsort, Ssort;
  int idx, ide;

  public NewBook(){
    Container ct = getContentPane();
    ct.setLayout(new BorderLayout(0, 20)); 
    JPanel  top = new JPanel();
    JPanel  bottom = new JPanel();
    top.setLayout(new GridLayout(6, 1)); 
    ct.add(bottom, BorderLayout.SOUTH); 
    ct.add(top, BorderLayout.CENTER); 
    
    JPanel p0 = new JPanel();
    p0.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel lb = new JLabel("��з�: ");
    JPanel p1 = new JPanel();
    Bsort = new JComboBox(big);
    JLabel ls = new JLabel("���з�: ");
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    Ssort = new JComboBox(small[0]);
    Bsort.addItemListener(this);
    Ssort.addActionListener(this);    
    p0.add(lb);  p1.add(ls);  p0.add(Bsort); p1.add(Ssort); 
   
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l1 = new JLabel("å��ȣ: ");
    num = new JTextField (8);
    p2.add(l1);  p2.add(num); 

    JPanel p3 = new JPanel();
    p3.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l2 = new JLabel("���� : ");
    title = new JTextField (8);
    p3.add(l2); p3.add(title);  

    JPanel p4 = new JPanel();
    p4.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l3 = new JLabel("�۰� : ");
    author = new JTextField (8);  
    p4.add(l3); p4.add(author);

    JPanel p5 = new JPanel();
    p5.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l4 = new JLabel("���ǻ�: ");
    publisher = new JTextField (10);
    p5.add(l4); p5.add(publisher); 

    top.add(p0);  top.add(p1);  top.add(p2);  top.add(p3);  top.add(p4);  top.add(p5);


    JButton b1 = new JButton("Ȯ��");
    JButton b2 = new JButton("���");
    b1.addActionListener(this);
    b2.addActionListener(this);
    bottom.add(b1);  bottom.add(b2);
   
  }
public void actionPerformed(ActionEvent ae){
  String s = ae.getActionCommand();
  String t_num="", t_title="", t_author="", t_publisher="", t_bsort="", t_ssort="";
  MessageDialog md, md1;
  ide = Ssort.getSelectedIndex();
  if ( s=="���") {//��� ������ �ʱ�ȭ
    num.setText("");  title.setText("");  author.setText("");
    publisher.setText(""); 
    Bsort.setSelectedItem((Object)big[0]);  Ssort.setSelectedItem((Object)small); }
  else if(s=="Ȯ��"){      
     t_num=num.getText();  t_title=title.getText();  
      t_author=author.getText();  t_publisher=publisher.getText(); 
      t_bsort=(String)Bsort.getSelectedItem();  t_ssort=(String)Ssort.getSelectedItem();
    if(t_num.equals("")||t_title.equals("")||t_author.equals("")||t_publisher.equals("")){//���� �߰��� �ϳ��� ��� ��� �߰�
     md = new MessageDialog(this, "���� �߰�", true,"��� ������ �Է����ּ���"); 
     md.show(); }
    else{
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
      } catch(ClassNotFoundException e) {
      System.err.println("����̹� �ε忡 �����߽��ϴ�.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB ���� �Ϸ�."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
      String strSql ="INSERT INTO book (bnum, title, author, publisher, bsort, ssort) VALUES ("+"'"+t_num+"', '"+t_title+"', '"+  t_author+"', '"+ t_publisher+"', '"+t_bsort+"','"+t_ssort+"');";
      dbSt.executeUpdate(strSql);
      System.out.println("������ ���� �Ϸ�");
      md1 = new MessageDialog(this, "���� �߰�", true,"������ �߰��Ǿ����ϴ�."); 
      md1.show(); 
      dbSt.close();       
      con.close();
     } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
      dispose();//Ȯ�δ����� ���� �߰��Ǹ� ���� �߰� â ����
      }
     } 
    }
  public void itemStateChanged(ItemEvent ie){//��з��� ���� ���з� �ٷ� �ٷ� �ٲ�
      idx = Bsort.getSelectedIndex();
      DefaultComboBoxModel model = new DefaultComboBoxModel(small[idx]);
      Ssort.setModel(model);    
   }
}
class User extends JPanel implements ActionListener, MouseListener{// ȸ�� ���� Ŭ����
  Vector<String> useIF;
  Vector<Vector<String>> rowData;
  JTable table;
  DefaultTableModel model = null;
  JScrollPane tableSP;
  JButton research;
   String s_id="";
  public User(){
    this.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER); 
    top. add(new JLabel("ȸ�� ����"));
    research = new JButton("����ȸ");
    top.add(research);
    research.addActionListener(this);
    useIF = new Vector<String>();
    useIF.add("ID");  useIF.add("�̸�");  useIF.add("��ȭ��ȣ");  
    useIF.add("E-Mail");  useIF.add("�ּ�");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, useIF);
    table = new JTable(model);   
    
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
   table.addMouseListener(this);
   table.setRowHeight(25);
   //ȸ�� ���� ���� �� ��� ȸ�� �� �� �ֵ���
   try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
      } catch(ClassNotFoundException e) {
      System.err.println("����̹� �ε忡 �����߽��ϴ�.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB ���� �Ϸ�."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
      model.setRowCount(0); 
        String info_mem;
        info_mem = "SELECT * FROM mem;";  
        ResultSet rs=dbSt.executeQuery(info_mem);    
        while(rs.next()){  
         model.addRow(new Object[]{rs.getString("mid"),rs.getString("name"),rs.getString("tel"), rs.getString("mail"),rs.getString("address")});
         }
      dbSt.close();       
      con.close();
      } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
  }  

  public void actionPerformed(ActionEvent ae){
    if(ae.getActionCommand()=="����ȸ"){
     try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
      } catch(ClassNotFoundException e) {
      System.err.println("����̹� �ε忡 �����߽��ϴ�.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB ���� �Ϸ�."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
      model.setRowCount(0); 
        String info_mem;
        info_mem = "SELECT * FROM mem;";  
        ResultSet rs=dbSt.executeQuery(info_mem);    
        while(rs.next()){  
         model.addRow(new Object[]{rs.getString("mid"),rs.getString("name"),rs.getString("tel"), rs.getString("mail"),rs.getString("address")});
         }
      dbSt.close();       
      con.close();
      } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }
      }
  public void mouseClicked (MouseEvent m){
     int row = (int)table.getSelectedRow();   
     s_id = (String)table.getValueAt(row, 0);
    DoMember dM= new DoMember(s_id);
    dM.setSize(360, 150);
    dM.setLocation(400, 300);
    dM.show();    
  }//Ŭ���ϸ� �� â������ ȸ���� ���õ� �۾��ϰ�
  public void mousePressed (MouseEvent m){}
  public void mouseReleased (MouseEvent m){}
  public void mouseEntered (MouseEvent m){}
  public void mouseExited (MouseEvent m){}
}

class DoMember extends JFrame implements ActionListener{
  JButton ok, cancel, search;
  int del_id;
  public DoMember(String id){
    del_id=Integer.parseInt(id);
    Container ct = getContentPane();
    ct.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    ct.add(top, BorderLayout.NORTH);
    ct.add(bottom, BorderLayout.CENTER); 
    JLabel warning = new JLabel("� �۾��� �Ͻðڽ��ϱ�?");
     ok = new JButton("����");
    cancel = new JButton("���");
    search = new JButton("��ȸ");
     top.add(warning); bottom.add(ok); bottom.add(cancel); bottom.add(search); 
    ok.addActionListener(this); cancel.addActionListener(this); search.addActionListener(this);
  }

  public void actionPerformed(ActionEvent ae){
  String s = ae.getActionCommand();
  if(s=="����"){//���� ��ư ������ �� ȸ�� ����
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
      } catch(ClassNotFoundException e) {
      System.err.println("����̹� �ε忡 �����߽��ϴ�.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB ���� �Ϸ�."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
      String delete;
      delete = "DELETE FROM mem WHERE mid='"+del_id+"';";
      dbSt.executeUpdate(delete);
      MessageDialog md = new MessageDialog(this, "ȸ������ ����", true,"�����Ǿ����ϴ�");    
      md.show();        System.out.println("������ ���� �Ϸ�");
      dbSt.close();       
      con.close();
     } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
      } 
    
    else if(s=="���")//��� ��ư ������ �� �߰� �۾� â ����
      ;
    else if(s=="��ȸ"){//��ȸ ��ư ������ �� �� ȸ���� ����, ��û ���� �� �� �ִ� â ����
       UserNew new_ = new UserNew(del_id);
       new_.setSize(720, 600);
       new_.setLocation(400, 300);
       new_.show(); }     
      dispose();
  }
}

class UserBook extends JPanel implements ActionListener{//ȸ���� ���� ���� Ŭ����
  Vector<String> MyBook;
  Vector<Vector<String>> rowData;
  JTable table =null;
  DefaultTableModel model = null;
  JScrollPane tableSP;
  JButton message;
  int m_id;
  Calendar calendar = new GregorianCalendar(Locale.KOREA);
  int nYear = calendar.get(Calendar.YEAR);
  int nMonth = calendar.get(Calendar.MONTH) + 1;
  int nDay = calendar.get(Calendar.DAY_OF_MONTH);

  public UserBook(int s_id){
    m_id=s_id;

    this.setLayout(new FlowLayout(FlowLayout.CENTER));
    MyBook = new Vector<String>();
    MyBook.add("������ȣ");  MyBook.add("����");  
    MyBook.add("���ǻ�");  MyBook.add("������");  
    MyBook.add("�ݳ���");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, MyBook);
    table = new JTable(model);    
    table.setRowHeight(25);
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
  
    message = new JButton("��������");
    this.add(message); 
    message.addActionListener(this);
    message.setEnabled(false);

      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String book_info;   
        book_info = "SELECT b.bnum, b.title, b.publisher, bo.bory, bo.borm, bo.bord, bo.duey, bo.duem, bo.dued from book b JOIN Borw bo on b.bnum=bo.bnum WHERE bo.id='"+m_id+"';";
        ResultSet rs=dbSt.executeQuery(book_info);
         while(rs.next()){
           int year, month, day;
           year = rs.getInt(7);
           month = rs.getInt(8);
           day = rs.getInt(9);
           if(nYear==year){
             if(nMonth==month){
               if(nDay>day)
                 message.setEnabled(true);}
              else if(nMonth>month)
                 message.setEnabled(true); }
             else if(nYear>year)
               message.setEnabled(true);
           model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)+rs.getString(5)+rs.getString(6), rs.getString(7)+rs.getString(8)+rs.getString(9)});
                }//å�� ��ü �Ǿ����� �������� ��ư Ȱ��ȭ �Ǿ� �˸� ����
        dbSt.close();
        con.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }      
  }

  public void actionPerformed(ActionEvent ae){       
    if(ae.getActionCommand()=="��������"){       
       sendText m= new sendText(m_id);
       m.setSize(360, 300);
       m.setLocation(400, 300);
       m.show();
     }
  } //���� ���� ��ư ������ ��    
}   

class sendText extends JFrame {
    // ���� ���۽� �ߴ� â
    int s_id;
    public sendText(int m_id) {
     s_id=m_id;
     JPanel alram = new JPanel();
     setContentPane(alram);  
     JLabel text = new JLabel("���ڸ� �����߽��ϴ�.");   
     alram.add(text);  
     setSize(300,50); 
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String Bborrow = "Update mem SET alarm ='"+"��ü�� ������ �ֽ��ϴ�"+"' WHERE mid='"+s_id+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("������ ���� �Ϸ�");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }//���� ���� ������ �� ���۵Ǵ� �˸�
  }
}

class UserApBook extends JPanel {//ȸ���� ��û ���� Ŭ����
  Vector<String> myApplyBook;
  Vector<Vector<String>> rowData;
  JTable table =null;
  DefaultTableModel model= null;  
  JScrollPane tableSP;
  int m_id;
  public UserApBook(int id){
    m_id=id;
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
    myApplyBook = new Vector<String>();
    myApplyBook.add("��û ��¥");  myApplyBook.add("����");
    myApplyBook.add("���ǻ�");  myApplyBook.add("���� ����");

    model = new DefaultTableModel(rowData, myApplyBook);
    table = new JTable(model);  
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
  table.setRowHeight(25);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String book_info;   
        book_info = "SELECT  bk.oday, bk.otitle, bk.opublish, bk.ostate from bkorder bk JOIN mem m on bk.oid=m.mid WHERE m.mid='"+m_id+"';";
        ResultSet rs=dbSt.executeQuery(book_info);
         while(rs.next()){
           String s = rs.getString(4);
           String sta = "";
           if(s.equals("0"))
             sta="�̱���";
           else
             sta ="����";
           model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),sta});
                }
        dbSt.close();
        con.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }      
  }
}

class UserNew extends JFrame{//ȸ�� ��ȸ ������ �� �ߴ� â
  int m_id;
  public UserNew(int id){
    m_id=id;
    JTabbedPane jtp = new JTabbedPane();
    UserBook ub = new UserBook(m_id);
    UserApBook uab = new UserApBook(m_id);
    
    jtp.addTab("���� ����", ub);
    jtp.addTab("��û ����", uab);
    Container ct = getContentPane();
    ct.add(jtp);
  }    
}

class ApplyBook extends JPanel{//��û ���� Ŭ����

  Vector<String> ApplyBook;
  Vector<Vector<String>> rowData;
  JTable table =null;
  DefaultTableModel model= null;  
  JScrollPane tableSP;

  public ApplyBook(){
    this.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER); 

    ApplyBook = new Vector<String>();
    ApplyBook.add("��û ��¥");  ApplyBook.add("����");
    ApplyBook.add("���ǻ�");  ApplyBook.add("��û ȸ�� ��ȣ");  ApplyBook.add("���� ����");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, ApplyBook);
    table = new JTable(model);   
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.setRowHeight(25);
    top.add(new JLabel("��û ���� ���"));
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
      } catch(ClassNotFoundException e) {
      System.err.println("����̹� �ε忡 �����߽��ϴ�.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB ���� �Ϸ�."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String info_Appbok;
        info_Appbok = "SELECT * FROM bkorder;";  
        ResultSet rs=dbSt.executeQuery(info_Appbok);     
        while(rs.next()){
         String s = rs.getString("ostate");
         String sta = "";
         if(s.equals("0"))
           sta="�̱���";
         else
           sta ="����";
         model.addRow(new Object[]{rs.getString("oday"),rs.getString("otitle"),rs.getString("opublish"), rs.getString("oid"), sta});
         }
      dbSt.close();       
      con.close();
      } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
  }
}

class BorrowBook extends JPanel implements ActionListener, MouseListener{//���� Ŭ����
  JButton search, borrow;  
  JTextField ID, Book;
  Vector<String> BorrowBook;
  Vector<Vector<String>> rowData;
  JTable table =null;
  DefaultTableModel model= null;  
  JScrollPane tableSP;
      String t_bnum="";
  public BorrowBook(){
    this.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER);
    JPanel Bbtn = new JPanel();
    this.add(Bbtn, BorderLayout.SOUTH);
    top.setLayout(new GridLayout(3,1));
    JPanel p0= new JPanel(); 
    JLabel id = new JLabel("ID: ");
    ID = new JTextField (15);
    p0.add(id);  p0.add(ID); top.add(p0);
    p0.setLayout(new FlowLayout(FlowLayout.LEFT));
    JPanel p1= new JPanel();
    JLabel book = new JLabel("����: ");
    Book = new JTextField (15);
    JButton search = new JButton("�˻�");
    search.addActionListener(this);
    search.setSize(20,10);
    p1.add(book);  p1.add(Book); p1.add(search); top.add(p1);
   p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    BorrowBook = new Vector<String>();
    BorrowBook.add("���� ��ȣ");  BorrowBook.add("����");
    BorrowBook.add("���ǻ�");  BorrowBook.add("���� ����"); 
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, BorrowBook);
    table = new JTable(model);
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.addMouseListener(this);
    table.setRowHeight(25);
   JButton borrow = new JButton("����");
   Bbtn.add(borrow);
   borrow.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent ae){
    String s = ae.getActionCommand();
    String t_id =ID.getText(), t_title=Book.getText();
    int nYear, rYear=0;
    int nMonth, rMonth=0;
    int nDay, rDay=0;
    Calendar calendar = new GregorianCalendar(Locale.KOREA);
    nYear = calendar.get(Calendar.YEAR);
    nMonth = calendar.get(Calendar.MONTH) + 1;
    nDay = calendar.get(Calendar.DAY_OF_MONTH);

    if(nMonth==1||nMonth==3||nMonth==5||nMonth==7||nMonth==8||nMonth==10||nMonth==12){
        if(nDay+14>31){
          if(nMonth==12)
            rYear=nYear+1;
          rMonth=nMonth+1;
          rDay=nDay+14-31;}
        else{
          rYear=nYear;
          rMonth=nMonth;
          rDay=nDay+14; }
        }
    else if(nMonth==4||nMonth==6||nMonth==9||nMonth==11){
      if(nDay+14>30){
          rYear=nYear;
          rMonth=nMonth+1;
          rDay=nDay+14-30;}
        else{
          rYear=nYear;
          rMonth=nMonth;
          rDay=nDay+14; }
        }
    else{
      if(nYear%4==0){
        if(nDay+14>29){
            rMonth=nMonth+1;
            rDay=nDay+14-29;}
        else{
            rMonth=nMonth;
            rDay=nDay+14; }
          }
      else{
        if(nDay+14>28){
          rMonth=nMonth+1;
          rDay=nDay+14-28;}
        else{
          rMonth=nMonth;
          rDay=nDay+14; }
        }
     }// ���� �� �ݳ� ������ ����
    if(s.equals("�˻�")){
       model.setRowCount(0);
       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String sqlbook;
        sqlbook = "SELECT * FROM book WHERE title='"+t_title+"';";
        ResultSet rs=dbSt.executeQuery(sqlbook);     // DB�κ��� �о�� ���ڵ带 ��üȭ
        while(rs.next()){  
        int year, month, day;
         String b = rs.getString("borw");
         String bo ="";
         if(b.equals("0"))
           bo="���Ⱑ��";
         else if(b.equals("1"))
            bo="����";
         else if(b.equals("2"))
            bo="��ü";
         model.addRow(new Object[]{rs.getString("bnum"),rs.getString("title"),rs.getString("publisher"),bo});
         }dbSt.close();       
          con.close();    
         } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
    }
    else if(s.equals("����")){

       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
        } catch(ClassNotFoundException e) {
        System.err.println("����̹� �ε忡 �����߽��ϴ�.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB ���� �Ϸ�."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
        String Bborrow = "Update book SET borw ='"+1+"' where bnum='"+t_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("������ ���� �Ϸ�");        
        String strSql ="INSERT INTO Borw (bnum, id, bory, borm, bord, duey, duem, dued) VALUES ("+"'"+t_bnum+"', '"+t_id+"','"+nYear+"', '"+nMonth+"', '"+nDay+"', '"+rYear+"', '"+rMonth+"', '"+rDay+"');";
        dbSt.executeUpdate(strSql);
        System.out.println("������ ���� �Ϸ�");
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }
        
 }

  public void mouseClicked(MouseEvent m){
   int row = table.getSelectedRow();
   String b_num = (String)table.getValueAt(row, 3);
   t_bnum = (String)table.getValueAt(row, 0);
   System.out.println(b_num); 
  }//������ ���� ���� ��ȣ ���
  public void mousePressed (MouseEvent m){}
  public void mouseReleased (MouseEvent m){}
  public void mouseEntered (MouseEvent m){}
  public void mouseExited (MouseEvent m){}
}

class MessageDialog extends JDialog implements ActionListener {  //�޼��� ���̾�α� Ŭ����                                                   
  Button ok;
  MessageDialog(JFrame parent, String title, boolean mode, String msg)
  { super(parent, title, mode);   
    JPanel pc = new JPanel(); 
    JLabel label = new JLabel(msg);
    pc.add(label);
    add(pc, BorderLayout.CENTER); 
   JPanel ps = new JPanel();      
    ok = new Button("OK");
    ok.addActionListener(this);
    ps.add(ok);
    add(ps, BorderLayout.SOUTH);  
    pack();    
  }
 public void actionPerformed(ActionEvent ae) {
    dispose();    
  } 
 } 

class Administrator extends JFrame{

   Administrator(){
    JTabbedPane ad = new JTabbedPane();
    Book b = new  Book();         
    User u = new User();         
    ApplyBook ap = new ApplyBook();         
    BorrowBook bB = new BorrowBook();

    ad.addTab("���� ����", b);
    ad.addTab("ȸ�� ����", u);
    ad.addTab("��û��������", ap);
    ad.addTab("���� ����", bB);

    Container ct = getContentPane();
    ct.add(ad);
  } 
}

class Klib{
   public static void main(String args[]){
      Login win = new Login("Login");
      win.setSize(1100, 600);
      win.setLocation(400, 235);
      win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      win.show();
   }
}