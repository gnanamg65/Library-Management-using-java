package javas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
     String title;
     String author;
     String ISBN;
    boolean isAvailable;

    public Book(String title, String author, String ISBN) 
    {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getAuthor() 
    {
        return author;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public boolean isAvailable() 
    {
        return isAvailable;
    }

    public void setAvailable(boolean available) 
    {
        isAvailable = available;
    }


}

class User {
     String name;
     String userId;
     List<Book> borrowedBooks;


    public User(String name, String userId) 
    {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() 
    {
        return name;

    }

    public String getUserId()
     {
        return userId;
    }

    public void borrowBook(Book book) 
    {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) 
    {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    public List<Book> getBorrowedBooks() 
    {
        return borrowedBooks;
    }
}

class Library 
{
     List<Book> books = new ArrayList<>();
     List<User> users = new ArrayList<>();
    
    public void addBook(Book book) 
    {
        books.add(book);
    }

    public void addUser(User user) 
    {
        users.add(user);
    }

    public Book findBookByISBN(String ISBN)
     {
        for (Book book : books) 
        {
            if (book.getISBN().equals(ISBN) && book.isAvailable()) 
            {
                return book;
            }
        }
        return null;
    }

    public User findUserById(String userId)
     {
        for (User user : users) 
        {
            if (user.getUserId().equals(userId)) 
            {
                return user;
            }
        }
        return null;
    }

    public void borrowBook(String ISBN, String userId)
     {
        Book book = findBookByISBN(ISBN);

        User user = findUserById(userId);

        if (book != null && user != null)
        {
            user.borrowBook(book);
        }
         else 
        {
            System.out.println("Book or User not found or Book is not available.");
        }
    }

    public void returnBook(String ISBN, String userId)
     {

        User user=findUserById(userId);

        if(user!=null)
        {
           for( Book x:user.getBorrowedBooks())

           if(x.getISBN().equals(ISBN))
           {
                 user.returnBook(x);
                 break;
           }
        }
     }

    public void printAllBooks() 
    {
        for(Book u:books)
        {
            System.out.print("{ "+u.getAuthor()+" ");
            System.out.print(u.getTitle()+" ");
            System.out.print(u.getISBN()+" ");
            System.out.print(u.isAvailable()+" }");
            System.out.println();
            
        }
    }
    public void printAllUsers() 
    {
        System.out.println("-----------------------");  
        for(User u:users)
        {
            System.out.print("{"+u.getName()+" ");
            System.out.println(u.getUserId()+"}");
        }
    }
}
class LibraryApplication
 {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

    
        library.addBook(new Book("life Story", "jeeva", "12345"));
        library.addBook(new Book("Kill The Turth", "sanjay", "1234"));
        library.addUser(new User("gnanaprakash", "1"));
        library.addUser(new User("seethal", "2"));

        while (true) 
        {
            System.out.println("--------------------------");
            System.out.println("\nChoose an option:");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("1. Add Book \n2. Add User \n3. Borrow Book \n4. User Borrowed Book \n5. Return Book  \n6. List Books \n7. List of user \n8. Exit");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("Enter choice: ");
            int choice = scanner.nextInt();
            
            scanner.nextLine();  
            switch (choice)
             {
                case 1:
                    library.printAllBooks();
                    System.out.println("--------------------------");
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String ISBN = scanner.nextLine();
                    library.addBook(new Book(title, author, ISBN));
                    break;
                case 2:
                    library.printAllUsers();
                    System.out.println("--------------------------");
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    
                     for(User u:library.users){
                        if(u.userId.equals(userId)){
                            System.out.println("user id is already register");
                            break;
                        }
                        else{
                            library.addUser(new User(name, userId));
                            System.out.println("user id add successfully");
                            break;
                        }
                     }
                    break;
                case 3:
                    library.printAllBooks();
                    System.out.println("--------------------------");
                    System.out.print("Enter ISBN to borrow: ");
                    String borrowISBN = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String borrowUserId = scanner.nextLine();
                    library.borrowBook(borrowISBN, borrowUserId);
            
                    break;
                case 4: 
                    System.out.println("--------------------------");
                    System.out.print("Enter User ID: ");
                    String borrowUserId1 = scanner.nextLine();
                    for(User x:library.users)
                    {
                        if(x.getUserId().equals(borrowUserId1))
                        {
                        for(Book u:x.getBorrowedBooks())
                        {
                        System.out.print("{ "+u.getAuthor()+" ");
                        System.out.print(u.getTitle()+" ");
                        System.out.print(u.getISBN()+" ");
                        System.out.print(u.isAvailable()+" }");
                        System.out.println();
                        }
                        }
                    }
                        break;
                   
                case 5:
                    System.out.println("--------------------------");
                    System.out.print("Enter ISBN to return: ");
                    String returnISBN = scanner.nextLine();
                    System.out.print("Enter User ID: ");
                    String returnUserId = scanner.nextLine();
                    library.returnBook(returnISBN, returnUserId);
                    break;
                    
                case 6:
                    System.out.println("--------------------------");
                    library.printAllBooks();
                    break;
                case 7:
                  
                    library.printAllUsers();
                    break;
                case 8:
                    System.out.println("--------------------------");
                    System.out.println("**Welcome**");
                    scanner.close();
                    return;
                default:
                   // System.out.println("--------------------------");
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


    

