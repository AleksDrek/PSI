import java.util.Random;



public class Perceptron {

    static int MAX_ITER = 1500;
    static double LEARNING_RATE = 0.001;
    static int NUM_INSTANCES = 4;
    static int theta = 0;

    public static void main(String[] args){
        Random randGenerator = new Random();

        //wartosci zmiennych
        int[] x = new int[NUM_INSTANCES];
        int[] y = new int[NUM_INSTANCES];
        int[] outputs = new int[NUM_INSTANCES];

        //inicjalizacja wartosciami bramki OR
        x[0] = 0; y[0] = 0;
        x[1] = 0; y[1] = 1;
        x[2] = 1; y[2] = 0;
        x[3] = 1; y[3] = 1;

        outputs[0] = 0;
        outputs[1] = 1;
        outputs[2] = 1;
        outputs[3] = 1;

        double[] weights = new double[3]; //2 dla zmiennych + bias
        double localError, globalError;
        int p, iteration, output;

        weights[0] = randomNumber(-1, 1); //losowa inicjalizacja wag, z zakresu <-1;1>
        weights[1] = randomNumber(-1, 1);
        weights[2] = randomNumber(-1, 1);

        int test = 1;
        iteration = 0;
        do{
            iteration++;
            globalError = 0;

            System.out.println("Wagi: "+weights[0]+", "+weights[1]);
            //pętla wykonująca jedną epokę
            for(p = 0;p<NUM_INSTANCES;p++){

                //obliczenia
                output = calculateOutput(theta, weights, x[p], y[p]);
                //różnica pomiędzy obliczonym, a prawdziwym wynikiem
                localError = outputs[p] - output;
                //obliczenia wag
                weights[0] += LEARNING_RATE*localError*x[p];
                weights[1] += LEARNING_RATE*localError*y[p];

                //update bias
                weights[2] += LEARNING_RATE*localError;

                globalError += (localError*localError);
            }
            /*Root Mean Squared Error*/ if(test == 1 && globalError == 0){test = 0;
                System.out.println("----------------------------NAUCZONY--------------------------------");
                iteration = MAX_ITER -20;}
            System.out.println("Numer iteracji: "+iteration+" : RMSE = "+Math.sqrt(globalError/NUM_INSTANCES));
            int x1 = randGenerator.nextInt(2);
            int y1 = randGenerator.nextInt(2);

            output = calculateOutput(theta, weights, x1, y1);
            System.out.println("\n=======\nKolejny punkt:");
            System.out.println("x = "+x1+",y = "+y1);
            System.out.println("Obliczony wynik = "+output);

        }while(iteration < MAX_ITER);

        System.out.println("\n========\nWartości graniczne:");
        System.out.println(weights[0]+"*x "+weights[1]+"*y + "+weights[2]+" = 0");

    }

    public static double randomNumber(double min, double max){
        double d = min+Math.random()*(max-min);
        return d;
    }

    static int calculateOutput(int theta, double weights[], double x, double y){
        double sum  = x*weights[0] + y*weights[1] + weights[2];
        return sum>=theta ? 1:0;
    }
}