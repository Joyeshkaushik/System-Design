abstract class ModelTrainer{
    public  final void trainPipeline(String dataPath){
        loadData(dataPath);
        preprocessData();
        trainModel();      // subclass-specific
        evaluateModel();   // subclass-specific
        saveModel();       // subclass-specific or default
    }
     protected void loadData(String path){
         System.out.println("[Common] Loading dataset from " + path);

     }
     protected void preprocessData(){
          System.out.println("[Common] Splitting into train/test and normalizing");
     }
        protected abstract void trainModel();
    protected abstract void evaluateModel();
     protected void saveModel() {
        System.out.println("[Common] Saving model to disk as default format");
    }

     
}
class NeuralNetworkTrainer extends ModelTrainer{
       protected void trainModel() {
        System.out.println("[NeuralNet] Training Neural Network for 100 epochs");
        // pseudo-code: forward/backward passes, gradient descent...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }

    @Override
    protected void saveModel() {
        System.out.println("[NeuralNet] Serializing network weights to .h5 file");
    }

     
}
class DecisionTreeTrainer extends ModelTrainer{
         protected void trainModel() {
        System.out.println("[DecisionTree] Building decision tree with max_depth=5");
        // pseudo-code: recursive splitting on features...
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[DecisionTree] Computing classification report (precision/recall)");
    }
     }

public class TemplateMethodPattern {
    public static void main(String[] args) {
          System.out.println("=== Neural Network Training ===");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeline("data/images/");

        System.out.println("\n=== Decision Tree Training ===");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeline("data/iris.csv");
    }
}
