import java.util.Iterator;

public class TaskList<E> {
    //class for creating the task list that includes different ListQueues
    private ListQueue<E> all;
    //creates data field for the "all" ListQueue
    private ListQueue<E> completed;
    //creates data field for the "completed" ListQueue
    private ListQueue<E> active;
    //creates data field for the "active" ListQueue
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    //creates data field for lowest priority
    private int HIGH_PRIORITY = 1;
    //creates data field for highest priority

    public TaskList(){
        //initializes empty task list
        all = new ListQueue();
        completed = new ListQueue();
        active = new ListQueue();
    }
    public boolean createTask(E item){
        //creates a new task and adds it to all and active ListQueues as lowest priority
        if(item == null){
            return false;
        }
        all.offer(item, LOW_PRIORITY);
        active.offer(item, LOW_PRIORITY);
        return true;

    }
    public boolean createTask(E item, int priority){
        //creates a new task and adds it active and all ListQueues according to priority
        if(item == null){
            return false;
        }
        all.offer(item, priority);
        active.offer(item, priority);
        return true;
    }
    public ListQueue<E> getAll(){
        return all;
    }
    //returns the "all" ListQueue
    public ListQueue<E> getActive(){
        return active;
    }
    //returns the "active" ListQueue
    public ListQueue<E> getCompleted(){
        return completed;
    }
    //returns the "completed" ListQueue
    public void printTopThreeTasks(){
        //prints the top 3 highest priority tasks
        Iterator<E> it  = active.iterator();
        int count = 1;
        while(it.hasNext() && count < 4){
            System.out.print(count + ". ");
            System.out.println(it.next());
            count++;
        }
    }
    public void showActiveTasks(){
        //prints all the tasks in active ListQueue
        printTasks(active);

    }
    public void showAllTasks(){
        //prints all the tasks in all ListQueue
        printTasks(all);

    }
    public void showCompletedTasks(){
        //prints all the tasks in completed ListQueue
        printTasks(completed);

    }
    private void printTasks(ListQueue<E> queue){
        //prints the tasks in a given ListQueue. Front of the queue will have the task number 1 and each next node will have an increasing task number.
        Iterator<E> it  = queue.iterator();
        int count = 1;
        for(int i = 0; i<queue.getSize(); i++) {
            E what = null;
            if (it.hasNext()) {
                what = it.next();
                System.out.print(count + ". ");
                System.out.println(what);
                count++;
            }
        }
    }
    public boolean crossOffMostUrgent(){
        //will remove the highest priority task from the front of the queue and returns true if it successfully removes. If it does not exist, it prints an error message and returns false
        if(active.getFront() == null){
            System.out.print("ERROR!");
            return false;
        }
        completed.addRear(active.peek());
        active.poll();
        return true;
    }
    public boolean crossOffTask(int taskNumber){
        //removes the task at the location identified by taskNumber. Front of the queue will have 1 as the taskNumber and numbers will be incremented by one for each next task. Numbers can be seen in the printed list. Returns true for successful run. If
        //task number is greater than the size of the active list or could not remove it successfully, method returns false
        if(taskNumber < 1 || taskNumber> active.getSize()){
            System.out.println("Unsuccessful operation! Please try again!");
            return false;
        }
        ListQueue current = active;
        ListQueue.Node what =current.getFront();
        int count = 1;
        if(taskNumber == 1){
            active.remove(what);
            completed.addRear((E) what.getData());
            System.out.println((E) what.getData());
            return true;
        }
        while(count<= current.getSize() && count != taskNumber){
            count++;
            what = what.getNext();
        }
        if(taskNumber == count){
            active.remove(what);
            completed.addRear((E) what.getData());
            System.out.println((E) what.getData());
            return true;
        }

        return false;
    }

}
