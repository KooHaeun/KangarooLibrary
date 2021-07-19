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
      JRadioButton user = new JRadioButton("사용자");
      user.addActionListener(this);
      JRadioButton manager = new JRadioButton("관리자");
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
              login = new JButton("로그인");
      login.addActionListener(this);
              schId = new JButton("아이디 찾기");
      schId.addActionListener(this);
              schPwd = new JButton("비밀번호 찾기");
      schPwd.addActionListener(this);
             newMem = new JButton("회원가입");
      newMem.addActionListener(this);   

      login.setBounds(750, 200, 100, 100);

      schId.setBounds(330, 340, 120, 30);
      schPwd.setBounds(610, 340, 120, 30);
      newMem.setBounds(490, 420, 90, 30);
      ct.add(login);   ct.add(schId);   ct.add(schPwd);   ct.add(newMem);
   }

   

    public void actionPerformed(ActionEvent ae){
      String s = ae.getActionCommand();
      if(s.equals("사용자")){ isUser = true; }
      else if(s.equals("관리자")){ isUser = false; }
      String t_id = "",t_passwd = "";   

      if(isUser == true){
          if(s.equals("로그인")){
            t_id = id.getText(); 
            t_passwd = passwd.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
         
               strSql="SELECT * FROM mem WHERE mid='"+t_id+"' and passwd='"+t_passwd+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  MessageDialog md = new MessageDialog(this, "로그인", true, "환영합니다!! 캥거루 도서관입니다!!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();

	     Scene2 sc = new Scene2(t_id);
	     sc.setTitle("사용자의 화면");
	     sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     sc.setSize(1600, 800);
	     sc.setVisible(true);
               }
               else{
                  MessageDialog md = new MessageDialog(this, "로그인 오류", true, "아이디 또는 비밀번호가 틀렸습니다.");   
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               
               dbSt.close();       
                  con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
         
         }
         else if(s.equals("회원가입")) {
            NewMemberUser my = new NewMemberUser("회원가입");
            my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            my.setSize(1100, 600);
            my.setLocation(400, 235);
            my.show();
          }
          else if(s.equals("아이디 찾기")) {
            FindIdU fi = new FindIdU("아이디 찾기");
            fi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fi.setSize(1100, 600);
            fi.setLocation(400, 235);
            fi.show();
          }
           else if(s.equals("비밀번호 찾기")) {
            FindPasswdU fp = new FindPasswdU("비밀번호 찾기");
            fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fp.setSize(1100, 600);
            fp.setLocation(400, 235);
            fp.show();
          }
         else{}
      }

      if(isUser == false){
          if(s.equals("로그인")){
            t_id = id.getText(); 
            t_passwd = passwd.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
         
               strSql="SELECT * FROM manager WHERE id='"+t_id+"'and passwd='"+t_passwd+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  MessageDialog md = new MessageDialog(this, "로그인", true, "환영합니다!! 캥거루 도서관입니다!!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                  md.show();

	     Administrator admin= new Administrator();
    	     admin.setTitle("관리자 화면");
    	     admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	     admin.setSize(1600, 800);
    	     admin.setVisible(true);     
               }
               else{
                  MessageDialog md = new MessageDialog(this, "로그인 오류", true, "아이디 또는 비밀번호가 틀렸습니다.");   
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();;
               }
               
               dbSt.close();       
               con.close();
             }catch (SQLException e) {
                         System.out.println("SQLException : "+e.getMessage()); }
         
         }
         else if(s.equals("회원가입")) {

            NewMemberMgr my = new NewMemberMgr("회원가입");
            my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            my.setSize(1100, 600);
            my.setLocation(400, 235);
            my.show();
          }
          else if(s.equals("아이디 찾기")) {
            FindIdM fi = new FindIdM("아이디 찾기");
            fi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fi.setSize(1100, 600);
            fi.setLocation(400, 235);
            fi.show();
          }
           else if(s.equals("비밀번호 찾기")) {
            FindPasswdM fp = new FindPasswdM("비밀번호 찾기");
            fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fp.setSize(1100, 600);
            fp.setLocation(400, 235);
            fp.show();
          }
         else{}
      }
   }
}
       

class NewMemberUser extends JFrame implements ActionListener{                           //사용자 회원가입
   JTextField id, tel_number, address, hintAns; JPasswordField passwd, passwdCh;
   String hintList[] = {"어머니 성함은?", "어릴 때 별명은?", "출신 초등학교는?", "고등학생 때 살았던 곳은?",
          "가장 기억에 남는 선생님은?"};
   JComboBox hint;
     boolean ch1 = true;
      boolean numCh = true;
 NewMemberUser(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("아이디");
   id = new JTextField(8);
   l1.setBounds(195, 80, 80, 30);
   id.setBounds(260, 80, 500, 30);

   JButton idcheck = new JButton("중복확인");
   ct.add(l1); ct.add(id); ct.add(idcheck);
   idcheck.setBounds(800, 80, 100, 30);
   idcheck.addActionListener(this);

   JLabel l2 = new JLabel("비밀번호");
   passwd = new JPasswordField(8);
   ct.add(l2); ct.add(passwd);
   l2.setBounds(190, 160, 80, 30);
   passwd.setBounds(260, 160, 500, 30);

   JLabel l3 = new JLabel("비밀번호 확인");
   passwdCh = new JPasswordField(8);
   ct.add(l3);   ct.add(passwdCh);
   l3.setBounds(170, 240, 100, 30);
   passwdCh.setBounds(260, 240, 500, 30);

   JLabel l4 = new JLabel("비밀번호 힌트");
   hint = new JComboBox(hintList);
   ct.add(l4); ct.add(hint); 
   l4.setBounds(170, 320, 100, 30);
   hint.setBounds(260, 320, 500, 30);

       JLabel l5 = new JLabel("답");
       hintAns = new JTextField (20);
       ct.add(l5); ct.add(hintAns); 
   l5.setBounds(200, 400, 80, 30);
   hintAns.setBounds(260, 400, 500, 30);


      JButton b1 = new JButton("다음");
       ct.add(b1);  
       b1.setBounds(500,480,80,30);
       b1.addActionListener(this);
 }
   
