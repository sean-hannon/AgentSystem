import java.util.*;

class AssignmentSystem {
    private static class Agent {
        String name;
        int assignments = 0;
        int limit = 2; // Default limit
        long lastAssignedTime;

        public Agent(String name) {
            this.name = name;
            this.lastAssignedTime = System.nanoTime();
        }
    }

    private PriorityQueue<Agent> queue;
    private Map<String, Agent> agentMap;

    public AssignmentSystem(List<String> agents) {
        agentMap = new HashMap<>(agents.size());
        queue = new PriorityQueue<>(Comparator.comparingInt((Agent a) -> a.assignments)
                .thenComparingLong(a -> a.lastAssignedTime));

        for (String agent : agents) {
            Agent newAgent = new Agent(agent);
            agentMap.put(agent, newAgent);
            queue.offer(newAgent);
        }
    }

    public void setLimit(String agentName, int limit) {
        if (agentMap.containsKey(agentName)) {
            agentMap.get(agentName).limit = limit;
        }
    }

    public String assign(String id) {
        if (queue.isEmpty()) return null;

        Agent agent = queue.poll();
        if (agent.assignments < agent.limit) {
            agent.assignments++;
            agent.lastAssignedTime = System.nanoTime();
            queue.offer(agent);
            System.out.println("Assigning call " + id + " to " + agent.name);
            return agent.name;
        }
        return null;
    }

    public List<String> getAssignmentQueue(int n) {
        List<String> result = new ArrayList<>(n);
        PriorityQueue<Agent> tempQueue = new PriorityQueue<>(queue);

        while (n-- > 0 && !tempQueue.isEmpty()) {
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