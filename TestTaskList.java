import java.util.Scanner;

public class TestTaskList<E> {
    //creates class to test TaskList
    private TaskList<E> toDoList = new TaskList<>();
    //creates data field for the to-do list. List is originally empty.
    private Scanner scan;
    //create data field for scanning input

    public static void main(String[] args) {
        //creates a TestTaskList object and calls the printMenu method from it
        TestTaskList<String> test = new TestTaskList<>();
        test.printMenu();
    }

    public void printMenu(){
        //prints menu with options for the toDoList ranging from 1-8. Checks to see if the menu should keep reprinting or if an error occurred depending on input.
        System.out.println("~~~ TO-DO List Program, created by truly yours ~~~");
        Scanner input = new Scanner(System.in);
        boolean done = false;
        while(!done){
            if(toDoList.getActive().getFront() == null ){
                System.out.println("==> Currently there are NO items in the To-Do List");
            }
            else if(toDoList.getActive().getFront().getData() != null){
                System.out.println("Current TO-DO List:");
                System.out.println("-------------------");
                toDoList.showActiveTasks();
            }
            System.out.println("To add a new task without priority information, press 1.");
            System.out.println("To add a new task with a priority information, press 2.");
            System.out.println("To cross off the task at the top of the list, press 3.");
            System.out.println("To cross off a certain task in the list, press 4.");
            System.out.println("To see the top 3 highest priority tasks, press 5.");
            System.out.println("To see the completed tasks, press 6.");
            System.out.println("To see the all tasks that has been completed or still active, press 7.");
            System.out.println("To quit the program, press 8.");
            String user = input.nextLine();
            int choose;
            try{
                choose = Integer.parseInt(user);
            }
            catch (NumberFormatException e){
                System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
                continue;
            }
            done = processMenuItem(choose);


        }

    }
    private boolean processMenuItem(int menuItem){
        //takes one input: operation that user chose (menuItem). It calls the appropriate functions for each operation from the toDoList object
        if (menuItem != 1 && menuItem != 2 && menuItem != 3&& menuItem != 4 && menuItem != 5 && menuItem != 6 && menuItem != 7 && menuItem != 8){
            System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
            return false;
        }
        else if(menuItem == 1){
            System.out.println("Please enter the task description:");
            Scanner input = new Scanner(System.in);
            E task = (E) input.nextLine();
            toDoList.createTask(task);
            System.out.println("Successfully entered the task to the to-do list!");
            return false;
        }
        else if (menuItem == 2) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter the task description:");
            E task = (E) input.nextLine();
            System.out.println("Please enter a priority number (1 indicates highest priority, increasing numbers show lower priority) :");
            String user = input.nextLine();
            int choose;
            try {
                choose = Integer.parseInt(user);
            } catch (NumberFormatException e) {
                return false;
            }
                toDoList.createTask(task, choose);
                System.out.println("Successfully entered the task to the to-do list!");
                return false;
        }
        else if(menuItem == 3){
            if(toDoList.getActive().getFront() == null){
                System.out.println("Unsuccessful operation! Please try again!");
                return false;
            }
            System.out.println("Task is completed and removed from the list: " + toDoList.getActive().peek());
            toDoList.crossOffMostUrgent();
            System.out.println("Successfully removed the most urgent task/top of the list task!");
            return false;


        }
        else if(menuItem == 4){
            System.out.println("Please enter the task number you would like to cross off the list :");
            Scanner input = new Scanner(System.in);
            String user = input.nextLine();
            int choose;
            try {
                choose = Integer.parseInt(user);
            } catch (NumberFormatException e) {
                System.out.println("Unsuccessful operation! Please try again!");
                return false;
            }
            if(choose < 1 || choose > toDoList.getActive().getSize()){
                System.out.println("Unsuccessful operation! Please try again!");
                return false;
            }
            System.out.print("Task number " + choose + " is removed: ");
            toDoList.crossOffTask(choose);
            System.out.println("Successfully removed the task number: " + choose);
            return false;
        }
        else if(menuItem == 5){
            System.out.println("Top 3 highest priority tasks:");
            System.out.println("------------------------------");
            System.out.println("Printing Top Three Tasks...");
            toDoList.printTopThreeTasks();
            return false;
        }
        else if(menuItem == 6){
            System.out.println("Completed Tasks:");
            System.out.println("----------------");
            toDoList.showCompletedTasks();
            return false;
        }
        else if(menuItem == 7){
            System.out.println("All of the Tasks - Both completed and active:");
            System.out.println("---------------------------------------------");
            toDoList.showAllTasks();
            return false;
        }
        else if(menuItem == 8){
            return true;

        }return true;

    }
}