   public void actionPerformed(ActionEvent ae) {  
          String s = ae.getActionCommand();     
          String t_id="", t_passwd="", t_passwdCh="", t_hintAns="";
      int t_hint;

   if(ch1 == true){
      if( s.equals("중복확인")){
         t_id = id.getText();
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
              } catch(ClassNotFoundException e) {
                       System.err.println("드라이버 로드에 실패했습니다.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB 연결 완료."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
            
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
               MessageDialog md = new MessageDialog(this, "ID입력경고", true, "아이디는 숫자로만 입렵해주세요.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(!(t_id.length() >= 4 && t_id.length() <= 10)){
               MessageDialog md = new MessageDialog(this, "ID입력경고", true, "아이디는 4~10자리 숫자로 입력해주세요.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            
            else if(result.next()){
               MessageDialog md = new MessageDialog(this, "ID중복체크", true, "이미 사용중인 아이디입니다.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }

            else{
	   ch1 = false;  
               MessageDialog md = new MessageDialog(this, "ID중복체크", true, "사용가능한 ID입니다!");
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

   if ( s.equals("다음")) { 
          if(ch1 == false){
               t_id = id.getText();    t_passwd = passwd.getText();  
               t_passwdCh = passwdCh.getText();     t_hint = hint.getSelectedIndex();
               t_hintAns = hintAns.getText(); 

         	if(!(t_id.equals("")) && !(t_passwd.equals("")) && !(t_passwdCh.equals("")) && !(t_hintAns.equals(""))){                  
                   try {         
                  	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
                 	 System.out.println("DB 연결 완료."); 
                  	Statement dbSt = con.createStatement();
                  	System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");   
            
                  	String strSql = "INSERT INTO mem (mid, passwd, hint, answer, name, tel, mail, address) VALUES ('"+t_id+"', '"+t_passwd+"', '"+ t_hint+"', '"+t_hintAns+"','"+"','"+1+"','"+"','"+"');";			
                  	dbSt.executeUpdate(strSql);      // sql 질의어 문장 strSql을 실행
                  	System.out.println("데이터 삽입 완료");
		String nextpage ="SELECT * FROM mem WHERE mid='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               	ResultSet result = dbSt.executeQuery(nextpage); 
               	if(result.next()){
                 		NextPage ne = new NextPage("회원가입", t_id);
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
            MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든항목을 작성하십시오.");
            md.setSize(500,120);
            md.setLocation(400,235);
         }
       }
   }

   } 
}

class NextPage extends JFrame implements ActionListener{                              //회원가입 페이지 2
   JTextField name, p, tel_number, email, address;
   String u_id;
 NextPage(String title, String id){
   u_id=id;
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("이름");
   name = new JTextField(8);
   l1.setBounds(200, 80, 80, 30);
   name.setBounds(280, 80, 500, 30);
   ct.add(l1); ct.add(name);

   JLabel l2 = new JLabel("전화번호");
    tel_number = new JTextField(8);
    l2.setBounds(200, 180, 80, 30);
    tel_number.setBounds(280, 180, 500, 30);
   ct.add(l2); ct.add(tel_number);    

   JLabel l3 = new JLabel("E-Mail");
   email = new JTextField(8);
   l3.setBounds(200, 280, 80, 30);
   email.setBounds(280, 280, 500, 30);
   ct.add(l3); ct.add(email);    

   JLabel l4 = new JLabel("주소");
   address = new JTextField(8);
   l4.setBounds(200, 380, 80, 30);
   address.setBounds(280, 380, 500, 30);
   ct.add(l4); ct.add(address);    

      JButton b1 = new JButton("가입");
       b1.addActionListener(this);    
       ct.add(b1);  
   b1.setBounds(500,480,80,30);
  }

   public void actionPerformed(ActionEvent ae){
         String s = ae.getActionCommand();
         String t_name="", t_tel_number="", t_email="", t_address=""; 
            if( s == "가입"){
            t_name = name.getText();    t_tel_number = tel_number.getText();  
            t_email = email.getText();     t_address = address.getText();   

      if(!(t_name.equals("")) && !(t_tel_number.equals("")) && !(t_email.equals("")) && !(t_address.equals(""))){
              try{   
                 Class.forName("com.mysql.jdbc.Driver");  // mysql의 jdbc Driver 연결
                 System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
              } catch(ClassNotFoundException e) {
                   System.err.println("드라이버 로드에 실패했습니다.");
              }  

          try {          
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
             System.out.println("DB 연결 완료."); 
             Statement dbSt = con.createStatement();
             System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
  
               String strSql1 = "UPDATE mem SET name = '"+t_name+"' WHERE mid = '"+u_id+"';"; 
	  String strSql2 = "UPDATE mem SET tel = '"+t_tel_number+"' WHERE mid = '"+u_id+"' ;";
	  String strSql3 = "UPDATE mem SET mail = '"+  t_email+"' WHERE mid = '"+u_id+"' ;";
	  String strSql4 ="UPDATE mem SET address = '"+t_address+"' WHERE mid = '"+u_id+"';";
               dbSt.executeUpdate(strSql1); dbSt.executeUpdate(strSql2); dbSt.executeUpdate(strSql3); dbSt.executeUpdate(strSql4);

               System.out.println("데이터 삽입 완료");
         dbSt.close();       
              con.close();        
             }catch(SQLException e) {
                System.out.println("SQLException: " +e.getMessage());
             }
         MessageDialog md = new MessageDialog(this, "회원가입", true, "회원가입되셨습니다!");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show(); 
         dispose();
          }
      else{
         MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든항목을 작성하십시오.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
       }
   }
} 

class NewMemberMgr extends JFrame implements ActionListener{                           //사용자 회원가입
   JTextField id, name, hintAns; JPasswordField passwd, passwdCh;
   String hintList[] = {"어머니 성함은?", "어릴 때 별명은?", "출신 초등학교는?", "고등학생 때 살았던 곳은?",
          "가장 기억에 남는 선생님은?"};
   JComboBox hint;
      boolean ch = true;
      boolean numCh = true;   

 NewMemberMgr(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("아이디");
   id = new JTextField(8);
   l1.setBounds(195, 80, 80, 30);
   id.setBounds(260, 80, 150, 30);

   JButton idcheck = new JButton("중복확인");
   idcheck.addActionListener(this);  
   ct.add(l1); ct.add(id); ct.add(idcheck);
   idcheck.setBounds(420, 80, 100, 30);

   JLabel l2 = new JLabel("이름");
   name = new JTextField(8);
   ct.add(l2); ct.add(name);
   l2.setBounds(570, 80, 80, 30);
   name.setBounds(610, 80, 150, 30);

   JLabel l3 = new JLabel("비밀번호");
   passwd = new JPasswordField(8);
   ct.add(l3); ct.add(passwd);
   l3.setBounds(190, 160, 80, 30);
   passwd.setBounds(260, 160, 500, 30);

   JLabel l4 = new JLabel("비밀번호 확인");
   passwdCh = new JPasswordField(8);
   ct.add(l4);   ct.add(passwdCh);
   l4.setBounds(170, 240, 100, 30);
   passwdCh.setBounds(260, 240, 500, 30);

   JLabel l5 = new JLabel("비밀번호 힌트");
   hint = new JComboBox(hintList);
   ct.add(l5); ct.add(hint); 
   l5.setBounds(170, 320, 100, 30);
   hint.setBounds(260, 320, 500, 30);

       JLabel l6 = new JLabel("답");
       hintAns = new JTextField(20);
       ct.add(l6); ct.add(hintAns); 
   l6.setBounds(200, 400, 80, 30);
   hintAns.setBounds(260, 400, 500, 30);


      JButton b1 = new JButton("가입");
       ct.add(b1);  
   b1.setBounds(500,480,80,30);
   b1.addActionListener(this);
 }
   
   public void actionPerformed(ActionEvent ae) {  
          String s = ae.getActionCommand();     
          String t_id="", t_name, t_passwd="", t_passwdCh="", t_hintAns="";
      int t_hint;
   if(ch == true){
          if( s.equals("중복확인")){
         t_id = id.getText(); ;
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
              } catch(ClassNotFoundException e) {
                       System.err.println("드라이버 로드에 실패했습니다.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB 연결 완료."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
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
               MessageDialog md = new MessageDialog(this, "ID입력경고", true, "아이디는 숫자로만 입렵해주세요.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(!(t_id.length() >= 4 && t_id.length() <= 10)){
               MessageDialog md = new MessageDialog(this, "ID입력경고", true, "아이디는 4~10자리 숫자로 입력해주세요.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else if(result.next()){
               MessageDialog md = new MessageDialog(this, "ID중복체크", true, "이미 사용중인 아이디입니다.");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
            }
            else{
	   ch = false;
               MessageDialog md = new MessageDialog(this, "ID중복체크", true, "사용가능한 ID입니다!");
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
  
   if ( s.equals("가입")) {
	if(ch == false){
        	   t_id = id.getText();  t_name = name.getText();   t_passwd = passwd.getText();  
               t_passwdCh = passwdCh.getText();  t_hint = hint.getSelectedIndex();
               t_hintAns = hintAns.getText();  

         		if(!(t_id.equals("")) && !(t_name.equals("")) && !(t_passwd.equals("")) && !(t_passwdCh.equals("")) && !(t_hintAns.equals(""))){
         
                  	 try{
                    	 Class.forName("com.mysql.jdbc.Driver");  // mysql의 jdbc Driver 연결
                     	 System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 	  } catch(ClassNotFoundException e) {
                         System.err.println("드라이버 로드에 실패했습니다.");
                 	  }  
              	  try {         
              	       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");   
                   	  System.out.println("DB 연결 완료."); 
                   	  Statement dbSt = con.createStatement();
                   	  System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
                  

                 	 String strSql = "INSERT INTO manager (id, name, passwd, hint, answer) VALUES ("+"'"+t_id+"', '"+t_name+"', '"+t_passwd+"', '"+ t_hint+"', '"+t_hintAns+"');";
                 	 dbSt.executeUpdate(strSql);      // sql 질의어 문장 strSql을 실행
                 	 System.out.println("데이터 삽입 완료");
                 	 dbSt.close();       
                  	con.close();        
              	  }catch(SQLException e) {
                   	System.out.println("SQLException: " +e.getMessage());}
            	MessageDialog md = new MessageDialog(this, "회원가입", true, "회원가입되셨습니다!");
            	md.setSize(500,120);
            	md.setLocation(400,235);
            	md.show();
            	dispose();
         	           }
         		else{
         		MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든항목을 작성하십시오.");
         		md.setSize(500,120);
         		md.setLocation(400,235);
       		md.show();
         		}
         }
   }
   } 
}




class FindIdU extends JFrame implements ActionListener{                              //아이디 찾기 사용자용
   JTextField name, tel_number, email;
 FindIdU(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   
   JLabel l1 = new JLabel("이름");
   name = new JTextField(8);
   ct.add(l1); ct.add(name);
   l1.setBounds(220, 120, 80, 30);
   name.setBounds(300, 120, 530, 30);
   
   JLabel l2 = new JLabel("전화번호");
   tel_number = new JTextField(8);
   ct.add(l2); ct.add(tel_number);
   l2.setBounds(220, 220, 80, 30);
   tel_number.setBounds(300, 220, 530, 30);

   JLabel l3 = new JLabel("E-Mail");
   email = new JTextField(8);
   ct.add(l3); ct.add(email);
   l3.setBounds(220, 320, 80, 30);
   email.setBounds(300, 320, 530, 30);
   
   JButton b = new JButton("찾기");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_name = "", t_tel_number = "";
   String t_email="";
      if( s.equals("찾기")){
         t_name = name.getText(); 
         t_tel_number = tel_number.getText(); 
         t_email = email.getText();
         if(!(t_name.equals("")) && !(t_tel_number.equals("")) && !(t_email.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
               strSql ="SELECT mid FROM mem WHERE name='"+t_name+"' and tel ='"+t_tel_number+"'and mail = '"+t_email+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  String myid = result.getString(1);
                  MessageDialog md = new MessageDialog(this, "아이디 찾기", true, myid);
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "오류", true, "조회된 회원이 없습니다!");
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
         MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든항목을 작성하십시오.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
         }
      } 
 }
}

class FindPasswdU extends JFrame implements ActionListener{                           //비밀번호 찾기 사용자용
   JTextField id, hintAns;   
   String hintList[] = {"어머니 성함은?", "어릴 때 별명은?", "출신 초등학교는?", "고등학생 때 살았던 곳은?",
          "가장 기억에 남는 선생님은?"};
   JComboBox hint;
 FindPasswdU(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("아이디");
   id = new JTextField(8);
   ct.add(l1); ct.add(id);
   l1.setBounds(200, 100, 80, 30);
   id.setBounds(270, 100, 500, 30);
   
   JLabel l2 = new JLabel("비밀번호 힌트");
   hint = new JComboBox(hintList);
   ct.add(l2); ct.add(hint); 
   l2.setBounds(170, 220, 100, 30);
   hint.setBounds(270, 220, 500, 30);

   JLabel l3 = new JLabel("답");
       hintAns = new JTextField (20);
       ct.add(l3); ct.add(hintAns); 
   l3.setBounds(210, 340, 80, 30);
   hintAns.setBounds(270, 340, 500, 30);
   
   JButton b = new JButton("찾기");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_id = "", t_hintAns = "";
   int t_hint;
   if( s.equals("찾기")){
      t_id = id.getText(); 
      t_hint = hint.getSelectedIndex();
      t_hintAns = hintAns.getText();
      if(!(t_id.equals("")) && !(t_hintAns.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
               strSql ="SELECT * FROM mem WHERE mid='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  FindPwdUser fp = new FindPwdUser("비밀번호 변경", t_id);
                         fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                         fp.setSize(1100, 600);
                         fp.setLocation(400, 235);
                  fp.show();
                  dispose();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "오류", true, "조회된 회원이 없습니다!");
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
         MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든항목을 작성하십시오.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
   } 
 }
}

class FindPwdUser extends JFrame implements ActionListener{                           //사용자 비번 찾기
   JTextField newPwd, newPwdCh;
   String u_id;
   FindPwdUser(String title, String id){ 
   u_id = id;
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("새 비밀번호");
   newPwd = new JPasswordField(8);
   ct.add(l1); ct.add(newPwd);
   l1.setBounds(240, 150, 80, 30);
   newPwd.setBounds(340, 150, 400, 30);

   JLabel l2 = new JLabel("비밀번호 확인");
   newPwdCh = new JPasswordField(8);
   ct.add(l2); ct.add(newPwdCh);
   l2.setBounds(240, 290, 100, 30);
   newPwdCh.setBounds(340, 290, 400, 30);

   JButton b = new JButton("변경 완료");
   ct.add(b);
   b.setBounds(500,420,100,30);
   b.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent ae) {  
      String s = ae.getActionCommand();
      String t_newPwd="", t_newPwdCh="";
      if(s.equals("변경 완료")){
         t_newPwd = newPwd.getText(); 
         t_newPwdCh = newPwdCh.getText();
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
              } catch(ClassNotFoundException e) {
                       System.err.println("드라이버 로드에 실패했습니다.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB 연결 완료."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
            String strSql;
         
            if(t_newPwd.equals(t_newPwdCh)){
               strSql="UPDATE mem SET passwd = '"+t_newPwd+"' WHERE mid = '"+u_id+"';";
               dbSt.executeUpdate(strSql);
               MessageDialog md = new MessageDialog(this, "비밀번호 변경", true, "변경되었습니다!");
               md.setSize(500,120);
               md.setLocation(400,235);
                   md.show();
               System.out.println("데이터 수정 완료");
               dispose();
            }
            else{
               MessageDialog md = new MessageDialog(this, "비밀번호 변경", true, "비밀번호가 일치하지 않습니다.");
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


class FindIdM extends JFrame implements ActionListener{                              //관리자용 아이디 찾기
   JTextField mname;;
 FindIdM(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("이름");
   mname = new JTextField(8);
   ct.add(l1); ct.add(mname);
   l1.setBounds(220, 250, 80, 30);
   mname.setBounds(300, 250, 530, 30);

   JButton b = new JButton("찾기");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   String t_name = "";
   if( s.equals("찾기")){
      t_name = mname.getText();
      if(!(t_name.equals(""))){
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                     Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
               strSql ="SELECT id FROM manager WHERE name='"+t_name+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  String myid = result.getString(1);
                  MessageDialog md = new MessageDialog(this, "아이디 찾기", true, myid);
                  md.setSize(500,120);
                  md.setLocation(400,235);
                  md.show();
               }
               else{
                  MessageDialog md = new MessageDialog(this, "오류", true, "조회된 회원이 없습니다!");
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
         MessageDialog md = new MessageDialog(this, "입력 오류", true, "항목을 입력하십시오.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
   } 
 }
}

class FindPasswdM extends JFrame implements ActionListener{                              //관리자용 비번찾기
   JTextField id, hintAns;   
   String hintList[] = {"어머니 성함은?", "어릴 때 별명은?", "출신 초등학교는?", "고등학생 때 살았던 곳은?",
          "가장 기억에 남는 선생님은?"};
   JComboBox hint;
 FindPasswdM(String title){
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);
   JLabel l1 = new JLabel("아이디");
   id = new JTextField(8);
   ct.add(l1); ct.add(id);
   l1.setBounds(200, 100, 80, 30);
   id.setBounds(270, 100, 500, 30);
   
   JLabel l2 = new JLabel("비밀번호 힌트");
   hint = new JComboBox(hintList);
   ct.add(l2); ct.add(hint); 
   l2.setBounds(170, 220, 100, 30);
   hint.setBounds(270, 220, 500, 30);

   JLabel l3 = new JLabel("답");
       hintAns = new JTextField (20);
       ct.add(l3); ct.add(hintAns); 
   l3.setBounds(210, 340, 80, 30);
   hintAns.setBounds(270, 340, 500, 30);
   
   JButton b = new JButton("찾기");
   ct.add(b);  
   b.setBounds(500,420,80,30);
   b.addActionListener(this);

   
 }
 public void actionPerformed(ActionEvent ae){ 
   String s = ae.getActionCommand();
   if( s.equals("찾기")){
   String t_id = "", t_hintAns = "";
   int t_hint;
   if( s.equals("찾기")){
      t_id = id.getText(); 
      t_hint = hint.getSelectedIndex();
      t_hintAns = hintAns.getText();

      if(!(t_id.equals("") && t_hintAns.equals(""))){
         try {
                   Class.forName("com.mysql.jdbc.Driver");  
                   System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
              } catch(ClassNotFoundException e) {
                       System.err.println("드라이버 로드에 실패했습니다.");}
              try {              
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                  System.out.println("DB 연결 완료."); 
                   Statement dbSt = con.createStatement();
                  System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
            String strSql;
               strSql ="SELECT * FROM manager WHERE id='"+t_id+"' and hint ='"+t_hint+"' and answer = '"+t_hintAns+"';";
               ResultSet result = dbSt.executeQuery(strSql);
               if(result.next()){
                  FindPwdUser fp = new FindPwdUser("비밀번호 변경", t_id);
                         fp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                         fp.setSize(1100, 600);
                         fp.setLocation(400, 235);
                  fp.show();
                  dispose();
               }
            else{
               MessageDialog md = new MessageDialog(this, "오류", true, "조회된 관리자가 없습니다!");
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
         MessageDialog md = new MessageDialog(this, "입력 오류", true, "모든 항목을 입력하십시오.");
         md.setSize(500,120);
         md.setLocation(400,235);
         md.show();
      }
      
   } 
   } 
 }
}
class FindPwdMgr extends JFrame implements ActionListener{                     //관리자용 비번 변경 화면2
   JTextField newPwd, newPwdCh;
   String m_id;
   FindPwdMgr(String title, String id){
   m_id = id;
   
   setTitle(title);
   Container ct = getContentPane();
   ct.setLayout(null);

   JLabel l1 = new JLabel("새 비밀번호");
   newPwd = new JPasswordField(8);
   ct.add(l1); ct.add(newPwd);
   l1.setBounds(240, 150, 80, 30);
   newPwd.setBounds(340, 150, 400, 30);

   JLabel l2 = new JLabel("비밀번호 확인");
   newPwdCh = new JPasswordField(8);
   ct.add(l2); ct.add(newPwdCh);
   l2.setBounds(240, 290, 100, 30);
   newPwdCh.setBounds(340, 290, 400, 30);

   JButton b1 = new JButton("변경 완료");
   ct.add(b1);
   b1.setBounds(500,420,100,30);
   b1.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent ae) {  
      String s = ae.getActionCommand();
      String t_newPwd="", t_newPwdCh="";
      if(s.equals("변경 완료")){
         t_newPwd = newPwd.getText(); 
         t_newPwdCh = newPwdCh.getText();
            try {
                      Class.forName("com.mysql.jdbc.Driver");  
                      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
                 } catch(ClassNotFoundException e) {
                          System.err.println("드라이버 로드에 실패했습니다.");}
                 try {              
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
                     System.out.println("DB 연결 완료."); 
                      Statement dbSt = con.createStatement();
                     System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
         
               String strSql;
         
               if(t_newPwd.equals(t_newPwdCh)){
                  strSql="UPDATE manager SET passwd = '"+t_newPwd+"' WHERE id = '"+m_id+"';";
                  dbSt.executeUpdate(strSql);
                  MessageDialog md = new MessageDialog(this, "비밀번호 변경", true, "변경되었습니다!");
                  md.setSize(500,120);
                  md.setLocation(400,235);
                      md.show();
                  System.out.println("데이터 수정 완료");
               }
               else{
                  MessageDialog md = new MessageDialog(this, "비밀번호 변경", true, "비밀번호가 일치하지 않습니다.");
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

	String[] Data2 = {"제목", "작가", "출판사"};

	String[] bigS = {"총류", "철학", "종교", "사회과학", "순수과학", "기술과학", "예술", "어학", "문학", "역사"};
	String[][] detailS = {{"총류 소프트웨어", "도서학, 서지학", "문헌정보학", "백과사전", "강연집, 수필집", "일반 연속 간행물", "일반학회", "신문, 언론", "일반 전집", "향토자료"}, 
			 {"철학일반", "형이상학", "철학체계", "경학", "서양철학", "논리학", "심리학", "윤리학, 도덕철학"}, // 2번과 5번은 null
			 {"종교일반", "비교종교", "불교", "기독교", "도교", "천도교", "신도", "바라문교", "이슬람교", "기타 제종교"}, 
			 {"사회과학일반", "통계학", "경제학", "사회학", "정치학", "행정학", "법학", "교육학", "풍속민속학", "국방군사학"}, 
			 {"순수과학일반", "수학", "물리학", "화학", "천문학", "지학", "광물학", "생명과학", "식물학", "동물학"}, 
			 {"기술과학일반", "의학, 약학", "농업, 수의학", "공학, 공업일반", "기계공학", "전기공학", "화학공학", "제조업, 인쇄술", "가정학 및 가정생화"}, 
			 {"예술일반", "건축술", "조각", "공예 장식미술", "서예", "회화, 도화", "사진술", "음악", "연극, 영화", "오락, 운동"}, 
			 {"어학일반", "한국어", "중국어", "일본어", "영어", "독일어", "프랑스어", "스페인어", "이탈리아어", "기타제어"}, 
			 {"문학일반", "한국문학", "중국문학", "일본문학", "영미문학", "독일문학", "프랑스문학", "스페인문학", "이탈리아문학", "기타제문학"}, 
			 {"역사일반", "아시아", "유럽", "아프리카", "북아메리카", "남아메리카", "오세아니아", "남극지방", "지리관광", "전기족보"}};

	JScrollPane tableSP;

	int i = 1;
	String t_bnum= "", t_title="", t_author="", t_publisher="", t_bsort="", t_ssort="", t_cond="", t_borw="";

	int ide, idx;
	boolean t;

	public Search() {						//검색화면 생성자
		this.setLayout(null);
		big = new JComboBox(bigS);
		detail = new JComboBox(detailS[0]);
		title = new JComboBox(Data2);
		big.addItemListener(this);		

		sf = new JTextField(10);
		sb = new JButton("검색");
		sb.addActionListener(this);

		big.setBounds(300, 10, 100, 30);
		detail.setBounds(410, 10, 100, 30);
		title.setBounds(525, 10, 100, 30);
		sf.setBounds(635, 10, 500, 30);
		sf.setFont(new Font("굴림", Font.PLAIN, 15));
		sb.setBounds(1225, 10, 100, 30);

		((JLabel)big.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)detail.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)title.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

		add(big); add(detail); 
		add(title); add(sf); add(sb);

		column = new Vector<String>();
		column.add("인덱스"); column.add("제목"); column.add("책 번호");
		column.add("작가"); column.add("출판사"); column.add("대출 현황");

		row = new Vector<Vector<String>>();
		model = new DefaultTableModel(row, column);

		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("굴림", Font.PLAIN, 20));

		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(233, 45));
		header.setFont(new Font("굴림", Font.PLAIN, 20));

		tableSP = new JScrollPane(table);
		tableSP.setBounds(100, 50, 1400, 750);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);

		add(tableSP);
	}

	public void actionPerformed(ActionEvent ae) { //검색 버튼을 눌렀을 때 생기는 이벤트를 처리해주는 메소드
		int index = title.getSelectedIndex();
		ide = detail.getSelectedIndex();
		t_bsort = bigS[idx];
		t_ssort = detailS[idx][ide];

		String s = sf.getText();

		if(!(s.equals(""))) {
			if(Data2[index] == "제목") {
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText("");
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
				} catch(ClassNotFoundException e) {
					System.out.println("드라이버 로드에 실패했습니다.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB 연결 완료");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
								txt.add("대출가능");
							else if(t_borw.equals("1"))
								txt.add("대출중");
							else if(t_borw.equals("2"))
								txt.add("연체중");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
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
			else if (Data2[index] == "작가") {
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText("");
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
				} catch(ClassNotFoundException e) {
					System.out.println("드라이버 로드에 실패했습니다.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB 연결 완료");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
								txt.add("대출가능");
							else if(t_borw.equals("1"))
								txt.add("대출중");
							else if(t_borw.equals("2"))
								txt.add("연체중");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
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
			else if(Data2[index] == "출판사"){
				int rowr = table.getRowCount();
				for (int j = 0; j < rowr; j++) {
					model.removeRow(j);
				}
				sf.setText(""); 
				i = 1;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
				} catch(ClassNotFoundException e) {
					System.out.println("드라이버 로드에 실패했습니다.");
				}

				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
					System.out.println("DB 연결 완료");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
								txt.add("대출가능");
							else if(t_borw.equals("1"))
								txt.add("대출중");
							else if(t_borw.equals("2"))
								txt.add("연체중");
							else;

							row.add(txt);
							table.updateUI();
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
							md.setSize(500, 200);
							md.setLocation(100, 200);
							md.show();
						}
						}
						else{
							Message md = new Message("도서 검색", "검색하신 도서가 존재하지 않습니다.");
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
				Message md = new Message("입력오류", Data2[index]+"을(를) 입력해주세요");
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

	public Borrow(String id) {						//대출화면 생성자
		this.setLayout(null);

		column = new Vector<String>();
		column.add("인덱스"); column.add("제목"); column.add("책 번호");
		column.add("출판사"); column.add("대출 일"); column.add("반납 예정일");
		column.add("알림");

		model = new DefaultTableModel(row, column);
		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("굴림", Font.PLAIN, 20));

		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(233, 45));
		header.setFont(new Font("굴림", Font.PLAIN, 20));

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
			System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드에 실패했습니다.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB 연결 완료");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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

		String str = t_name + "님의 대출 현황";
		jl = new JLabel(str);
		jl.setBounds(650, 10, 400, 100);
		jl.setFont(new Font("굴림", Font.PLAIN, 30));
		jl.setHorizontalAlignment(JLabel.CENTER);

		add(jl);
	}
}

class Apply extends JPanel  implements ActionListener{
	JTextField tf, pf, wf, f1, f2, f3;
	JLabel jl1, jl2, jl3, jl4;
	JButton ab;
	String mid;

	public Apply(String id) {						//신청화면 생성자
		this.setLayout(null);

		mid = id;

		jl1 = new JLabel("신청 도서 제목");
		tf = new JTextField(10);

		jl2 = new JLabel("출판사");
		pf = new JTextField(10);

		jl3 = new JLabel("작가");
		wf = new JTextField(10);

		jl4 = new JLabel("신청일(년-월-일)");
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

		jl1.setFont(new Font("굴림", Font.PLAIN, 20));
		jl2.setFont(new Font("굴림", Font.PLAIN, 20));
		jl3.setFont(new Font("굴림", Font.PLAIN, 20));
		jl4.setFont(new Font("굴림", Font.PLAIN, 20));
		tf.setFont(new Font("굴림", Font.PLAIN, 20));
		pf.setFont(new Font("굴림", Font.PLAIN, 20));
		wf.setFont(new Font("굴림", Font.PLAIN, 20));
		f1.setFont(new Font("굴림", Font.PLAIN, 20));
		f2.setFont(new Font("굴림", Font.PLAIN, 20));
		f3.setFont(new Font("굴림", Font.PLAIN, 20));

		add(jl1); add(tf); add(jl2); add(pf); add(jl3); add(wf);
		add(jl4); add(f1); add(f2); add(f3);

		ab = new JButton("신청");
		ab.addActionListener(this);

		ab.setBounds(700, 300, 200, 30);

		add(ab);
	}

	public void actionPerformed(ActionEvent ae) { //신청버튼을 눌렀을 때 생기는 이벤트 처리 해주는 메소드
		JLabel jl = new JLabel();

		jl.setBounds(600, 400, 1000, 30);
		jl.setFont(new Font("굴림", Font.PLAIN, 30));

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
				System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
			} catch(ClassNotFoundException e) {
				System.out.println("드라이버 로드에 실패했습니다.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB 연결 완료");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
				jl.setText(t_bsort + "에 존재하는 도서 입니다.");
			else{
				t_onum = s4 + "-" + s5 + "-" + s6;
				t_oid = t_mid;
				t_ostate = "0";
				t_otitle = s1;
				t_opublish = s2;
				t_owriter = s3;;

				try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
				} catch(ClassNotFoundException e) {
					System.out.println("드라이버 로드에 실패했습니다.");
				}
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
						System.out.println("DB 연결 완료");
					Statement dbSt = con.createStatement();
					System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
					t_title = tf.getText();
					String strSql = "INSERT INTO bkorder (oday, otitle, opublish, owriter, oid, ostate) VALUES ("+"'"+t_onum+"','"+t_otitle+"','"+t_opublish+"','"+t_owriter+"','"+t_oid+"','"+t_ostate+"');";
					dbSt.executeUpdate(strSql);
					dbSt.close();
					con.close();
				} catch(SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
				jl.setText("도서 신청이 완료되었습니다.");
			}
		}
		else {
				Message md = new Message("입력오류", "모든 칸이 채워지지 않았습니다.");
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

	public Personal(String id) {						//개인정보화면 생성자
		this.setLayout(null);
		mid = id;
		String t_mid=id;
		String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드에 실패했습니다.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB 연결 완료");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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

		jl1 = new JLabel("이름:" + t_name);
		jl2 = new JLabel("아이디:" + t_mid);
		jl3 = new JLabel("전화번호:" + t_tel);
		jl4 = new JLabel("E-Mail:" + t_mail);
		jl5 = new JLabel("주소:" + t_address);

		jl1.setBounds(10, 10, 300, 100);
		jl2.setBounds(10, 120, 300, 100);
		jl3.setBounds(10, 230, 300, 100);
		jl4.setBounds(10, 340, 300, 100);
		jl5.setBounds(10, 450, 500, 100);

		jl1.setFont(new Font("굴림", Font.PLAIN, 20));
		jl2.setFont(new Font("굴림", Font.PLAIN, 20));
		jl3.setFont(new Font("굴림", Font.PLAIN, 20));
		jl4.setFont(new Font("굴림", Font.PLAIN, 20));
		jl5.setFont(new Font("굴림", Font.PLAIN, 20));

		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);

		psb = new JButton("비밀번호 변경");
		psb.addActionListener(this);
		infb = new JButton("개인정보 변경");
		infb.addActionListener(this);

		psb.setBounds(500, 600, 150, 30);
		infb.setBounds(1000, 600, 150, 30);

		add(psb); add(infb);
	}

	public void actionPerformed(ActionEvent ae) { // 비밀번호 변경이나 개인정보 변경 버튼을 눌렀을 때 생기는 이벤트 처리 해주는 메소드
		String s = ae.getActionCommand();

		if (s == "비밀번호 변경") {
			NewPass np = new NewPass(mid);
			np.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			np.setSize(800, 500);
			np.setLocation(400, 300);
			np.setVisible(true);
		}
		else if (s == "개인정보 변경") {
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

	public NewPass(String mid) {				//비밀번호 변경화면 생성자
		this.setTitle("비밀번호 변경화면");
		id = mid;

		Container ct = getContentPane();
		ct.setLayout(null);		

		jl1 = new JLabel("새 비밀번호");
		jl2 = new JLabel("비밀번호 확인");

		tf1 = new JPasswordField(20);
		tf2 = new JPasswordField(20);

		btn = new JButton("비밀번호 변경");
		btn.addActionListener(this);

		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl2.setHorizontalAlignment(JLabel.CENTER);

		jl1.setBounds(100, 130, 200, 30);
		jl2.setBounds(100, 180, 200, 30);
		jl1.setFont(new Font("굴림", Font.PLAIN, 15));
		jl2.setFont(new Font("굴림", Font.PLAIN, 15));

		tf1.setBounds(320, 130, 300, 30);
		tf2.setBounds(320, 180, 300, 30);
		tf1.setFont(new Font("굴림", Font.PLAIN, 15));
		tf2.setFont(new Font("굴림", Font.PLAIN, 15));

		btn.setBounds(300, 300, 200, 30);

		ct.add(jl1); ct.add(jl2);
		ct.add(tf1); ct.add(tf2);;
		ct.add(btn);
	}

	public void actionPerformed(ActionEvent ae) { // 비밀번호 변경버튼을 눌렀을 때 생기는 이벤트를 처리해주는 메소드
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
				Message md = new Message("입력오류", "비밀번호가 일치하지 않습니다.");
				md.setSize(500, 200);
				md.setLocation(100, 200);
				md.show();
				tf1.setText(""); tf2.setText("");
			}
		}
		else {
			Message md = new Message("입력오류", "변경할 비밀번호를 입력해주세요.");
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

	public ChangeInfo(String id) {					//개인정보 변경화면 생성자
		this.setTitle("개인정보 변경화면");
		id1 = id;

		Container ct = getContentPane();
		ct.setLayout(null);

		String t_mid=id;
		String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드에 실패했습니다.");
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
			System.out.println("DB 연결 완료");
			Statement dbSt = con.createStatement();
			System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
		jl2 = new JLabel("주소");

		j1 = new JLabel("이름");
		j2 = new JLabel("아이디");
		j3 = new JLabel("전화번호");

		l1 = new JLabel(t_name);
		l2 = new JLabel(t_mid);
		l3 = new JLabel(t_tel);

		tf1 = new JTextField(10);
		tf2 = new JTextField(10);

		l1.setHorizontalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.CENTER);

		btn = new JButton("변경");
		btn.addActionListener(this);

		jl1.setFont(new Font("굴림", Font.PLAIN, 15));
		jl2.setFont(new Font("굴림", Font.PLAIN, 15));

		j1.setFont(new Font("굴림", Font.PLAIN, 15));
		j2.setFont(new Font("굴림", Font.PLAIN, 15));
		j3.setFont(new Font("굴림", Font.PLAIN, 15));

		l1.setFont(new Font("굴림", Font.PLAIN, 15));
		l2.setFont(new Font("굴림", Font.PLAIN, 15));
		l3.setFont(new Font("굴림", Font.PLAIN, 15));

		tf1.setFont(new Font("굴림", Font.PLAIN, 15));
		tf2.setFont(new Font("굴림", Font.PLAIN, 15));

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

	public void actionPerformed(ActionEvent ae) { // 변경 버튼을 눌렀을 때 생기는 이벤트를 처리해주는 메소드
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
			Message md = new Message("입력오류", "변경된 정보를 입력해주세요");
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
		this.setTitle("변경확인");
		id = mid;

		str1 = s1;
		n = np;

		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(10, 0));

		JPanel center = new JPanel();
		JPanel bottom = new JPanel();

		ct.add(center, BorderLayout.CENTER);
		ct.add(bottom, BorderLayout.SOUTH);

		JLabel j = new JLabel("변경하시겠습니까?");
		j.setFont(new Font("굴림", Font.PLAIN, 20));
		j.setHorizontalAlignment(JLabel.CENTER);
		center.add(j);

		JButton btn1 = new JButton("확인");
		JButton btn2 = new JButton("취소");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		bottom.add(btn1); bottom.add(btn2);
	}

	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();

		if(s == "취소") 
			dispose();
		else {
			String t_mid=id;
			String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
			} catch(ClassNotFoundException e) {
				System.out.println("드라이버 로드에 실패했습니다.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB 연결 완료");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
				
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
		this.setTitle("변경확인");
		id = mid;

		str1 = s1; str2 = s2;
		c = ci;

		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(10, 0));

		JPanel center = new JPanel();
		JPanel bottom = new JPanel();

		ct.add(center, BorderLayout.CENTER);
		ct.add(bottom, BorderLayout.SOUTH);

		JLabel j = new JLabel("변경하시겠습니까?");
		j.setFont(new Font("굴림", Font.PLAIN, 20));
		j.setHorizontalAlignment(JLabel.CENTER);
		center.add(j);

		JButton btn1 = new JButton("확인");
		JButton btn2 = new JButton("취소");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		bottom.add(btn1); bottom.add(btn2);
	}

	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();

		if(s == "취소") 
			dispose();
		else {
			String t_mid=id;
			String t_name="", t_passwd="", t_hint="", t_answer="", t_tel="", t_mail="", t_address="";

			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
			} catch(ClassNotFoundException e) {
				System.out.println("드라이버 로드에 실패했습니다.");
			}

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
				System.out.println("DB 연결 완료");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
				
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
		label.setFont(new Font("굴림", Font.PLAIN, 15));
		add(pc, BorderLayout.CENTER);
		JPanel ps = new JPanel();
		btn = new Button("확인");
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
		JTabbedPane jtp = new JTabbedPane();	//JTabbedPane 객체 생성
		Search se = new Search();			//검색화면
		Borrow bo = new Borrow(mid);			//대출화면
		Apply ap = new Apply(mid);			//신청화면
		Personal pe = new Personal(mid);		//개인정보화면

		jtp.addTab("도서 검색", se);
		jtp.addTab("도서 대출 현황", bo);
		jtp.addTab("도서 신청", ap);
		jtp.addTab("개인정보", pe);

		Container ct = getContentPane();
		ct.add(jtp);
	}
}
class Book extends JPanel implements ActionListener{ //도서관리 클래스 
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
  String boSta[]={"전체", "대출", "연체", "대출가능"};
  String bSta[] ={"전체", "양호", "불량"};
  String b_bnum, t_Bbnum;
  public Book(){
    this.setLayout(new BorderLayout());
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER); 

    bookIF = new Vector<String>();
    bookIF.add("도서번호");  bookIF.add("제목");
    bookIF.add("출판사");  bookIF.add("작가");
    bookIF.add("대출여부");  bookIF.add("상태");
    bookIF.add("반납예정일");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, bookIF);
    table = new JTable(model);   
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.setRowHeight(30);

      //처음 화면 떴을 때 모든 도서 볼 수 있게
    try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325"); 
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        model.setRowCount(0);  
        String info_Int;
        info_Int = "SELECT bnum, title, author, publisher, cond, borw FROM book;";
        ResultSet rs=dbSt.executeQuery(info_Int);     // DB로부터 읽어온 레코드를 객체화    
        while(rs.next()){  
          String co = rs.getString(5);
          String bo =rs.getString(6);
          if(co.equals("0"))
            co="양호";
          else if(co.equals("1"))
            co="불량";

          if(bo.equals("0"))
           bo="대출가능";
          else if(bo.equals("1"))
            bo="대출";      
           else if(bo.equals("2"))
            bo="연체";         
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), bo, co});
         }
       dbSt.close();
       con.close();
       } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }

      //대출된 책일때는 반납 예정일이 뜨게
      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt1 = con1.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
                 model.setValueAt("연체", i, 4);
                 }
             else if(nMonth>month)
               model.setValueAt("연체", i, 4); }
             else if(nYear>year)
               model.setValueAt("연체", i, 4); 
            }else
            ; }       }
        dbSt1.close();
        con1.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }
     for(int i =0; i<table.getRowCount(); i++){
       if(((String)model.getValueAt(i,4))=="연체")
         b_bnum=(String)model.getValueAt(i,0);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String Bborrow = "Update book SET borw ='"+2+"' WHERE bnum='"+b_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("데이터 수정 완료");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }//연체인 경우 대출 상태를 2로 바꾸기
     
    boState = new JComboBox(boSta);
    BState = new JComboBox(bSta); 
    boState.addActionListener(this);
    BState.addActionListener(this);
 
    Bplus = new JButton("추가");
    Bplus.addActionListener(this);
    search = new JButton("조회");
    search.addActionListener(this);
    top.setLayout(new FlowLayout());
    JLabel label = new JLabel("도서 목록");

    top.add(boState,FlowLayout.LEFT);  top.add(BState);  top.add(label);  top.add(Bplus); top.add(search);
  } 

  public void actionPerformed(ActionEvent ae){ 
    if(ae.getActionCommand().equals("조회")){ 
      String brw = boState.getSelectedItem().toString();
      String bk = BState.getSelectedItem().toString(); 
       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325"); 
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");  
        model.setRowCount(0);
     
       String info_Int;
       info_Int =  "SELECT bnum, title, author, publisher, cond, borw FROM book;";
       ResultSet rs=dbSt.executeQuery(info_Int); 
       while(rs.next()){         
          int i=0; 
          String c = rs.getString(5);
          String b = rs.getString(6);
         
          if(c.equals("0"))
            c="양호";
          else if(c.equals("1"))
            c="불량";

          if(b.equals("0"))
           b="대출가능";
          else if(b.equals("1"))
            b="대출";
          else if(b.equals("2"))
            b="연체";
           if(brw==b&&bk==c){
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});
            System.out.println(b); System.out.println(c);}
         else if(brw.equals("전체")||bk.equals("전체"))
            if(brw==b || bk==c)
              model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});
          else if(brw.equals("전체")&&bk.equals("전체"))
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), b, c});        
         } //콤보박스 값과 같은 대출상태와 상태를 가진 책 검색
       dbSt.close();
       con.close();
       } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }     
      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt1 = con1.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
                 model.setValueAt("연체", i, 4);}
             else if(nMonth>month)
               model.setValueAt("연체", i, 4); }
             else if(nYear>year)
               model.setValueAt("연체", i, 4); 
            }else
            ; }       }
        dbSt1.close();
        con1.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }
        for(int i =0; i<table.getRowCount(); i++){
       if(((String)model.getValueAt(i,4))=="연체")
         b_bnum=(String)model.getValueAt(i,0);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String Bborrow = "Update book SET borw ='"+2+"' WHERE bnum='"+b_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("데이터 수정 완료");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); } }//연체인 경우 대출 상태를 2로 바꾸기
       }
         
  else if(ae.getActionCommand().equals("추가")){ //추가 버튼 누르면 도서 추가 창
      NewBook plus = new NewBook();
      plus.setSize(350, 450);
      plus.setLocation(400, 300);
      plus.show(); }
  }

}

