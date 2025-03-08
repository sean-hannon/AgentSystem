import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> agents = new ArrayList<>();
        agents.add("Alice");
        agents.add("Bob");
        agents.add("Charlie");

        AssignmentSystem system = new AssignmentSystem(agents);

        system.setLimit("Bob", 4);
        system.setLimit("Charlie", 3);

        List<String> queue = system.getAssignmentQueue(4);
        System.out.println(queue);

        system.assign("101");
        system.assign("102");
        system.assign("103");
        system.assign("104");

        List<String> agentQueue = system.getAssignmentQueue(5);
        System.out.println(agentQueue);
    }
}
