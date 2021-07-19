import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;


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
          {"ö���Ϲ�", "���̻���", null, "ö��ü��", "����", null, "����ö��", "����", "�ɸ���", "������, ����ö��"}, 
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
             if(nMonth==month)
               if(nDay<day)
                 message.setEnabled(true);
               else if(nMonth<month)
                 message.setEnabled(true); }
             else if(nYear<year)
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
    JLabel book = new JLabel("����: ");
    Book = new JTextField (15);
    JButton search = new JButton("�˻�");
    search.addActionListener(this);
    search.setSize(20,10);
    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
    p1.add(book);  p1.add(Book); p1.add(search); top.add(p1);

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
     if(t_id==" ")
       msg = new Mes("���̵� �Է�", "���̵� �Է��ϼ���"); // ���̵� �Է� ������ �� ����Ұ�
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

  public static void main(String[] args) {
    Administrator admin= new Administrator();
    admin.setTitle("������ ȭ��");
    admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    admin.setSize(1600, 800);
    admin.setVisible(true);
	}
}