class NewBook extends JFrame implements ActionListener, ItemListener {//도서 추가하는 클래스
  JTextField num, title, author, publisher, bsort, ssort;
  String[] big = {"총류", "철학", "종교", "사회과학", "순수과학", "기술과학", "예술", "어학", "문학", "역사"};
  String[][] small = {{"총류 소프트웨어", "도서학, 서지학", "문헌정보학", "백과사전", "강연집, 수필집", "일반 연속 간행물", "일반학회", "신문, 언론", "일반 전집", "향토자료"}, 
          {"철학일반", "형이상학", "철학체계", "경학",  "서양철학", "논리학", "심리학", "윤리학, 도덕철학"}, 
          {"종교일반", "비교종교", "불교", "기독교", "도교", "천도교", "신도", "바라문교", "이슬람교", "기타 제종교"}, 
          {"사회과학일반", "통계학", "경제학", "사회학", "정치학", "행정학", "법학", "교육학", "풍속민속학", "국방군사학"}, 
          {"순수과학일반", "수학", "물리학", "화학", "천문학", "지학", "광물학", "생명과학", "식물학", "동물학"}, 
          {"기술과학일반", "의학, 약학", "농업, 수의학", "공학, 공업일반", "기계공학", "전기공학", "화학공학", "제조업, 인쇄술", "가정학 및 가정생화"}, 
          {"예술일반", "건축술", "조각", "공예 장식미술", "서예", "회화, 도화", "사진술", "음악", "연극, 영화", "오락, 운동"}, 
          {"어학일반", "한국어", "중국어", "일본어", "영어", "독일어", "프랑스어", "스페인어", "이탈리아어", "기타제어"}, 
          {"문학일반", "한국문학", "중국문학", "일본문학", "영미문학", "독일문학", "프랑스문학", "스페인문학", "이탈리아문학", "기타제문학"}, 
          {"역사일반", "아시아", "유럽", "아프리카", "북아메리카", "남아메리카", "오세아니아", "남극지방", "지리관광", "전기족보"}};
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
    JLabel lb = new JLabel("대분류: ");
    JPanel p1 = new JPanel();
    Bsort = new JComboBox(big);
    JLabel ls = new JLabel("세분류: ");
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    Ssort = new JComboBox(small[0]);
    Bsort.addItemListener(this);
    Ssort.addActionListener(this);    
    p0.add(lb);  p1.add(ls);  p0.add(Bsort); p1.add(Ssort); 
   
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l1 = new JLabel("책번호: ");
    num = new JTextField (8);
    p2.add(l1);  p2.add(num); 

