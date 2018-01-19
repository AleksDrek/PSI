public class Main {

    public static void main(String[] args) {
        System.out.println("Scenariusz 2 - Budowa i dzialanie sieci jednowarstwowej");

        String wejsciePath = "src/Resources/wejscie.txt";
        String wejscieTestPath = "src/Resources/daneTestowe.txt";
        
            NeuralNetwork network = new NeuralNetwork(wejsciePath, wejscieTestPath);

    }
    
}