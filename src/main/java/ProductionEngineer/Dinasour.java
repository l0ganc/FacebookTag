package ProductionEngineer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dinasour {
    private static double g = 9.8 * 9.8;
    private static class Dina{
        String name;
        double speed;
        private Dina(String name, double speed) {
            this.name = name;
            this.speed = speed;
        }
    }
    private static List<String> getName(String dataset1Path, String dataset2Path) {
        Map<String, Double> nameToStride = new HashMap<>();
        Map<String, Double> nameToLeg = new HashMap<>();
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(dataset2Path));
            reader1.readLine();  //read the first title line
            String line1;

            while ((line1 = reader1.readLine()) != null) {
                String[] item1 = line1.split(",");
                if (item1[2].equals("bipedal")) {
                    nameToStride.put(item1[0], Double.parseDouble(item1[1]));
                }
            }

            BufferedReader reader2 = new BufferedReader(new FileReader(dataset1Path));
            reader2.readLine();
            String line2;

            while ((line2 = reader2.readLine()) != null) {
                String[] item2 = line2.split(",");
                if (nameToStride.containsKey(item2[0])) {
                    nameToLeg.put(item2[0], Double.parseDouble(item2[1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        PriorityQueue<Dina> maxHeap = new PriorityQueue<>((x, y) -> Double.compare(y.speed, x.speed));
        for (String name : nameToLeg.keySet()) {
            maxHeap.add(new Dina(name, calcSpeed(nameToStride.get(name), nameToLeg.get(name))));
        }

        List<String> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll().name);
        }

        return res;
    }

    private static double calcSpeed(double stride_length, double leg_length) {
        return ((stride_length / leg_length) - 1) * Math.sqrt(leg_length * g);
    }
    public static void main(String[] args) {
//        String[] dataset1 = new String[] {
//                "Hadrosaurus,1.2,herbivore",
//                "Struthiomimus,0.92,omnivore",
//                "Velociraptor,1.0,carnivore",
//                "Triceratops,0.87,herbivore",
//                "Euoplocephalus,1.6,herbivore",
//                "Euoplocephalus,1.6,herbivore",
//                "Tyrannosaurus Rex,2.5,carnivore"
//        };
//        String[] dataset2 = new String[] {
//                "Euoplocephalus,1.87,quadrupedal",
//                "Stegosaurus,1.90,quadrupedal",
//                "Tyrannosaurus Rex,5.76,bipedal",
//                "Tyrannosaurus Rex,5.76,bipedal",
//                "Deinonychus,1.21,bipedal",
//                "Struthiomimus,1.34,bipedal",
//                "Struthiomimus,1.34,bipedal"
//        };

        System.out.println(getName("/Users/logan/Downloads/FacebookTag/src/main/java/ProductionEngineer/dataset1.csv", "/Users/logan/Downloads/FacebookTag/src/main/java/ProductionEngineer/dataset2.csv"));


    }
}