    JPanel p3 = new JPanel();
    p3.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l2 = new JLabel("제목 : ");
    title = new JTextField (8);
    p3.add(l2); p3.add(title);  

    JPanel p4 = new JPanel();
    p4.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l3 = new JLabel("작가 : ");
    author = new JTextField (8);  
    p4.add(l3); p4.add(author);

    JPanel p5 = new JPanel();
    p5.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel l4 = new JLabel("출판사: ");
    publisher = new JTextField (10);
    p5.add(l4); p5.add(publisher); 

    top.add(p0);  top.add(p1);  top.add(p2);  top.add(p3);  top.add(p4);  top.add(p5);


    JButton b1 = new JButton("확인");
    JButton b2 = new JButton("취소");
    b1.addActionListener(this);
    b2.addActionListener(this);
    bottom.add(b1);  bottom.add(b2);
   
  }
public void actionPerformed(ActionEvent ae){
  String s = ae.getActionCommand();
  String t_num="", t_title="", t_author="", t_publisher="", t_bsort="", t_ssort="";
  MessageDialog md, md1;
  ide = Ssort.getSelectedIndex();
  if ( s=="취소") {//취소 누르면 초기화
    num.setText("");  title.setText("");  author.setText("");
    publisher.setText(""); 
    Bsort.setSelectedItem((Object)big[0]);  Ssort.setSelectedItem((Object)small); }
  else if(s=="확인"){      
     t_num=num.getText();  t_title=title.getText();  
      t_author=author.getText();  t_publisher=publisher.getText(); 
      t_bsort=(String)Bsort.getSelectedItem();  t_ssort=(String)Ssort.getSelectedItem();
    if(t_num.equals("")||t_title.equals("")||t_author.equals("")||t_publisher.equals("")){//도서 추가시 하나라도 비면 경고 뜨게
     md = new MessageDialog(this, "도서 추가", true,"모든 정보를 입력해주세요"); 
     md.show(); }
    else{
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
      } catch(ClassNotFoundException e) {
      System.err.println("드라이버 로드에 실패했습니다.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB 연결 완료."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
      String strSql ="INSERT INTO book (bnum, title, author, publisher, bsort, ssort) VALUES ("+"'"+t_num+"', '"+t_title+"', '"+  t_author+"', '"+ t_publisher+"', '"+t_bsort+"','"+t_ssort+"');";
      dbSt.executeUpdate(strSql);
      System.out.println("데이터 삽입 완료");
      md1 = new MessageDialog(this, "도서 추가", true,"도서가 추가되었습니다."); 
      md1.show(); 
      dbSt.close();       
      con.close();
     } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
      dispose();//확인누르고 도서 추가되면 도서 추가 창 닫힘
      }
     } 
    }
  public void itemStateChanged(ItemEvent ie){//대분류에 따라 세분류 바로 바로 바뀜
      idx = Bsort.getSelectedIndex();
      DefaultComboBoxModel model = new DefaultComboBoxModel(small[idx]);
      Ssort.setModel(model);    
   }
}
class User extends JPanel implements ActionListener, MouseListener{// 회원 관리 클래스
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
    top. add(new JLabel("회원 정보"));
    research = new JButton("재조회");
    top.add(research);
    research.addActionListener(this);
    useIF = new Vector<String>();
    useIF.add("ID");  useIF.add("이름");  useIF.add("전화번호");  
    useIF.add("E-Mail");  useIF.add("주소");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, useIF);
    table = new JTable(model);   
    
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
   table.addMouseListener(this);
   table.setRowHeight(25);
   //회원 관리 들어갔을 때 모든 회원 볼 수 있도록
   try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
      } catch(ClassNotFoundException e) {
      System.err.println("드라이버 로드에 실패했습니다.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB 연결 완료."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
    if(ae.getActionCommand()=="재조회"){
     try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
      } catch(ClassNotFoundException e) {
      System.err.println("드라이버 로드에 실패했습니다.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB 연결 완료."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
  }//클릭하면 새 창열려서 회원과 관련된 작업하게
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
    JLabel warning = new JLabel("어떤 작업을 하시겠습니까?");
     ok = new JButton("삭제");
    cancel = new JButton("취소");
    search = new JButton("조회");
     top.add(warning); bottom.add(ok); bottom.add(cancel); bottom.add(search); 
    ok.addActionListener(this); cancel.addActionListener(this); search.addActionListener(this);
  }

  public void actionPerformed(ActionEvent ae){
  String s = ae.getActionCommand();
  if(s=="삭제"){//삭제 버튼 눌렀을 시 회원 삭제
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
      } catch(ClassNotFoundException e) {
      System.err.println("드라이버 로드에 실패했습니다.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB 연결 완료."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
      String delete;
      delete = "DELETE FROM mem WHERE mid='"+del_id+"';";
      dbSt.executeUpdate(delete);
      MessageDialog md = new MessageDialog(this, "회원정보 삭제", true,"삭제되었습니다");    
      md.show();        System.out.println("데이터 삭제 완료");
      dbSt.close();       
      con.close();
     } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
      } 
    
    else if(s=="취소")//취소 버튼 눌렀을 시 추가 작업 창 닫힘
      ;
    else if(s=="조회"){//조회 버튼 눌렀을 때 그 회원의 대출, 신청 도서 볼 수 있는 창 열림
       UserNew new_ = new UserNew(del_id);
       new_.setSize(720, 600);
       new_.setLocation(400, 300);
       new_.show(); }     
      dispose();
  }
}

class UserBook extends JPanel implements ActionListener{//회원의 대출 도서 클래스
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
    MyBook.add("도서번호");  MyBook.add("제목");  
    MyBook.add("출판사");  MyBook.add("대출일");  
    MyBook.add("반납일");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, MyBook);
    table = new JTable(model);    
    table.setRowHeight(25);
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
  
    message = new JButton("문자전송");
    this.add(message); 
    message.addActionListener(this);
    message.setEnabled(false);

      try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
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
                }//책이 연체 되었으면 문자전송 버튼 활성화 되어 알림 전송
        dbSt.close();
        con.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }      
  }

  public void actionPerformed(ActionEvent ae){       
    if(ae.getActionCommand()=="문자전송"){       
       sendText m= new sendText(m_id);
       m.setSize(360, 300);
       m.setLocation(400, 300);
       m.show();
     }
  } //문자 전송 버튼 눌렀을 때    
}   

