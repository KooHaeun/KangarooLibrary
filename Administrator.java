import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;


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
          {"철학일반", "형이상학", null, "철학체계", "경학", null, "서양철학", "논리학", "심리학", "윤리학, 도덕철학"}, 
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
             if(nMonth==month)
               if(nDay<day)
                 message.setEnabled(true);
               else if(nMonth<month)
                 message.setEnabled(true); }
             else if(nYear<year)
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
  Mes msg;
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
    JLabel id = new JLabel("I D: ");
    ID = new JTextField (15);
    p0.setLayout(new FlowLayout(FlowLayout.LEFT));
    p0.add(id);  p0.add(ID); top.add(p0);
    
    JPanel p1= new JPanel();
    JLabel book = new JLabel("제목: ");
    Book = new JTextField (15);
    JButton search = new JButton("검색");
    search.addActionListener(this);
    search.setSize(20,10);
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    p1.add(book);  p1.add(Book); p1.add(search); top.add(p1);

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
     if(t_id==" ")
       msg = new Mes("아이디 입력", "아이디를 입력하세요"); // 아이디 입력 안했을 시 대출불가
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
class Mes extends JFrame implements ActionListener{
  Button ok;
  Mes(String title, String msg){
      this.setTitle(title);
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

  public static void main(String[] args) {
    Administrator admin= new Administrator();
    admin.setTitle("관리자 화면");
    admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    admin.setSize(1600, 800);
    admin.setVisible(true);
	}
}
