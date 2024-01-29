import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

class Toy {
    int id;
    String name;
    int weight;

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }
}

class ToyWeightComparator implements Comparator<Toy> {
    public int compare(Toy t1, Toy t2) {
        return t2.weight - t1.weight;
    }
}

class ToyStore {
    PriorityQueue<Toy> toys;
    Random random;

    public ToyStore() {
        toys = new PriorityQueue<>((Comparator) new ToyWeightComparator());
        random = new Random();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public String getToy() {
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.weight;
        }

        int randomPoint = random.nextInt(totalWeight);

        int currentPoint = 0;
        for (Toy toy : toys) {
            currentPoint += toy.weight;
            if (randomPoint < currentPoint) {
                return String.valueOf(toy.id);
            }
        }

        return "1"; // This should never happen
    }

    public void run() throws IOException {
        addToy(new Toy(1, "constructor", 2));
        addToy(new Toy(2, "robot", 2));
        addToy(new Toy(3, "doll", 6));

        FileWriter writer = new FileWriter("result.txt");

        for (int i = 0; i < 10; i++) {
            int toyId = Integer.parseInt(getToy());
            writer.write(toyId + "\n");
        }

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        ToyStore toyStore = new ToyStore();
        toyStore.run();
    }
}