class sendText extends JFrame {
    // 문자 전송시 뜨는 창
    int s_id;
    public sendText(int m_id) {
     s_id=m_id;
     JPanel alram = new JPanel();
     setContentPane(alram);  
     JLabel text = new JLabel("문자를 전송했습니다.");   
     alram.add(text);  
     setSize(300,50); 
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String Bborrow = "Update mem SET alarm ='"+"연체된 도서가 있습니다"+"' WHERE mid='"+s_id+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("데이터 수정 완료");        
        dbSt.close();       
        con.close();
       } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }//문자 전송 눌렀을 때 전송되는 알림
  }
}

class UserApBook extends JPanel {//회원의 신청 도서 클래스
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
    myApplyBook.add("신청 날짜");  myApplyBook.add("제목");
    myApplyBook.add("출판사");  myApplyBook.add("구입 여부");

    model = new DefaultTableModel(rowData, myApplyBook);
    table = new JTable(model);  
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
  table.setRowHeight(25);
     try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String book_info;   
        book_info = "SELECT  bk.oday, bk.otitle, bk.opublish, bk.ostate from bkorder bk JOIN mem m on bk.oid=m.mid WHERE m.mid='"+m_id+"';";
        ResultSet rs=dbSt.executeQuery(book_info);
         while(rs.next()){
           String s = rs.getString(4);
           String sta = "";
           if(s.equals("0"))
             sta="미구입";
           else
             sta ="구입";
           model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),sta});
                }
        dbSt.close();
        con.close();   
        } catch (SQLException e) {
         System.out.println("SQLException : "+e.getMessage()); }      
  }
}

