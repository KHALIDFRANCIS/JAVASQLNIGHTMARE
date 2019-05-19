package database;

import java.sql.*;

public class Francisdbmk2 {
	
//	get connection
	
	public static void main(String[] args) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/BOOKZ";
		String username = "root";
		String password = "CARBON216-x";

		
		
		try {

			
			Class.forName(driver);
//			
			Connection connect = DriverManager.getConnection(url,username,password);
			
			

          
          paperauthordetail1("spo.bob@nick.net", connect);
          
          reviewdetailpublish(connect);

          papercount(connect);

          newpapersub(connect);

          deleteFirstAuthor(connect);
          
		} catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

			
			
			
         private static void paperauthordetail1(String key, Connection conn) {

              try {
            	  Statement myStmt = conn.createStatement();
            	  

                  String sql = "SELECT " +
                          " PAPER.id as id, " +
                          " PAPER.title as title, " +
                          " PAPER.absrract as absrract, " +
                          " AUTHOR.email as email, " +
                          " AUTHOR.firstname as firstname, " +
                          " AUTHOR.lastname as lastname" +
                          " FROM " +
                          " PAPER, AUTHOR" +
                          " where " +
                          " PAPER.foreignmail = AUTHOR.email" +
                          " and AUTHOR.email = '" + key + "'";

                  ResultSet myRs = myStmt.executeQuery(sql);

                  while (myRs.next()) {
                      System.out.println("Paper Id:" + myRs.getInt("id"));
                      System.out.println("Paper Title:" + myRs.getString("title"));
                      System.out.println("Paper Abstract:" + myRs.getString("absrract"));
                      System.out.println("Author Email Address:" + myRs.getString("email"));
                      System.out.println("Author First Name:" + myRs.getString("firstname"));
                      System.out.println("Author Last Name:" + myRs.getString("lastname"));
                  }
//      			
      		} catch(Exception e)	{
      			e.printStackTrace();
      		}
      		

      	}

              
         private static void reviewdetailpublish(Connection conn) {

 	        try {
 	            Statement stmt = conn.createStatement();

 	            String sqlStr = "SELECT " +
 	                    " PAPER.id as id1," +
 	                    " REVIEW.paperid as paperid, " +
 	                    " REVIEW.reviewerid as revrid, " +
 	                    " REVIEW.recommendation as recommendation, " +
 	                    " REVIEW.meritscore as mscore," +
 	                    " REVIEW.readabilityscore as readable, " +
 	                    " REVIEW.originaliyscore as original," +
 	                    " REVIEW.relevancescore as relevant" +
 	                    " FROM " +
 	                    " REVIEW, PAPER" +
 	                    " where " +
 	                    " PAPER.id = REVIEW.paperid" +
 	                    " and REVIEW.recommendation = 'BEST SELLER!!'";

 	            ResultSet rset = stmt.executeQuery(sqlStr);

 	            while (rset.next()) {
 	                System.out.println("Id: " + rset.getInt("id1"));
 	                System.out.println("Paper Id: " + rset.getString("paperid"));
 	                System.out.println("Reviewer Id: " + rset.getString("revid"));
 	                System.out.println("Recommendation: " + rset.getString("recommendation"));
 	                System.out.println("Merit Score: " + rset.getInt("mscore"));
 	                System.out.println("Readability score: " + rset.getInt("readable"));
 	                System.out.println("Originality score: " + rset.getInt("original"));
 	                System.out.println("Relevance score: " + rset.getInt("relevant"));
 	                
 	            }

 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	    }
          
          
         private static void papercount(Connection conn) {

 	        try {
 	            Statement stmt = conn.createStatement();

 	            String sqlStr = "SELECT" +
 	                    " COUNT(*) as count" +
 	                    " FROM " +
 	                    " BOOKZ.REVIEW";
 	            ResultSet rset = stmt.executeQuery(sqlStr);
 	            while (rset.next()) {
 	                System.out.println("There are " + rset.getInt("count") );
 	                
// 	               
 	            }

 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	    }
  
			
         private static void newpapersub(Connection conn) {

 	        try {
 	            Statement ps = conn
 	                    .createStatement();
 	            String paperDeleteSql = "delete from paper where id = 5";
 	            String authorDeleteSql = "delete from author where email ='Spgbob@Nick.net'";
// 	            ps.executeUpdate(authorDeleteSql);
 	            ps.executeUpdate(paperDeleteSql);
 	            ps.executeUpdate(authorDeleteSql);


 	            String AUTHORSQL = "insert into AUTHOR (firstname, lastname, email)" +
 	                    "VALUES('Mic', 'Croms', 'MC@vtext.com')";


 	            int i = ps.executeUpdate(AUTHORSQL);
 	            if (i > 0)
 	                System.out.print("Thank you! You have successfully registered an author...");

 	            String papersQL = " INSERT INTO PAPER (filename, absrract, title, id, foreignmail)" +
 	                    " VALUES ('TBOCROMS', 'TBOC', 'The Book of Croms', '5', 'MC@aol.com')";

 	            System.out.println();
 	            int j = ps.executeUpdate(papersQL);
 	            if (j > 0)
 	                System.out.print("SUCCESS. PAPER SUBMITTED. ENJOY YOUR LIFE.");


 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }


 	    }


         private static void deleteFirstAuthor(Connection conn) {

 	        try {
 	            Statement ps = conn
 	                    .createStatement();
 	            String firstAuthorDeleteSql = "delete from AUTHOR where email='Spgbob@Nick.net'";

 	            System.out.println();
 	            int j = ps.executeUpdate(firstAuthorDeleteSql);
 	            if (j > 0)
 	                System.out.print("Author Deleted");
 	        } catch (SQLIntegrityConstraintViolationException e) {
 	            System.out.print("*Error* Author cannot be deleted. There is already a paper assigned to this author");
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }


 	    }

	

}
