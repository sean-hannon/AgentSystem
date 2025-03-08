import java.util.*;

class AssignmentSystem {
    private static class Agent {
        String name;
        int assignments;
        int limit;
        long lastAssignedTime;

        public Agent(String name) {
            this.name = name;
            this.assignments = 0;
            this.limit = 2; // Default limit
            this.lastAssignedTime = System.nanoTime();
        }
    }

    private PriorityQueue<Agent> queue;
    private Map<String, Agent> agentMap;

    public AssignmentSystem(List<String> agents) {
        agentMap = new HashMap<>();
        queue = new PriorityQueue<>((a, b) -> a.assignments == b.assignments
                ? Long.compare(a.lastAssignedTime, b.lastAssignedTime)
                : Integer.compare(a.assignments, b.assignments));

        for (String agent : agents) {
            Agent newAgent = new Agent(agent);
            agentMap.put(agent, newAgent);
            queue.offer(newAgent);
        }
    }

    public void setLimit(String agentName, int limit) {
        if (agentMap.containsKey(agentName)) {
            Agent agent = agentMap.get(agentName);
            agent.limit = limit;
        }
    }

    public String assign(String id) {
        if (queue.isEmpty()) return null;

        Agent agent = queue.poll();
        if (agent.assignments < agent.limit) {
            agent.assignments++;
            agent.lastAssignedTime = System.nanoTime();
            System.out.println("Assigning call " + id + " to " + agent.name);
            queue.offer(agent);
            return agent.name;
        }
        return null;
    }

    public List<String> getAssignmentQueue(int n) {
        List<String> result = new ArrayList<>();
        PriorityQueue<Agent> tempQueue = new PriorityQueue<>(queue);

        for (int i = 0; i < n && !tempQueue.isEmpty(); i++) {
            Agent agent = tempQueue.poll();
            if (agent.assignments < agent.limit) {
                result.add(agent.name);
                agent.lastAssignedTime = System.nanoTime();
                tempQueue.offer(agent);
            }
        }
        return result;
    }
}