class UserNew extends JFrame{//회원 조회 눌렀을 때 뜨는 창
  int m_id;
  public UserNew(int id){
    m_id=id;
    JTabbedPane jtp = new JTabbedPane();
    UserBook ub = new UserBook(m_id);
    UserApBook uab = new UserApBook(m_id);
    
    jtp.addTab("대출 도서", ub);
    jtp.addTab("신청 도서", uab);
    Container ct = getContentPane();
    ct.add(jtp);
  }    
}

class ApplyBook extends JPanel{//신청 도서 클래스

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
    ApplyBook.add("신청 날짜");  ApplyBook.add("제목");
    ApplyBook.add("출판사");  ApplyBook.add("신청 회원 번호");  ApplyBook.add("구입 여부");  
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, ApplyBook);
    table = new JTable(model);   
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.setRowHeight(25);
    top.add(new JLabel("신청 도서 목록"));
    try {
      Class.forName("com.mysql.jdbc.Driver"); 
      System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
      } catch(ClassNotFoundException e) {
      System.err.println("드라이버 로드에 실패했습니다.");
      }  
    try {         
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
      System.out.println("DB 연결 완료."); 
      Statement dbSt = con.createStatement();
      System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String info_Appbok;
        info_Appbok = "SELECT * FROM bkorder;";  
        ResultSet rs=dbSt.executeQuery(info_Appbok);     
        while(rs.next()){
         String s = rs.getString("ostate");
         String sta = "";
         if(s.equals("0"))
           sta="미구입";
         else
           sta ="구입";
         model.addRow(new Object[]{rs.getString("oday"),rs.getString("otitle"),rs.getString("opublish"), rs.getString("oid"), sta});
         }
      dbSt.close();       
      con.close();
      } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
  }
}

class BorrowBook extends JPanel implements ActionListener, MouseListener{//대출 클래스
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
    JLabel book = new JLabel("제목: ");
    Book = new JTextField (15);
    JButton search = new JButton("검색");
    search.addActionListener(this);
    search.setSize(20,10);
    p1.add(book);  p1.add(Book); p1.add(search); top.add(p1);
   p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    BorrowBook = new Vector<String>();
    BorrowBook.add("도서 번호");  BorrowBook.add("제목");
    BorrowBook.add("출판사");  BorrowBook.add("대출 상태"); 
    rowData = new Vector<Vector<String>>();
    model = new DefaultTableModel(rowData, BorrowBook);
    table = new JTable(model);
    tableSP = new JScrollPane(table);
    tableSP.setBounds(100, 50, 1400, 750);
    add(tableSP);
    table.addMouseListener(this);
    table.setRowHeight(25);
   JButton borrow = new JButton("대출");
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
     }// 대출 시 반납 예정일 설정
    if(s.equals("검색")){
       model.setRowCount(0);
       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String sqlbook;
        sqlbook = "SELECT * FROM book WHERE title='"+t_title+"';";
        ResultSet rs=dbSt.executeQuery(sqlbook);     // DB로부터 읽어온 레코드를 객체화
        while(rs.next()){  
        int year, month, day;
         String b = rs.getString("borw");
         String bo ="";
         if(b.equals("0"))
           bo="대출가능";
         else if(b.equals("1"))
            bo="대출";
         else if(b.equals("2"))
            bo="연체";
         model.addRow(new Object[]{rs.getString("bnum"),rs.getString("title"),rs.getString("publisher"),bo});
         }dbSt.close();       
          con.close();    
         } catch (SQLException e) {
          System.out.println("SQLException : "+e.getMessage()); }
    }
    else if(s.equals("대출")){

       try {
        Class.forName("com.mysql.jdbc.Driver"); 
        System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
        } catch(ClassNotFoundException e) {
        System.err.println("드라이버 로드에 실패했습니다.");
        }  
      try {         
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/klib?serverTimezone=UTC", "root", "1325");
        System.out.println("DB 연결 완료."); 
        Statement dbSt = con.createStatement();
        System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
        String Bborrow = "Update book SET borw ='"+1+"' where bnum='"+t_bnum+"';";
        dbSt.executeUpdate(Bborrow);
        System.out.println("데이터 수정 완료");        
        String strSql ="INSERT INTO Borw (bnum, id, bory, borm, bord, duey, duem, dued) VALUES ("+"'"+t_bnum+"', '"+t_id+"','"+nYear+"', '"+nMonth+"', '"+nDay+"', '"+rYear+"', '"+rMonth+"', '"+rDay+"');";
        dbSt.executeUpdate(strSql);
        System.out.println("데이터 삽입 완료");
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
  }//선택한 행의 도서 번호 얻기
  public void mousePressed (MouseEvent m){}
  public void mouseReleased (MouseEvent m){}
  public void mouseEntered (MouseEvent m){}
  public void mouseExited (MouseEvent m){}
}

class MessageDialog extends JDialog implements ActionListener {  //메세지 다이얼로그 클래스                                                   
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

    ad.addTab("도서 관리", b);
    ad.addTab("회원 관리", u);
    ad.addTab("신청도서관리", ap);
    ad.addTab("대출 관리", bB);

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